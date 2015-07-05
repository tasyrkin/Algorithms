package algorithms;

import java.util.HashSet;
import java.util.Set;

public class PriorityQueue {

	private int[] container;
	private int size;

	public PriorityQueue(Set<Integer> values) {
		makequeue(values);
	}

	public void makequeue(Set<Integer> values) {
		container = new int[values.size() + 1];
		int cnt = 1;
		for (Integer i : values) {
			container[cnt++] = i;
		}
		size = container.length - 1;
		int lastNonLeafIdx = getLastNonLeafIndex();
		for (int idx = lastNonLeafIdx; idx >= 1; idx--) {
			adjustNode(idx);
		}
	}

	private int getLastNonLeafIndex() {
		return size % 2 == 0 ? size / 2 : (size - 1) / 2;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}

	private void adjustNode(int idx) {

		int nodeValue = container[idx];

		boolean hasLeftChild = (idx * 2 <= size);
		int leftChild = hasLeftChild ? container[idx * 2] : -1;
		boolean hasRightChild = (idx * 2 + 1 <= size);
		int rightChild = hasRightChild ? container[idx * 2 + 1] : -1;

		if (!hasLeftChild && !hasRightChild) {
			return;
		}

		int maxChildValue = Math.max(leftChild, hasRightChild ? rightChild : leftChild);
		if (maxChildValue > nodeValue) {
			if (maxChildValue == leftChild) {
				container[idx] = leftChild;
				container[idx * 2] = nodeValue;
				adjustNode(idx * 2);
			} else if (hasRightChild && maxChildValue == rightChild) {
				container[idx] = rightChild;
				container[idx * 2 + 1] = nodeValue;
				adjustNode(idx * 2 + 1);
			}
		}
	}

	public int deletemin() {
		int res = container[1];
		container[1] = container[size--];
		adjustNode(1);
		return res;
	}

	public void insert(int value) {
		if (size + 1 < container.length) {
			int top = container[1];
			container[++size] = top;
			container[1] = value;
			int lastNonLeafIdx = getLastNonLeafIndex();
			for (int idx = lastNonLeafIdx; idx >= 1; idx--) {
				adjustNode(idx);
			}
		}
	}

	public static void main(String[] args) {
		Set<Integer> values = new HashSet<Integer>();
		values.add(1);
		values.add(2);
		values.add(5);
		values.add(10);
		PriorityQueue pq = new PriorityQueue(values);
		while(!pq.isEmpty()){
			System.out.println(pq.deletemin());
		}		
	}

}
