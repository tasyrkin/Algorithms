package structures.heap;

public interface HeapElement {
	Integer getKey();
	Object getObject();
	int compareTo(HeapElement he);
	void increaseKey();
	void decreaseKey();
	String toString();
}
