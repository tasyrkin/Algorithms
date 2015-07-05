package algorithms.coursera.algo2.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tim
 * Date: 12/16/12
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrimAlgorithm {

    private static class EdgeEnd{
        int node;
        int cost;
        EdgeEnd(int n, int c){
            node = n;
            cost = c;
        }
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split("\\s+");

        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        List<EdgeEnd>[] graph = new List[n];

        for(int i = 0; i < m; i++){
            parts = br.readLine().split("\\s+");
            int start = Integer.parseInt(parts[0])-1;
            int end = Integer.parseInt(parts[1])-1;
            int cost = Integer.parseInt(parts[2]);

            if(graph[start] == null){
                graph[start] = new ArrayList<EdgeEnd>();
            }
            graph[start].add(new EdgeEnd(end, cost));

            if(graph[end] == null){
                graph[end] = new ArrayList<EdgeEnd>();
            }
            graph[end].add(new EdgeEnd(start, cost));
        }

        Set<Integer> visited = new HashSet<Integer>();
        visited.add(0);

        int totalCost = 0;

        while(visited.size() != n){

            int minCost = Integer.MAX_VALUE;
            int nodeToPickUp = -1;
            int checkedNode = -1;

            //System.out.println("Visited nodes: " + Arrays.toString(visited.toArray(new Integer[0])));

            for(Integer currNode : visited){

                //System.out.println("checking node [" + currNode + "]...");

                List<EdgeEnd> neighbours = graph[currNode];

                if(neighbours == null)continue;

                for(EdgeEnd ee : neighbours){
                    //System.out.println("checking neighbour [" + ee.node + "] with cost [" + ee.cost + "]...");
                    if(!visited.contains(ee.node) && minCost > ee.cost){
                        checkedNode = currNode;
                        minCost = ee.cost;
                        nodeToPickUp = ee.node;
                    }
                }
            }

            if(nodeToPickUp == -1){
                throw new IllegalStateException("Node cannot be negative");
            }

            //System.out.println("Node [" + checkedNode + "] with neighbour [" + nodeToPickUp + "] with cost [" + minCost + "]...");

            totalCost += minCost;
            visited.add(nodeToPickUp);
        }

        System.out.println(totalCost);

    }
}
