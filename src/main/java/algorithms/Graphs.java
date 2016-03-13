package algorithms;

import java.util.Arrays;
import java.util.HashSet;

import static com.google.common.base.Preconditions.checkArgument;

public class Graphs {

    private static class NumOfTraversals {
        private int value;

        public NumOfTraversals(){
            value = 0;
        }

        private NumOfTraversals(int value){
            this.value = value;
        }

        void inc() {
            ++value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Contains result of the numOfPaths() method.
     * It contains number of paths and number of traversals
     * that was necessary to calculate the number of paths.
     */
    public static class NumPathsResult {
        private long numOfPaths;
        private long numOfTraversals;

        public NumPathsResult(long numOfPaths, long numOfTraversals) {
            this.numOfPaths = numOfPaths;
            this.numOfTraversals = numOfTraversals;
        }

        public long getNumOfPaths() {
            return numOfPaths;
        }

        public long getNumOfTraversals() {
            return numOfTraversals;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NumPathsResult that = (NumPathsResult) o;

            if (numOfPaths != that.numOfPaths) return false;
            return numOfTraversals == that.numOfTraversals;

        }

        @Override
        public int hashCode() {
            int result = (int) (numOfPaths ^ (numOfPaths >>> 32));
            result = 31 * result + (int) (numOfTraversals ^ (numOfTraversals >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "NumPathsResult{" +
                    "numOfPaths=" + numOfPaths +
                    ", numOfTraversals=" + numOfTraversals +
                    '}';
        }
    }

    /**
     * Finds the number of paths from one vertex to another in a direct acyclic graph.
     * This method does not check if the graph does not contain cycles.
     * @param graph
     * @param from
     * @param to
     * @return
     */
    public static NumPathsResult numOfPaths(HashSet<Integer>[] graph, int from, int to) {
        checkArgument(graph != null, "Graph is missing");
        checkArgument(from >= 0 && from < graph.length, "Invalid 'from' vertex [%d]", from);
        checkArgument(to >= 0 && to < graph.length, "Invalid 'to' vertex [%d]", to);

        if(from == to) {
            return new NumPathsResult(0, 0);
        }

        final long[] cache = new long[graph.length];
        Arrays.fill(cache, -1);

        final NumOfTraversals numOfTraversals = new NumOfTraversals();
        final long numOfPaths = findNumOfPaths(graph, from, to, numOfTraversals, cache);

        return new NumPathsResult(numOfPaths, numOfTraversals.getValue());
    }

    private static long findNumOfPaths(HashSet<Integer>[] graph, int from, int to, NumOfTraversals numOfTraversals, long[] cache) {

        if(cache[from] != -1) {
            return cache[from];
        }

        if(from == to) {
            numOfTraversals.inc();
            return 1;
        }
        final HashSet<Integer> fromVertices = graph[from];
        long totalNumOfPaths = 0;
        if(fromVertices != null) {
            for(Integer nextVertex : fromVertices) {
                totalNumOfPaths += findNumOfPaths(graph, nextVertex, to, numOfTraversals, cache);
            }
        }

        cache[from] = totalNumOfPaths;

        return totalNumOfPaths;
    }

    /**
     * Creates a adjacent lists graph representation.
     * @param numVertices must be positive
     * @param edges represents by pairs of integers. The number of vertices must be even.
     * @return
     */
    public static HashSet<Integer>[] createGraph(int numVertices, Integer... edges) {
        checkArgument(numVertices > 0, "Number of vertices must be positive [%s]", numVertices);
        checkArgument(edges.length % 2 == 0, "Wrong number of vertices in edges [%s]", edges.length);

        final HashSet<Integer>[] graph = new HashSet[numVertices];

        for(int index = 0; index < edges.length; index+=2) {
            HashSet<Integer> vertices = graph[edges[index]];
            if(vertices == null) {
                vertices = new HashSet<>();
                graph[edges[index]] = vertices;
            }
            vertices.add(edges[index + 1]);
        }

        return graph;
    }
}
