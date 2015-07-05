package structures.heap;


public interface Heap {
	public HeapElement findMax();
	public HeapElement deleteMax();
	public void increaseKey();	
	public void decreaseKey();
	public void insert(HeapElement he);
	public void merge(MyHeap anotherHeap);
}
