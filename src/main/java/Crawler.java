import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

    private int totalBytes = 0;
    private Args arguments = null;

    public Crawler(Args arguments){
        this.arguments = arguments;
    }

    public static class Args {
        int links = 1000;
        String startingUrl = null;
        boolean randomizeUrlTraversal = false;
        int connectTimeOut = 2500;
        int readTimeOut = 2500;
        boolean showMoreInfo = false;

        void setArgument(String opt, String value){
            if("-u".equals(opt)){
                startingUrl = value;
            } else if("-l".equals(opt)){
                links = Integer.parseInt(value);
            } else if("-r".equals(opt)){
                randomizeUrlTraversal = Boolean.valueOf(value);
            } else if("-cto".equals(opt)){
                connectTimeOut = Integer.parseInt(value);
            } else if("-rto".equals(opt)){
                readTimeOut = Integer.parseInt(value);
            } else if("-d".equals(opt)){
                showMoreInfo = Boolean.valueOf(value);
            } else {
                throw new IllegalArgumentException("Bad argument [" + opt + "]");
            }
        }

        boolean isComplete(){
           return links > 0 && startingUrl != null;
        }
    }

    private List<String> extractUrlsFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(arguments.connectTimeOut);
        con.setReadTimeout(arguments.readTimeOut);
        Pattern pattern = Pattern.compile("charset=([^\\s]+)\\s*");

        if(con.getContentType() == null){
            throw new IllegalArgumentException("unable to read data from [" + urlStr +  "] " +
                    "within [" + (arguments.connectTimeOut + arguments.readTimeOut)  + "] ms");
        }

        Matcher matcher = pattern.matcher(con.getContentType());

        String charset = matcher.matches() ? matcher.group(1) : "ISO-8859-1";
        Reader reader = new InputStreamReader(con.getInputStream(), charset);
        StringBuilder htmlInString = new StringBuilder();
        char[] buf = new char[1024];
        int charsRead;
        while (0 < (charsRead = reader.read(buf))) {
            htmlInString.append(buf, 0, charsRead);
            totalBytes += charsRead;
        }
        List<String> urls = extractUrls(htmlInString.toString());

        return normalizeUrl(url.getProtocol(), url.getHost(), urls);
    }

    /**
     * Extracting urls is cleaner made with http://jsoup.org/cookbook/extracting-data/selector-syntax
     * or http://htmlparser.sourceforge.net/
     * Here there is an implementation with regexp found on the web.
     * @param input
     * @return
     */
    private List<String> extractUrls(String input) {
        List<String> result = new ArrayList<String>();

        Pattern pattern = Pattern.compile(
                "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
                        "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
                        "|mil|biz|info|mobi|name|aero|jobs|museum" +
                        "|travel|[a-z]{2}))(:[\\d]{1,5})?" +
                        "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                        "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                        "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                        "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

    private static String normalizeUrl(String protocol, String url) {
        return normalizeUrl(protocol, "", url);
    }

    private static String normalizeUrl(String protocol, String host, String url) {
        List<String> urls = new ArrayList<String>();
        urls.add(url);
        List<String> result = normalizeUrl(protocol, host, urls);
        return result.get(0);
    }

    private static List<String> normalizeUrl(String protocol, List<String> urls){
        return normalizeUrl(protocol, "", urls);
    }

    /**
     * This function adds protocol and host if an url is relative to the web site.
     * @param protocol
     * @param host
     * @param urls
     * @return
     */
    private static List<String> normalizeUrl(String protocol, String host, List<String> urls) {
        List<String> processedUrls = new ArrayList<String>();
        if(urls == null || urls.isEmpty()){
            return processedUrls;
        }
        for(String url : urls){
            String normalizedUrl = url;
            if(url.startsWith("http") || url.startsWith("https") || url.startsWith("ftp")){
                normalizedUrl = url;
            } else if(url.startsWith("www") || url.startsWith("www2")){
                normalizedUrl = protocol + "://" + url;
            } else {
                normalizedUrl = protocol + "://" + host + "/" + url;
            }
            processedUrls.add(normalizedUrl);
        }
        return processedUrls;
    }

    public void crawl(){

        String startingUrl = arguments.startingUrl;

        int hops = arguments.links;

        startingUrl = normalizeUrl("http", startingUrl);

        Queue<String> urlsQueue = new LinkedList<String>();
        urlsQueue.offer(startingUrl);
        Set<String> visitedUrls = new HashSet<String>();
        int step = 0;
        Map<String, Exception> errors = new HashMap<String, Exception>();
        boolean finishExtractingUrls = false;
        //the traversal algorithm is DFS
        long startTime = System.currentTimeMillis();
        while(!urlsQueue.isEmpty() && visitedUrls.size() < hops){
            ++step;
            String currentUrl = urlsQueue.poll();
            //apache logger is preferable
            System.out.println("Step [" + step + "], " +
                    "time spent so far [" + (System.currentTimeMillis() - startTime) + "] ms, " +
                    "visited size [" + visitedUrls.size() + "], " +
                    "visited+queue [" + (visitedUrls.size() + urlsQueue.size()) + "], " +
                    "url [" + currentUrl + "]");

            if(visitedUrls.contains(currentUrl)){
                continue;
            }
            visitedUrls.add(currentUrl);

            //heuristics: do not process if the number of urls is big
            if((visitedUrls.size() + urlsQueue.size()) > 10*hops){
                finishExtractingUrls = true;
            }
            if(finishExtractingUrls){
                continue;
            }

            List<String> urls;
            try {
                urls = extractUrlsFromUrl(currentUrl);
            } catch (Exception e) {
                errors.put(currentUrl, e);
                continue;
            }

            if(arguments.randomizeUrlTraversal){
                Random random = new Random(System.currentTimeMillis());
                while(!urls.isEmpty()){
                    int randIdx = Math.abs(random.nextInt()) % urls.size();
                    urlsQueue.offer(urls.get(randIdx));
                    urls.remove(randIdx);
                }
            } else {
                urlsQueue.addAll(urls);
            }
        }
        System.out.println("Processed..");
        System.out.println("    unique urls:" + visitedUrls.size());
        System.out.println("    successful urls:" + (visitedUrls.size()-errors.keySet().size()));
        System.out.println("    failed urls:" + errors.keySet().size());
        if(arguments.showMoreInfo){
            long totalTime = (System.currentTimeMillis()-startTime);
            System.out.println("    steps:" + step);
            System.out.println("    unprocessed urls:" + urlsQueue.size());
            System.out.println("    total time:" + totalTime + " ms");
            System.out.println("    avg time/url:" + (double)totalTime/visitedUrls.size() + " ms");
            System.out.println("    how much times visited already elaborated urls:" + (step-visitedUrls.size()));
            System.out.println("    total bytes downloaded:" + totalBytes);
            for(Map.Entry<String, Exception> errorEntry : errors.entrySet()){
                System.out.println("failed url [" + errorEntry.getKey() + "], cause [" + errorEntry.getValue() + "]");
            }
        }

    }

    public static void main(String[]args) {

        Args arguments = new Args();
        for(String arg : args){
            String[] optionAndValue = arg.split("=");
            if(optionAndValue.length != 2){
                System.err.println("Unable to process argument [" + arg + "]");
                help();
                return;
            }
            try {
                arguments.setArgument(optionAndValue[0], optionAndValue[1]);
            } catch (Exception ex){
                ex.printStackTrace(System.err);
                help();
                return;
            }
        }
        if(!arguments.isComplete()){
            System.err.println("Not enough arguments");
            help();
            return;
        }
        new Crawler(arguments).crawl();
    }

    private static void help(){
        System.out.println("crawler v0.1");
        System.out.println("-u=<url>                 starting url");
        System.out.println("-l=[links,def=1000]      number of unique urls to process");
        System.out.println("-r=[true,def=false]      randomize urls traversal");
        System.out.println("-cto=[ms,def=2500]       connection timeout, ms");
        System.out.println("-rto=[ms,def=2500]       read timeout, ms");
        System.out.println("-d=[true,def=false]      show additional statistics");
    }
}
