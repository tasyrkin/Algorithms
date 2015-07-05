package structures.disjointset;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet<T> {
	
	private List<DisjointSetElement<T>> list = null;
	
	public DisjointSet(T data){
		DisjointSetElement<T> dse = new DisjointSetElement<T>(data, this);
		list = new ArrayList<DisjointSetElement<T>>();
		list.add(dse);
	}
	
	public DisjointSetElement<T> getRepresentative(){
		return list.get(0);
	}
	
	public DisjointSet<T> union(DisjointSet<T> set){
		for(DisjointSetElement<T> dse : set.list){
			dse.setDisjointSet(this);
			list.add(dse);
		}
		return this;
	}
	
	@Override
	public boolean equals(Object o){
		if(o != null && o instanceof DisjointSet){
			DisjointSet<T> ds = (DisjointSet)o;
			return this.getRepresentative().equals(ds.getRepresentative());
		}
		return false;
	}
}
