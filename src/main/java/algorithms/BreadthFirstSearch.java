package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

	public static class Node{
		int value;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> graph = new HashMap<String,ArrayList<String>>();
		String v = "a";
		ArrayList<String> e = new ArrayList<String>();
		e.add("b");
		e.add("c");
		graph.put(v, e);
		v = "b";
		e = new ArrayList<String>();
		e.add("a");
		e.add("d");
		e.add("j");
		graph.put(v, e);
		v = "c";
		e = new ArrayList<String>();
		e.add("d");
		e.add("e");
		e.add("f");
		graph.put(v, e);
		HashMap<String, Integer> res = BFS(graph,"a");
		Iterator<String> keys = res.keySet().iterator();
		while(keys.hasNext()){
			String vertex = keys.next();
			Integer dist = res.get(vertex);
			System.out.print(vertex+":"+dist + " ");
		}
	}
	private static <T> HashMap<T,Integer> BFS(HashMap<T, ArrayList<T>> graph, T v){
		HashMap<T,Integer> res = new HashMap<T, Integer>();
		HashSet<T> visited = new HashSet<T>();
		Queue<T> queue = new LinkedList<T>();
		queue.add(v);
		visited.add(v);
		res.put(v, 0);
//		for(T e : queue){
//			System.out.print(e + " ");
//		}
//		System.out.println();
		while(queue.size()>0){
			T curr = queue.remove();
			ArrayList<T> siblings = graph.get(curr);
			Integer dist = res.get(curr);
			if(siblings!=null){
				for(T sib : siblings){
					if(!visited.contains(sib)){
						visited.add(sib);
						queue.add(sib);
						res.put(sib, dist+1);
					}
				}
			}
//			for(T e : queue){
//				System.out.print(e + " ");
//			}
//			System.out.println();
		}
		return res;
	}
	private static void BFS(HashMap<Integer, ArrayList<Integer>> graph, Integer v, Integer max) {
		char[] color = new char[max];
		for(int i = 0; i < color.length; i++)color[i] = 'W';
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		color[v] = 'G';
		for(Integer e : queue){
			System.out.print(e + " ");
		}
		System.out.println();
		while(queue.size()>0){
			Integer curr = queue.remove();
			ArrayList<Integer> siblings = graph.get(curr);
			if(siblings!=null){
				for(Integer sib : siblings){
					if(color[sib]=='W'){
						color[sib] = 'G';
						queue.add(sib);
					}
				}
			}
			color[curr] = 'B';
			for(Integer e : queue){
				System.out.print(e + " ");
			}
			System.out.println();
		}
	}

}
