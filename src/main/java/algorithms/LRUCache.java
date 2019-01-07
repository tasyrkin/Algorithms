package algorithms;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Data> map;
    private final java.util.PriorityQueue<Data> heap;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity, expected a positive number");
        }
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.heap = new java.util.PriorityQueue<>(this.capacity, new DataCmp());
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    public int get(int key) {
        Data data = map.get(key);

        if (data != null) {
            heap.remove(data);
            data.timestamp = System.currentTimeMillis();
            heap.offer(data);
        }

        final Stack<Data> stack = new Stack<Data>();

        //stack.remove()

        return data != null ? data.value : -1;
    }

    public void put(int key, int value) {
        Data data = map.get(key);
        if (data != null) {
            data.value = value;
        } else {
            if (map.size() >= capacity) {
                Data removed = heap.poll();
                map.remove(removed.key);
            }
            final Data newData = new Data(key, value, System.currentTimeMillis());
            map.put(key, newData);
            heap.offer(newData);
        }
    }

    private static class Data {
        int key;
        int value;
        long timestamp;

        Data(int key, int value, long timestamp) {
            this.key = key;
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    private static class DataCmp implements Comparator<Data> {
        public int compare(Data d1, Data d2) {
            return Long.compare(d1.timestamp, d2.timestamp);
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
