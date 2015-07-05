package structures.queue;

import java.util.ArrayList;
import java.util.List;

public class MyQueue<T> {
	private List<T> list = new ArrayList<T>();

	public void queue(T elem) {
		list.add(elem);
	}

	public T dequeue() {
		if (list.isEmpty()) {
			throw new RuntimeException();
		}
		return list.remove(0);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public static void main(String[] args) {
		MyQueue<Integer> mystack = new MyQueue<Integer>();
		for (int cnt = 10; cnt >= 0; cnt--) {
			mystack.queue(cnt);
		}
		while (!mystack.isEmpty()) {
			System.out.print(mystack.dequeue() + " ");
		}
	}
}
