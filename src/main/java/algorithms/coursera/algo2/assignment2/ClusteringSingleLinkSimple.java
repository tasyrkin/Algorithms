package algorithms.coursera.algo2.assignment2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oksana
 * Date: 12/23/12
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClusteringSingleLinkSimple {

    private static final int K = 4;

    private static class Node{
        int lead;
        int count = 1;
        public Node(int lead){
            this.lead = lead;
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
            return (this.cost-o.cost);
        }
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("clustering1.txt")));
        int n = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[n];
        List<Edge> edges = new ArrayList<Edge>(n*n);
        String[]parts = null;
        String line = null;
        while(null != (line = br.readLine())){
            parts = line.split("\\s+");
            int fromIdx = Integer.parseInt(parts[0])-1;
            int toIdx = Integer.parseInt(parts[1])-1;
            int cost = Integer.parseInt(parts[2]);

            if(nodes[fromIdx] == null){
                nodes[fromIdx] = new Node(fromIdx);
            }
            if(nodes[toIdx] == null){
                nodes[toIdx] = new Node(toIdx);
            }
            Edge e1 = new Edge(nodes[fromIdx], nodes[toIdx], cost);
            //Edge e2 = new Edge(nodes[toIdx], nodes[fromIdx], cost);
            edges.add(e1);
            //edges.add(e2);
        }
        Collections.sort(edges);
        System.out.println("edges.size=" + edges.size());
        int nOfClusters = n;

        int res = -1;
        for(Edge edge : edges){
            if(edge.from.lead == edge.to.lead){
                continue;
            }
            if(K == nOfClusters){
                res = edge.cost;
                break;
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
            --nOfClusters;
        }

        System.out.println(res);
    }

}
