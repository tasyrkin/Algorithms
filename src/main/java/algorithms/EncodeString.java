package algorithms;

import java.util.HashMap;
import java.util.Map;

public class EncodeString {
    /**
     * abaa -> a1b1a2
     */
    public String encode(String s){
        if(s == null || s.length() == 0){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int next = 1;
        while(next < s.length()){
            if(s.charAt(next) != s.charAt(start)){
                sb.append(s.charAt(start));
                sb.append(next - start);
                start = next;
            }
            next++;
        }
        if(start != next){
            sb.append(s.charAt(start));
            sb.append(next - start);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new EncodeString().encode("abaa"));
        System.out.println(new EncodeString().encode("a"));
        System.out.println(new EncodeString().encode(""));
        System.out.println(new EncodeString().encode("aaabaaa"));
    }
}
