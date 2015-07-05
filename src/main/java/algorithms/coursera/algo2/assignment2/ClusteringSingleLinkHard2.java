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
public class ClusteringSingleLinkHard2 {
    private static class Node{
        int lead;
        int count = 1;
        int num = 0;
        public Node(int lead, int num){
            this.lead = lead;
            this.num = num;
        }
    }
    private static class Edge implements  Comparable<Edge>{
        Node from;
        Node to;
        int cost;

        public Edge(Node from, Node to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("clustering2.txt")));

        String[]parts = br.readLine().split("\\s+");
        int N = Integer.parseInt(parts[0]);
        int n = Integer.parseInt(parts[1]);

        List<Edge> edges = new ArrayList<Edge>(N);

        Node[]nodes = new Node[N];
        for(int i = 0; i < N; i++){
            System.out.println("node=" + i);
            String numStr = br.readLine();
            int num = Integer.valueOf(numStr.replaceAll("\\s+", ""), 2);
            nodes[i] = new Node(i, num);
            for(int j = i - 1; j >= 0; j--){
                int diff = diff(nodes[i], nodes[j]);
                if(diff <= 2){
                    edges.add(new Edge(nodes[i], nodes[j], diff));
                }
            }
        }

        Collections.sort(edges);

        int numOfClusters = N;

        int res = -1;
        for(Edge edge : edges){
            if(edge.from.lead == edge.to.lead){
                continue;
            }
            Node lead1 = nodes[edge.from.lead];
            Node lead2 = nodes[edge.to.lead];
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
