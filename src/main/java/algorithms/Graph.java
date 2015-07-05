package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Graph {
	
	public static int universalSink(int[][] graph){
		
		int n = graph.length;
		int sink = 0;
		int count = 0;
		Set<Integer> visited = new HashSet<Integer>();
		int i = 0;
		int j = 0;
		for(i = 0; i < n; i++){
			visited.add(i);
			count = 0;
			for(j=0; j<n; j++){
				if(graph[i][j] == 1 && !visited.contains(j)){
					i = j-1;//j-1 for contains i++
					break;
				}
				else{
					if(graph[i][j] == 0){
						count++;
					}
				}
			}
			if(count == n){
				sink = i;
				break;
			}
		}		
		
		return sink;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<?>[] list = new ArrayList[5];
		list[1] = new ArrayList<Integer>();
		for(int i = 0; i < list.length; i++){
			System.out.println(list[i]);
		}
	}

}
