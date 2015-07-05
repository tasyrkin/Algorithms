package algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondKarp {

	public static int maxFlow(int[][] capacity, int[][] graph, int s, int t) {
		int n = capacity.length;
		int[][] flowMatrix = new int[n][n];
		int maxFlow = 0;
		int[] parents = new int[n];
		while (true) {
			int flow = findAugmentingPathFlowBFS(capacity, flowMatrix, graph, s, t, parents);
			if(flow==0)break;
			maxFlow += flow;
			int v = t;
			while(v!=s){
				int u = parents[v];
				flowMatrix[u][v] += flow;
				flowMatrix[v][u] -= flow;
				v = u;
			}
		}
		return maxFlow;
	}

	private static int findAugmentingPathFlowBFS(int[][] capacity, 
			int[][] flowMatrix, int[][] graph, int s, int t, int[] parents) {
		Arrays.fill(parents, -1);
		parents[s] = -2;
		int[] pathCapacity = new int[capacity.length];
		pathCapacity[s] = Integer.MAX_VALUE;
	
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		while(!queue.isEmpty()){
			int u = queue.poll();
			for(int v = 0; v < capacity.length; v++){
				if(graph[u][v]==1){
					if(parents[v]==-1&&capacity[u][v]-flowMatrix[u][v]>0){
						parents[v] = u;
						pathCapacity[v] = Math.min(pathCapacity[u], capacity[u][v]-flowMatrix[u][v]);
						if(v!=t){
							queue.add(v);
						}
						else{
							return pathCapacity[v];
						}
					}
				}
			}
		}		
		return 0;
	}

	public static void main(String[] args) {
		int[][] capacity = {{0,100},{0,0}};
		int[][] graph = {{0,1},{0,0}};
		int s = 0;
		int t = 1;
		int flow = EdmondKarp.maxFlow(capacity, graph, s, t);
		System.out.println("flow:" + flow);
	}

}
