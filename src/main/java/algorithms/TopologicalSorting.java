package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Lists;

public class TopologicalSorting {
    public LinkedList<Integer> sort(final Map<Integer, List<Integer>> graph) {
        if (graph == null) {
            return Lists.newLinkedList();
        }

        Map<Integer, Integer> counts = getCounts(graph);
        Queue<Integer> queue = new LinkedList<>();

        for (Integer vertex : counts.keySet()) {
            int cnt = counts.get(vertex);
            if (cnt == 0) {
                queue.add(vertex);
            }
        }

        final LinkedList<Integer> sorted = new LinkedList<>();
        while (!queue.isEmpty()) {
            Integer currVertex = queue.poll();
            sorted.add(currVertex);

            List<Integer> neighbours = graph.get(currVertex);
            if (neighbours != null) {
                for (Integer neighbour : neighbours) {
                    Integer neighboursCount = counts.get(neighbour);
                    counts.put(neighbour, --neighboursCount);
                    if (neighboursCount == 0) {
                        queue.add(neighbour);
                    }
                }
            }
        }

        if (sorted.size() != graph.keySet().size()) {
            throw new IllegalStateException("There is a cycle in the graph");
        }

        return sorted;
    }

    private Map<Integer, Integer> getCounts(final Map<Integer, List<Integer>> graph) {

        final Map<Integer, Integer> counts = new HashMap<>();

        Set<Integer> visited = new HashSet<>();

        for (Integer vertex : graph.keySet()) {
            if (visited.contains(vertex)) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(vertex);
            while (!queue.isEmpty()) {
                Integer currentNode = queue.poll();

                if (counts.get(currentNode) == null) {
                    counts.put(currentNode, 0);
                }

                if (visited.contains(currentNode)) {
                    continue;
                }

                visited.add(currentNode);

                List<Integer> neighbours = graph.get(currentNode);
                if (neighbours != null) {
                    for (Integer neighbour : neighbours) {
                        Integer cnt = counts.get(neighbour);
                        if (cnt == null) {
                            cnt = 0;
                        }

                        counts.put(neighbour, cnt + 1);
                        queue.offer(neighbour);
                    }
                }
            }

        }

        return counts;

    }

    public static void main(final String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Lists.newArrayList(2, 5));
        graph.put(2, Lists.newArrayList(4));
        graph.put(3, Lists.newArrayList(2));
        graph.put(4, Lists.newArrayList(2));
        graph.put(5, Lists.newArrayList(6));
        graph.put(6, Lists.newArrayList(4));

        LinkedList<Integer> sorted = new TopologicalSorting().sort(graph);

        for (Integer vertex : sorted) {
            System.out.println(vertex);
        }
    }
}
