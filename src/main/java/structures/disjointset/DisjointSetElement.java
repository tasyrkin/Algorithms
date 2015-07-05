package structures.disjointset;

public class DisjointSetElement<T> {
	private T data;
	private DisjointSet<T> djset = null;
	
	DisjointSetElement(T data, DisjointSet<T> djset){
		this.data = data;
		this.djset = djset;
	}
	
	DisjointSet<T> getDisjointSet(){
		return djset;
	}
	void setDisjointSet(DisjointSet<T> djset){
		this.djset = djset;
	}
	
	public T getData(){
		return data;
	}
	
	@Override
	public boolean equals(Object o){
		if(o != null && o instanceof DisjointSetElement){
			DisjointSetElement<T> dse = (DisjointSetElement<T>)o;
			Comparable<T> cmp1 = (Comparable<T>)data; 
			return cmp1.compareTo(dse.data)==0;
		}
		return false;
	}
}
