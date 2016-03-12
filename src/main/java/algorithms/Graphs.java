package algorithms;

import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;

import java.util.HashSet;

import static com.google.common.base.Preconditions.checkArgument;

public class Graphs {

    /**
     * Finds the number of paths from one vertex to another in a direct acyclic graph.
     * This method does not check if the graph does not contain cycles.
     * @param graph
     * @param from
     * @param to
     * @return
     */
    public static long numOfPaths(HashSet<Integer>[] graph, int from, int to) {
        checkArgument(graph != null, "Graph is missing");
        try {
            HashSet<Integer> fromVertices = graph[from];
            HashSet<Integer> toVertices = graph[to];
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Invalid 'from' vertex [%s] or 'to' vertex [%d]", from, to), e);
        }

        if(from == to) {
            return 0;
        }

        return findNumOfPaths(graph, from, to);
    }

    private static long findNumOfPaths(HashSet<Integer>[] graph, int from, int to) {
        if(from == to) {
            return 1;
        }
        final HashSet<Integer> fromVertices = graph[from];
        long totalNumOfPaths = 0;
        if(fromVertices != null) {
            for(Integer nextVertex : fromVertices) {
                totalNumOfPaths += findNumOfPaths(graph, nextVertex, to);
            }
        }
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
