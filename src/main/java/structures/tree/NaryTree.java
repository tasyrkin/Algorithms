package structures.tree;

public class NaryTree {
	
	private Integer N = -1;
	private Node root = null;
	
	public static class Node{
		Integer[]keys;
		Node[]children;
		Node parent;
		
		public Node(int n, Node parent){
			keys = new Integer[n-1];
			children = new Node[n];
			this.parent = parent;
		}
	}
	
	public NaryTree(int N){
		this.N = N;
	}
	
	public void add(Integer elem){
		if(root == null){
			root = new Node(N, null);
			root.keys[0] = elem;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
