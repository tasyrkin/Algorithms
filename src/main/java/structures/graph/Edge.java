package structures.graph;

public class Edge implements Comparable<Edge>{
	private Vertex from = null;
	private Vertex to = null;
	private int weight = -1;
	
	public Edge(int weight, Vertex from, Vertex to){
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
	public int getWeight() {
		return weight;
	}
	
	public Vertex getFrom() {
		return from;
	}
	public Vertex getTo() {
		return to;
	}
	
	@Override
	public int compareTo(Edge o) {
		int res = Math.abs(this.weight - o.weight);
		int cmp = this.weight - o.weight;
		if(cmp<0)
			return -res;
		return res;
	}	
	
	@Override
	public boolean equals(Object o){
		if(o != null && o instanceof Edge){
			Edge w = (Edge)o;
			return from.equals(w.from) && to.equals(w.to) && weight == w.weight; 
		}
		return false;
	}

	@Override
	public int hashCode(){
		int res = from.hashCode();
		res += to.hashCode();
		res += ((Integer)weight).hashCode();
		return res; 
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("("+getFrom().toString() + "->" + getTo().toString() + ":" + weight +")");
		return sb.toString();
	}
}
