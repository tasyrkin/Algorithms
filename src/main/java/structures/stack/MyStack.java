package structures.stack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class MyStack<T> {
	private List<T> list = new ArrayList<T>();

	public void push(T elem) {
		list.add(0, elem);
	}

	public T pop() {
		if (list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(0);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public static void main(String[] args) {
		MyStack<Integer> mystack = new MyStack<Integer>();
		for (int cnt = 10; cnt >= 0; cnt--) {
			mystack.push(cnt);
		}
		while (!mystack.isEmpty()) {
			System.out.print(mystack.pop() + " ");
		}
	}
}
