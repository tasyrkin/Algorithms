package structures.graph;

import structures.disjointset.DisjointSetElement;
import structures.disjointset.DisjointSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {

	private int V;
	private List<Integer>[] E = null;
	private int[] color = null;
	private int time = 0;
	private int[] pre = null;
	private int[] post = null;
	private int[] predecessor = null;
	
	public Graph(int V){
		this.V = V;
		E = new ArrayList[V];
		color = new int[V];
		pre = new int[V];
		post = new int[V];
		predecessor = new int[V];
		for(int i = 0; i < V; i++){
			E[i] = new ArrayList<Integer>();			
		}
	}
	
	public void addEdge(int u, int v){
		E[u].add(v);
	}

	public int[] BFSDist(int s){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		Set<Integer> visited = new HashSet<Integer>();
		int[] res = new int[V];
		Arrays.fill(res, Integer.MAX_VALUE);
		res[s] = 0;
		while(!queue.isEmpty()){
			Integer vertex = queue.poll();
			visited.add(vertex);
			for(Integer v2 : E[vertex]){
				if(!visited.contains(v2)){
					res[v2] = res[vertex] + 1; 
					queue.add(v2);
				}
			}
		}
		
		return res;
	}

	public String BFS(int s){
		StringBuffer sb = new StringBuffer();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		Set<Integer> visited = new HashSet<Integer>();
		boolean isFirst = true;
		while(!queue.isEmpty()){
			Integer vertex = queue.poll();
			sb.append((isFirst?"":", ") + vertex);
			visited.add(vertex);
			for(Integer v2 : E[vertex]){
				if(!visited.contains(v2)){
					queue.add(v2);
				}
			}
			isFirst = false;
		}
		
		return sb.toString();
	}
	
	public boolean hasCycle(){
		Arrays.fill(color, 0);
		Arrays.fill(predecessor, -1);
		for(int vertex = 0; vertex < V; vertex++){
			if(color[vertex] == 0){
				if(hasCycle(vertex)){
					return true; 
				}
			}
		}
		return false;
	}
	
	private boolean hasCycle(int vertex) {
		color[vertex] = 1;
		for(Integer child : E[vertex]){
			if(color[child]==0){
				predecessor[child] = vertex;
				if(hasCycle(child)){
					return true;
				}
//			} else {
//				int currVertex = vertex;
//				while(currVertex!=-1){
//					if(child == predecessor[currVertex]){
//						return true;
//					}
//					currVertex = predecessor[currVertex];
//				}
			} else if(color[child] == 1) { 
				return true;
			}
		}
		color[vertex] = 2;
		return false;
	}

	public void DFS(){
		Arrays.fill(color, 0);
		for(int i = 0; i < V; i++){
			if(color[i] == 0){
				DFSVisit(i);
			}
		}
	}
	
	private void DFSVisit(int i) {
		color[i] = 1;
		pre[i] = ++time;
		for(Integer child : E[i]){
			if(color[child] == 0){
				DFSVisit(child);
			}
		}
		color[i] = 2;
		post[i] = ++time;
	}
	
	public void printTimes(){
		for(int i = 0; i < V; i++){
			System.out.print(i+"="+(pre[i] + ":" + post[i]) + ";");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Graph g = new Graph(5);
//		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
//		g.addEdge(1, 0);
		g.addEdge(1, 4);
		g.addEdge(2, 1);
		g.addEdge(3, 1);
//		g.addEdge(4, 0);
//		String bfs = g.BFS(0);
//		System.out.println(g.BFS(0));
//		System.out.println(g.BFS(2));
		g.DFS();
		g.printTimes();
//		showOrderedByDistance(g);
		System.out.println("has cycle:" + g.hasCycle());
;	}

	public static void showOrderedByDistance(Graph g) {
		int[]dist = g.BFSDist(3);
		Map<Integer, List<Integer>>map = new HashMap<Integer, List<Integer>>();
		for(int v = 0; v < dist.length; v++){
			List<Integer> list = map.get(dist[v]);
			if(list == null){
				list = new ArrayList<Integer>();
			}
			list.add(v);
			map.put(dist[v], list);
		}
		Integer[]dists = map.keySet().toArray(new Integer[map.keySet().size()]);
		Arrays.sort(dists);
		for(Integer d : dists){
			boolean isFirst = true;
			StringBuffer sb = new StringBuffer();
			List<Integer> list = map.get(d);
			for(Integer vertex : list){
				sb.append((isFirst?"":",") + vertex);
				isFirst = false;
			}
			System.out.print(d + ":" + sb.toString() + "; ");
		}
	}
	
	public Set<Edge> mstKruskal(){
		Set<Edge> mst = new HashSet<Edge>();
		int numOfEdges = 0;
		for(int i = 0; i < edges.length; i++){
			numOfEdges+=edges[i].size();
		}
		Edge[]arrayEdges = new Edge[numOfEdges];
		int edjCnt = 0;
		for(int i = 0; i < edges.length; i++){
			for(Edge w : edges[i]){
				arrayEdges[edjCnt++] = w;
			}
		}
		Arrays.sort(arrayEdges);
		for(Edge e : arrayEdges){
			DisjointSetElement<Integer> dsFrom = e.getFrom().getDse();
			DisjointSetElement<Integer> dsTo = e.getTo().getDse();
			//if(!disjointSets.findSet(dsFrom).equals(disjointSets.findSet(dsTo))){
			//	mst.add(e);
			//	disjointSets.union(dsFrom, dsTo);
			//}
		}
		return mst;
	}
	
	public static class SPEstimate implements Comparable<SPEstimate>{
		int dist = -1;
		int vertex = -1;
		int predecessor = -1;
		public SPEstimate(int dist, int vertex, int predecessor){
			this.dist = dist;
			this.vertex = vertex;
			this.predecessor = predecessor;
		}
		@Override
		public int compareTo(SPEstimate o) {
			return this.dist - o.dist;
		}
		
		@Override
		public String toString() {
			return vertex + ":" + dist + "(" + predecessor + ")";
		}
	}

	private List<Edge>[] edges = null;
	private Vertex[] vertexes = null;
	private DisjointSets<Integer> disjointSets = null;
	private int v;
	
	public Graph(int n, Vertex[] vertexes, DisjointSets<Integer> ds){
		this.v = n;
		edges = new List[n];
		for(int i = 0; i < n; i++){
			edges[i] = new ArrayList<Edge>(); 
		}
		this.vertexes = vertexes;
		this.disjointSets = ds;
	}
	
	public void addEdge(Vertex u, Vertex v, int weight){
		edges[u.getId()].add(new Edge(weight, u, v));
	}
	
	public int[] dijkstra(int source){
		int[]d = new int[v];
		int[]pred = new int[v];
		for(int i = 0; i < d.length; i++){
			d[i] = Integer.MAX_VALUE;
			pred[i] = -1;
		}
		Set<Integer> traversed = new HashSet<Integer>();
		d[source] = 0;
		while(traversed.size()<v){
			int min = Integer.MAX_VALUE;
			int vertex = -1;
			for(int i = 0; i < v; i++){
				if(!traversed.contains(i)){
					if(min>d[i]){
						min = d[i];
						vertex = i;
					}
				}
			}
			if(vertex==-1)break;
			traversed.add(vertex);
			for(Edge e : edges[vertex]){
				if(d[e.getTo().getId()] > d[e.getFrom().getId()] + e.getWeight()){
					d[e.getTo().getId()] = d[e.getFrom().getId()] + e.getWeight();
					pred[e.getTo().getId()] = e.getFrom().getId();
				}
			}
		}
		return d;
	}


}
