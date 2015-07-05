package algorithms.coursera.algo2.assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oksana
 * Date: 12/23/12
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClusteringSingleLinkHard {
    private static class Node{
        int lead;
        int count = 1;
        int num = 0;
        public Node(int lead, int num){
            this.lead = lead;
            this.num = num;
        }
    }
    private static class Edge{
        Node from;
        Node to;

        public Edge(Node from, Node to){
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("clustering2.txt")));

        String[]parts = br.readLine().split("\\s+");
        int N = Integer.parseInt(parts[0]);
        int n = Integer.parseInt(parts[1]);
        Node[]nodes = new Node[N];
        Set<Integer>[]existDiff = new HashSet[n+1];
        int[]diffs = new int[n+1];
        for(int i = 0; i < existDiff.length; i++){
            existDiff[i] = new HashSet<Integer>();
        }


        for(int i = 0; i < N; i++){
            System.out.println("i=" + i);
            String numStr = br.readLine();
            int num = Integer.valueOf(numStr.replaceAll("\\s+", ""), 2);
            nodes[i] = new Node(i, num);
            for(int j = i - 1; j >= 0; j--){
                int diff = diff(nodes[i], nodes[j]);
                existDiff[diff].add(i);
                existDiff[diff].add(j);
                diffs[diff]++;
            }
        }

        for(int i = 0; i < diffs.length; i++){
            System.out.println("dist=" + i + ", num=" + diffs[i]);
        }

        int numOfClusters = N;

        List<Node> nodesInSameCluster = new ArrayList<Node>();

        System.out.println("Before main cycle");
        while(true){
            int min = Integer.MAX_VALUE;
            int currMin = 0;
            boolean found = false;
            int from = -1;
            int to = -1;
            long s = System.currentTimeMillis();
            for(int i = 0; i < N; i++){
                if(!existDiff[currMin].contains(i) && !existDiff[currMin+1].contains(i)){
                    continue;
                }
                for(int j = i-1; j >= 0; j--){
                     if(nodes[i].lead != nodes[j].lead){
                        int diff = diff(nodes[i], nodes[j]);
                        if(min > diff){
                            min = diff;
                            from = i;
                            to = j;
                            if(currMin == min){
                                found = true;
                                break;
                            }
                        }
                     }
                }
            }
            if(!found){
                ++currMin;
            }
            if(min == Integer.MAX_VALUE){
                System.out.println("Skipped");
                continue;
            }
            if(min >= 3){
                break;
            }
            System.out.println(min + ", i = " + from + ", j = "+ to +", time:" + (System.currentTimeMillis() - s));
            Node lead1 = nodes[nodes[from].lead];
            Node lead2 = nodes[nodes[to].lead];
            int leadToSearch = -1;
            int leadToReplace = -1;
            if(lead1.count > lead2.count){
                leadToSearch = lead2.lead;
                leadToReplace = lead1.lead;
                lead1.count += lead2.count;
            } else {
                leadToSearch = lead1.lead;
                leadToReplace = lead2.lead;
                lead2.count += lead1.count;
            }

            for(int i = 0; i < nodes.length; i++){
                if(nodes[i].lead == leadToSearch){
                    nodes[i].lead = leadToReplace;
                }
            }
            --numOfClusters;
        }
        System.out.println(numOfClusters);
    }

    private static short diff(Node n1, Node n2){
        int diff = n1.num ^ n2.num;
        short cnt = 0;
        for(int k = 0; k <= 23; k++){
            cnt += (diff >> k) & 1;
        }
        return cnt;
    }
}
