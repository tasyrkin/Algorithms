package structures.heap;

import java.util.ArrayList;

public class MyHeap implements Heap {

	Node rootNode = null;
	
	private static class Node{
		HeapElement element;
		ArrayList<Node> children = null;
		public Node(HeapElement he) {
			element = he;
			children = null;
		}
		
		public int compareTo(Node n){
			return this.element.compareTo(n.element);
		}
		
		public boolean equals(Object o){
			if(o != null && o instanceof Node){
				return this.element.equals(((Node)o).element);
			}
			return false;
		}
		
		public String toString(){
			StringBuffer sb = new StringBuffer(element.toString());
			if(children != null){
				sb.append("[");
				String comma = "";
				for(Node child : children){
					sb.append(comma+child.toString());
					if(comma.length()==0)comma=",";
				}
				sb.append("]");
			}
			return sb.toString();
		}
		
		public void insert(HeapElement he){
			if(element.compareTo(he)>0){
				Node toAdd = new Node(he);
				if(children==null){
					children = new ArrayList<Node>();
				}
				int i = 0;
				boolean added = false;
				for(Node child : children){
					if(toAdd.compareTo(child)>0){
						added = true;
						if(children.size()>0)children.add(i,toAdd);
						else children.add(toAdd);
						break;
					}
					else if(toAdd.compareTo(child)==0){
						added = true;
						break;
					}
					i++;
				}
				if(!added){
					children.add(toAdd);
				}
			}
		}
		
	}
	
	public HeapElement findMax(){
		return rootNode != null ? rootNode.element : null;
	}

	public HeapElement deleteMax(){
		if(rootNode == null)return null;
		if(rootNode.children == null){
			HeapElement returnedHE = rootNode.element;
			rootNode = null;
			return returnedHE;
		}
		Node maxNode = rootNode.children.get(0);
		rootNode.children.remove(maxNode);
		if(rootNode.children.size()==0)rootNode.children = null;
		if(maxNode.children!=null&&rootNode.children!=null)maxNode.children.addAll(rootNode.children);
		else if(maxNode.children==null&&rootNode.children!=null)maxNode.children = rootNode.children;
		HeapElement returnedHE = rootNode.element;
		rootNode = maxNode;
		return returnedHE;
	}

	public void increaseKey(){
		if(rootNode!=null)rootNode.element.increaseKey();
	}
	
	public void decreaseKey(){
		HeapElement he = this.deleteMax();
		if(he!=null){
			he.decreaseKey();
			this.insert(he);
		}
	}

	public void insert(HeapElement he){
		if(rootNode==null){
			rootNode = new Node(he);
			return;
		}
		if(rootNode.element.compareTo(he)>=0){
			rootNode.insert(he);			
		}
		else{
			Node maxNode = new Node(he);
			maxNode.children = new ArrayList<Node>();
			maxNode.children.add(rootNode);
			rootNode = maxNode;
		}
	}

	public void merge(MyHeap anotherHeap){
		HeapElement he = null;
		while((he = anotherHeap.deleteMax()) != null){
			this.insert(he);
		}
	}

	public String toString(){
		return rootNode != null ? rootNode.toString() : "";
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyHeap mh = new MyHeap();
//		for(int i=0;i<10;i++){
//			mh.insert(new MyHeapElement(i));
//		}
		for(int i=0;i<3;i++){
			mh.insert(new MyHeapElement(i));
		}
		MyHeap mh2 = new MyHeap();
		mh2.insert(new MyHeapElement(100));
		mh2.insert(new MyHeapElement(99));
		mh2.insert(new MyHeapElement(98));
		
		for(int i = 0; i< 50; i++)mh2.decreaseKey();
		
		System.out.println(mh.toString() + "\n");
		System.out.println(mh2.toString() + "\n");

		mh.merge(mh2);
		System.out.println(mh.toString() + "\n");
		System.out.println(mh2.toString());
//		mh.insert(new MyHeapElement(2));
//		mh.insert(new MyHeapElement(1));
//		mh.insert(new MyHeapElement(1));
		//System.out.println(mh);
	}


}
