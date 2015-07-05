package structures.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

	private static class Node<T> {
		T elem = null;
		Node<T> prev = null;
		Node<T> next = null;

		public Node(T elem, Node<T> prev, Node<T> next) {
			this.elem = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	private Node<T> header = new Node<T>(null, null, null);
	private Node<T> trailer = new Node<T>(null, null, null);
	private int size = 0;
	private long actualStructuralChanges = 0;

	public MyLinkedList() {
		header.next = trailer;
		trailer.prev = header;
	}

	public int size() {
		return size;
	}

	public void addFirst(T elem) {
		Node<T> newNode = new Node<T>(elem, header, header.next);
		header.next.prev = newNode;
		header.next = newNode;
		++size;
		++actualStructuralChanges;
	}

	public void addLast(T elem) {
		Node<T> newNode = new Node<T>(elem, trailer.prev, trailer);
		trailer.prev.next = newNode;
		trailer.prev = newNode;
		++size;
		++actualStructuralChanges;
	}

	public void add(T elem, int idx) {
		Node<T> tmp = header.next;
		while (tmp.next != null && idx > 0) {
			--idx;
			tmp = tmp.next;
		}
		if (idx != 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> newNode = new Node<T>(elem, tmp.prev, tmp);
		tmp.prev.next = newNode;
		tmp.prev = newNode;
		++size;
		++actualStructuralChanges;
	}

	public void remove(int idx) {
		Node<T> tmp = header.next;
		while (tmp.next != null && idx > 0) {
			--idx;
			tmp = tmp.next;
		}
		if (tmp.next == null || idx != 0) {
			throw new IndexOutOfBoundsException();
		}
		tmp.prev.next = tmp.next;
		tmp.next.prev = tmp.prev;
		--size;
		++actualStructuralChanges;
	}

	public void remove(T elem) {
		Node<T> tmp = header.next;
		while (tmp.next != null) {
			if (tmp.elem.equals(elem)) {
				tmp.prev.next = tmp.next;
				tmp.next.prev = tmp.prev;
				break;
			}
			tmp = tmp.next;
		}
		--size;
		++actualStructuralChanges;
	}

	@Override
	public String toString() {
		Node<T> tmp = header.next;
		boolean isFirst = true;
		StringBuffer sb = new StringBuffer("[");
		while (tmp.next != null) {
			sb.append((!isFirst ? "," : "") + tmp.elem);
			isFirst = false;
			tmp = tmp.next;
		}
		sb.append("]");
		return sb.toString();
	}

	public void removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		header.next.next.prev = header;
		header.next = header.next.next;
		--size;
		++actualStructuralChanges;
	}

	public void removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		trailer.prev.prev.next = trailer;
		trailer.prev = trailer.prev.prev;
		--size;
		++actualStructuralChanges;
	}

	private class MyLinkedListIterator implements Iterator<T> {

		private Node<T> current = header;
		private long expectedStructuralChanges = actualStructuralChanges;
		private boolean isNextCalled = false;

		@Override
		public boolean hasNext() {
			return current.next.next != null;
		}

		@Override
		public T next() {
			if (expectedStructuralChanges != actualStructuralChanges) {
				throw new ConcurrentModificationException();
			}
			if (current.next.next == null) {
				throw new NoSuchElementException();
			}
			T res = current.next.elem;
			current = current.next;
			isNextCalled = true;
			return res;
		}

		@Override
		public void remove() {
			if (!isNextCalled) {
				throw new IllegalStateException("next() was not called yet");
			}
			if (expectedStructuralChanges != actualStructuralChanges) {
				throw new ConcurrentModificationException();
			}
			isNextCalled = false;
			Node<T> saved = current.prev;
			MyLinkedList.this.remove(current.elem);
			current = saved;
			++expectedStructuralChanges;
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new MyLinkedListIterator();
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> mll = new MyLinkedList<Integer>();
		System.out.println(mll.toString());
		mll.add(0, 0);
		mll.add(1, 0);
		mll.add(2, 0);
		System.out.println(mll.toString());
		Integer i = 0;
		mll.remove(i);
		System.out.println(mll.toString());
		Iterator<Integer> iter = mll.iterator();
		while (iter.hasNext()) {
			Integer integer = iter.next();
			System.out.print(integer + " ");
			Iterator<Integer> iter1 = mll.iterator();
			System.out.print("iter1:"+iter1.next() +" ");
		}
		System.out.println("END");
	}

}
