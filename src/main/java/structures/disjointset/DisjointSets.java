package structures.disjointset;

public class DisjointSets<T> {
	
	public DisjointSet<T> makeSet(T data){
		return new DisjointSet<T>(data);
	}
	public DisjointSet<T> findSet(DisjointSetElement<T> dsElement){
		return dsElement.getDisjointSet();
	}
	public DisjointSet<T> union(DisjointSetElement<T> x, DisjointSetElement<T> y){
		return x.getDisjointSet().union(y.getDisjointSet());
	}
}
