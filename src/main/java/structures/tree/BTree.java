package structures.tree;

public class BTree<T> {
	private int dimension;
	private Node root = null;
	private static class Node {
		private Integer[] keys;
		private Node[] children;
		private Integer value;
		public Node(int dimension){
			keys = new Integer[2*dimension];
			children = new Node[2*dimension+1];
		}
		public Node(Integer value){
			this.value = value;
		}
	}
	public BTree(int dimension){
		this.dimension = dimension;
	}
	public void add(Integer elem){		
	}
	public void remove(Integer elem){		
	}
	public boolean find(Integer elem){
		return false;
	}
	
}
