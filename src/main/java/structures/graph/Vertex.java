package structures.graph;

import structures.disjointset.DisjointSetElement;


public class Vertex {
	
	private int id;
	private DisjointSetElement<Integer> dse;
	
	public Vertex(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public DisjointSetElement<Integer> getDse() {
		return dse;
	}
	
	public void setDse(DisjointSetElement<Integer> dse) {
		this.dse = dse;
	}
	
	@Override
	public boolean equals(Object o){
		if(o != null && o instanceof Vertex){
			Vertex v = (Vertex)o;
			return id == v.id; 
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		Integer i = id;
		return i.hashCode();
	}
	
	@Override
	public String toString(){
		return ""+id;
	}
	
	public static void main(String[]args){
	}
}
