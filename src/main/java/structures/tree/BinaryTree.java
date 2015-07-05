package structures.tree;

import java.util.Comparator;

public class BinaryTree {
    /*
	public static class Node<T> {
		private T elem;
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;

		Node(Integer elem, Node parent, Node left, Node right) {
			this.elem = elem;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		public Node<T> parent() {
			return parent;
		}

		public boolean hasLeft() {
			return left != null;
		}

		public Node<T> left() {
			return left;
		}

		public boolean hasRight() {
			return right != null;
		}

		public Node<T> right() {
			return right;
		}
	}

	private Node<T> root = null;
	private Comparator<T> comparator = null;

	private Node createRootNode(Integer elem) {
		return new Node(elem, null, null, null);
	}

	public BinaryTree(T rootData, Comparator<T> comparator) {
		root = new Node<T>(rootData, null, null, null);
		this.comparator = comparator;
	}

	public Node<T> root() {
		return root;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void add(T elem) {
		Node<T> curr = root;
		Node<T> parent = null;
		while (curr != null) {
			parent = curr.parent;
			if (compare(elem, curr.elem) <= 0) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		if (parent == null) {
			root = new Node<T>(elem, null, null, null);
		} else {
			if (compare(elem, parent.elem) <= 0) {
				parent.left = new Node<T>(elem, parent, null, null);
			} else {
				parent.right = new Node<T>(elem, parent, null, null);
			}
		}
	}
	
	public Node<T> minimum(Node<T> node){
		Node<T> curr = node;
		while(curr.hasLeft()){
			curr = curr.left;
		}
		return curr;
	}

	public Node<T> sucessor(Node<T> node){
		if(node.hasRight()){
			return minimum(node.right);
		}
		Node<T> parent = node.parent;
		while(parent!=null && parent.right==node){
			node = parent;
			parent = node.parent;
		}
		return parent;
	}
	
	public void delete(Node<T> node){
		
	}
	
	private int compare(T elem, T elem2) {
		if (comparator != null) {
			return comparator.compare(elem, elem2);
		}
		Comparable<T> elemCmp = (Comparable<T>) elem;
		return elemCmp.compareTo(elem2);
	}
	
	public boolean search(int elem){
		return searchRecursive(root,elem)!=null?true:false;
	}

	private Node searchRecursive(Node node, int elem) {
		if(node == null || node.elem == elem){
			return node;
		}
		if(elem < node.elem){
			searchRecursive(node.left, elem);
		} 
		return searchRecursive(node.right, elem);
	}

	public void add(Integer element) {
		if (isEmpty()) {
			root = this.createRootNode(element);
			return;
		}
		Node current = root;
		Node saved = null;
		while(current != null){
			saved = current;
			if(element <= saved.elem){
				if(hasLeft(saved)){
					current = current.left;					
				} else {
					current = null;
				}
			} else {
				if(hasRight(saved)){
					current = current.right;
				} else {
					current = null;
				}
			}
		}
		if(saved == null) {
			root = createRootNode(element);
		} else {
			if(element <= saved.elem) {
				Node node = new Node(element, saved, null, null);
				saved.left = node;
			} else {
				Node node = new Node(element, saved, null, null);				
				saved.right = node;
			}
		}
	}
	

	 * TODO: finish function implementation
	 * @param element
	 * @return

	public boolean removeCormen(Integer element){
		Node found = searchRecursive(root, element);
		if(found == null)return false;
		if(found.left == null && found.right == null){
			if(found.parent == null){
				root = null;
				return true;
			}
			if(found.parent.left == found){
				found.parent.left = null;
			}
			else if(found.parent.right == found){
				found.parent.right = null;
			}
			return true;
		}
		if(found.left != null || found.right != null){
			Node remaining = found.left != null ? found.left : found.right;
			if(found.parent == null){
				
			}
		}
		return false;
	}
	
	public boolean remove(Integer element) {
		if(isEmpty())return false;
		Node found = root;
		while(found != null) {
			if(element == found.elem){
				break;
			}
			if(element <= found.elem){
				if(hasLeft(found)) {
					found = found.left;
				} else {
					found = null;
				}
			} 
			else if(element >= found.elem){
				if(hasRight(found)){
					found = found.right;
				} else {
					found = null;
				}
			}
		}
		if(found == null)return false;
		//a. found does not have any child
		if(!hasLeft(found) && !hasRight(found)){
			if(found.parent == null){
				root = null;
				return true;
			}
			if(element <= found.parent.elem){
				found.parent.left = null;
			} else if(element >= found.parent.elem) {
				found.parent.right = null;
			}
			return true;
		}
		//b. found has both children
		if(hasLeft(found) && hasRight(found)){
			if(found.parent == null){
				root = found.left;
				root.parent = null;			
			} else if(element <= found.parent.elem){
				found.parent.left = found.left;
			} else if(element >= found.parent.elem) {
				found.parent.right = found.left;
			}
			Node current = found.left;
			while(hasRight(current)){
				current = current.right;
			}
			current.right = found.right;
			return true;			
		}
		//c. found has only right child
		if(hasRight(found)){
			if(found.parent == null){
				root = found.right;
				root.parent = null;
				return true;
			}
			if(element <= found.parent.elem){
				found.parent.left = found.right;
			} else if(element >= found.parent.elem) {
				found.parent.right = found.right;
			}
			return true;			
		}
		//d. found has only left child
		if(found.parent == null){
			root = found.left;
			root.parent = null;
			return true;
		}
		if(element <= found.parent.elem){
			found.parent.left = found.left;
		} else if(element >= found.parent.elem) {
			found.parent.right = found.left;
		}
		return true;			
	}
	
	public void traverseInOrder(){
		if(root == null){
			System.out.println("Tree is empty");
		} else {
			traverseInOrderInternal(root, 0);
		}
	}

	private void traverseInOrderInternal(Node node, int level) {		
		if(node == null)return;
		if(hasLeft(node)){
			traverseInOrderInternal(node.left, level+1);
		} 
		System.out.println("element=" + node.elem + ", level=" + level);
		if(hasRight(node)){
			traverseInOrderInternal(node.right, level+1);
		}
	}
	
	public int height(){
		if(isEmpty())return 0;
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(root, 0));
		int res = Integer.MIN_VALUE;
		while(!queue.isEmpty()){
			Pair p = queue.poll();
			if(p.level > res) res = p.level;
			if(hasLeft(p.node)){
				queue.add(new Pair(p.node.left, p.level+1));
			} 
			if(hasRight(p.node)){
				queue.add(new Pair(p.node.right, p.level+1));				
			}
		}
		return res;
	}

	private static class Pair{
		Node node;
		int level;
		Pair(Node node, int level){
			this.node = node;
			this.level = level;
		}
	}
	

	public static void main(String[] args) {

	}
	*/

}
