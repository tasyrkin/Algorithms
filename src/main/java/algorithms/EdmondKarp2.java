package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class EdmondKarp2 {

	public static int maxflow(int[][] capacity,
			Map<Integer, List<Integer>> graph, int s, int t) {
		int n = capacity.length;
		int maxFlow = 0;
		int[][] flowMatrix = new int[n][n];
		int[] parents = new int[n];
		while (true) {
			int flow = findAugmentingPathBFS(capacity, graph, flowMatrix,
					parents, s, t);
			if (flow == 0) {
				break;
			}
			maxFlow += flow;
			int v = t;
			while (v != s) {
				int u = parents[v];
				flowMatrix[u][v] += flow;
				flowMatrix[v][u] -= flow;
				v = u;
			}
		}

		return maxFlow;
	}

	private static int findAugmentingPathBFS(int[][] capacity,
			Map<Integer, List<Integer>> graph, int[][] flowMatrix,
			int[] parents, int s, int t) {
		Arrays.fill(parents, -1);
		parents[s] = -2;
		int[] flowTillVertex = new int[capacity.length];
		flowTillVertex[s] = Integer.MAX_VALUE;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			List<Integer> adjacentVertexes = graph.get(u);
			if (adjacentVertexes != null) {
				for (int v : adjacentVertexes) {
					if (capacity[u][v] - flowMatrix[u][v] > 0
							&& parents[v] == -1) {
						parents[v] = u;
						flowTillVertex[v] = Math.min(flowTillVertex[u],
								capacity[u][v] - flowMatrix[u][v]);
						if (v == t) {
							return flowTillVertex[t];
						} else {
							queue.add(v);
						}
					}
				}
			}
		}
		return 0;
	}

	public static Map<Integer, Integer> maximumMatching(
			Map<Integer, List<Integer>> graph, int n) {
		Map<Integer, Integer> matching = new HashMap<Integer, Integer>();
		int s = 0;
		int t = n + 1;
		int[][] capacity = new int[n + 2][n + 2];
		for (int i = 1; i < n / 2 + 1; i++) {
			capacity[s][i] = 1;
			capacity[i + n / 2][t] = 1;
		}
		Iterator<Integer> iter = graph.keySet().iterator();
		while (iter.hasNext()) {
			int u = iter.next();
			List<Integer> adjEdges = graph.get(u);
			if (adjEdges != null) {
				for (int v : adjEdges) {
					capacity[u + 1][v + 1] = 1;
				}
			}
		}

		Map<Integer, List<Integer>> newGraph = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < capacity.length; i++) {
			for (int j = 0; j < capacity[0].length; j++) {
				if (capacity[i][j] != 0) {
					List<Integer> adjVertices = newGraph.get(i);
					if (adjVertices == null) {
						adjVertices = new ArrayList<Integer>();
					}
					adjVertices.add(j);
					newGraph.put(i, adjVertices);
				}
			}
		}

		int[][] flowMatrix = new int[n + 2][n + 2];
		int[] parents = new int[n + 2];

		while (true) {
			int flow = findFlowBFS(capacity, newGraph, flowMatrix, parents, s,
					t);
			if (flow == 0) {
				break;
			}
			int v = t;
			while (v != s) {
				int u = parents[v];
				flowMatrix[u][v] += flow;
				flowMatrix[v][u] -= flow;
				if (u != s && v != t) {
					matching.put(u - 1, v - 1);
				}
				v = u;
			}
		}

		return matching;
	}

	private static int findFlowBFS(int[][] capacity,
			Map<Integer, List<Integer>> graph, int[][] flowMatrix,
			int[] parents, int s, int t) {
		Arrays.fill(parents, -1);
		parents[s] = -2;
		int[] flowTillVertex = new int[capacity.length];
		flowTillVertex[s] = Integer.MAX_VALUE;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			List<Integer> adjEdges = graph.get(u);
			if (adjEdges != null) {
				for (int v : adjEdges) {
					if (capacity[u][v] > flowMatrix[u][v] && parents[v] == -1) {
						parents[v] = u;
						flowTillVertex[v] = Math.min(flowTillVertex[u],
								capacity[u][v] - flowMatrix[u][v]);
						if (v == t)
							return flowTillVertex[v];
						else {
							queue.add(v);
						}
					}
				}
			}
		}
		return 0;
	}

	public static int[][] hungarianAlgorithm(int[][] costMatrix) {
		int n = costMatrix.length;
		Map<Integer, Integer> maximumMatcing = null;
		while (true) {

			for (int i = 0; i < n; i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < n; j++) {
					if (min > costMatrix[i][j])
						min = costMatrix[i][j];
				}
				for (int j = 0; j < n; j++) {
					costMatrix[i][j] -= min;
				}
			}
			for (int j = 0; j < n; j++) {
				int min = Integer.MAX_VALUE;
				for (int i = 0; i < n; i++) {
					if (min > costMatrix[i][j])
						min = costMatrix[i][j];
				}
				for (int i = 0; i < n; i++) {
					costMatrix[i][j] -= min;
				}
			}
			Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < n; i++) {
					if (costMatrix[i][j] == 0) {
						List<Integer> adjEdges = graph.get(i);
						if (adjEdges == null) {
							adjEdges = new ArrayList<Integer>();
						}
						adjEdges.add(j + n);
						graph.put(i, adjEdges);
					}
				}
			}
			maximumMatcing = maximumMatching(graph, 2 * n);
			Set<Integer> minimumCoverVertexes = new HashSet<Integer>();
			Iterator<Entry<Integer, Integer>> edgesIter = maximumMatcing.entrySet().iterator();
			while (edgesIter.hasNext()) {
				Entry<Integer, Integer> entry = edgesIter.next();
				minimumCoverVertexes.add(entry.getKey());
				minimumCoverVertexes.add(entry.getValue());
			}
			if (minimumCoverVertexes.size() == 2 * n) {
				break;
			}
			int delta = Integer.MAX_VALUE;
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(!minimumCoverVertexes.contains(i)&&!minimumCoverVertexes.contains(j+n)){
						if(delta>costMatrix[i][j]){
							delta = costMatrix[i][j];
						}
					}
				}
			}			
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(!minimumCoverVertexes.contains(i)&&!minimumCoverVertexes.contains(j+n)){
						costMatrix[i][j]-=delta;
					}
					else if(minimumCoverVertexes.contains(i)&&minimumCoverVertexes.contains(j+n)){						
					}
					else{
						costMatrix[i][j]+=delta;
					}
				}
			}
		}
		
		int[][]result = new int[n][n];
		Iterator<Entry<Integer, Integer>> edgesIter = maximumMatcing.entrySet().iterator();
		while (edgesIter.hasNext()) {
			Entry<Integer, Integer> entry = edgesIter.next();
			result[entry.getKey()][entry.getValue()-n] = 1;
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] costMatrix = new int[3][3];
		costMatrix[0][0] = 1;
		costMatrix[0][1] = 4;
		costMatrix[0][2] = 5;
		
		costMatrix[1][0] = 5;
		int[][] assignment = hungarianAlgorithm(costMatrix );
	}

	private static void textMaximumlMatching() {
		Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		int n = 6;
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		graph.put(0, list);
		list = new ArrayList<Integer>();
		list.add(3);
		list.add(4);
		list.add(5);
		graph.put(1, list);
		list = new ArrayList<Integer>();
		list.add(3);
		graph.put(2, list);
		Set<Integer> X = new HashSet<Integer>();
		X.add(0);
		X.add(1);
		X.add(2);
		Set<Integer> Y = new HashSet<Integer>();
		Y.add(3);
		Y.add(4);
		Y.add(5);

		Map<Integer, Integer> matching = maximumMatching(graph, n);
		Iterator<Entry<Integer, Integer>> entryIter = matching.entrySet()
				.iterator();
		Set<Integer> inMatching = new HashSet<Integer>();
		while (entryIter.hasNext()) {
			Entry<Integer, Integer> entry = entryIter.next();
			inMatching.add(entry.getKey());
			inMatching.add(entry.getValue());
		}
		if (inMatching.size() == n) {
			System.out.println("Matched!");
		} else {
			System.out.println("NOT Matched!");
		}
		Iterator<Integer> iter = matching.keySet().iterator();
		while (iter.hasNext()) {
			int x = iter.next();
			int y = matching.get(x);
			System.out.println(x + " - " + y);
		}
	}

	private static void testMaxFlow() {
		int[][] capacity = new int[6][6];
		Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		int s = 0;
		int t = 5;
		capacity[0][1] = 10;
		capacity[0][2] = 10;
		capacity[1][2] = 2;
		capacity[1][3] = 4;
		capacity[1][4] = 8;
		capacity[2][4] = 9;
		capacity[3][5] = 10;
		capacity[4][5] = 10;
		capacity[4][3] = 6;
		for (int i = 0; i < capacity.length; i++) {
			for (int j = 0; j < capacity[0].length; j++) {
				if (capacity[i][j] > 0) {
					List<Integer> adjEdges = graph.get(i);
					if (adjEdges == null) {
						adjEdges = new ArrayList<Integer>();
					}
					adjEdges.add(j);
					graph.put(i, adjEdges);
				}
			}
		}
		int maxFlow = maxflow(capacity, graph, s, t);
		System.out.println(maxFlow);
	}

}
