package structures.heap;

import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: tim
 * Date: 12/7/12
 * Time: 8:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinAndMaxHeaps {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>();
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

    public void offerMin(int element){
        minHeap.offer(element);
    }
    public void offerMax(int element){
        maxHeap.offer(-element);
    }
    public int pollMin(){
        return minHeap.poll();
    }

    public int pollMax(){
        return -maxHeap.poll();
    }

    public int peekMin(){
        return minHeap.peek();
    }

    public int peekMax(){
        return -maxHeap.peek();
    }

    public int minSize(){
        return minHeap.size();
    }

    public boolean isMinEmpty(){
        return minSize() == 0;
    }

    public int maxSize(){
        return maxHeap.size();
    }

    public boolean isMaxEmpty(){
        return maxSize() == 0;
    }

}
