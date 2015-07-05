package algorithms.coursera.algo2.assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JohnsonAPSP {

    private static class EdgeEnd {
        int vertex;
        int cost;

        public EdgeEnd(final int v, final int c) {
            vertex = v;
            cost = c;
        }

        public boolean equals(final Object o) {
            if (o != null && o instanceof EdgeEnd) {
                return vertex == ((EdgeEnd) o).vertex;
            }

            return false;
        }

        public int hashCode() {
            int prime = 31;
            int code = 0;
            code += (prime * vertex);
            return code;
        }

        public String toString() {
            return "(" + vertex + "," + cost + ")";
        }
    }

    public static void main(final String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = bufferedReader.readLine().split("\\s+");

        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        List<EdgeEnd>[] reversedGraph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            if (reversedGraph[i] == null) {
                reversedGraph[i] = new ArrayList<EdgeEnd>(n + 1);
            }

            reversedGraph[i].add(new EdgeEnd(0, 0));
        }

        for (int i = 0; i < m; i++) {
            parts = bufferedReader.readLine().split("\\s+");

            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            int cost = Integer.parseInt(parts[2]);
            if (reversedGraph[to] == null) {
                reversedGraph[to] = new ArrayList<EdgeEnd>(n + 1);
            }

            reversedGraph[to].add(new EdgeEnd(from, cost));
        }

// List<EdgeEnd>[] reweightedGraph = BellmanFord(reversedGraph.length, reversedGraph);
        int nNew = n + 1;
        int[][] DP = new int[nNew + 1][nNew + 1];

        for (int v = 0; v < DP.length; v++) {
            Arrays.fill(DP[v], Integer.MAX_VALUE);
            DP[v][v] = 0;
        }

        for (int i = 1; i <= nNew; i++) {
            for (int v = 0; v < nNew; v++) {
                int prev = DP[i - 1][v];
                int dpStep = Integer.MAX_VALUE;
                List<EdgeEnd> inVertexes = reversedGraph[v];
                if (inVertexes != null) {
                    for (EdgeEnd ee : inVertexes) {
                        int currDpStep = Integer.MAX_VALUE;
                        if (DP[i - 1][ee.vertex] != Integer.MAX_VALUE) {
                            currDpStep = DP[i - 1][ee.vertex] + ee.cost;
                        }

                        dpStep = Math.min(dpStep, currDpStep);
                    }
                }

                DP[i][v] = Math.min(prev, dpStep);
            }
        }

        for (int v = 0; v < nNew; v++) {
            if (DP[nNew][v] != DP[nNew - 1][v]) {
                System.out.println("Has negative cycle for vertex [" + v + "] DP[n][v] = " + DP[nNew][v]
                        + ", DP[n-1][v] = " + DP[nNew - 1][v] + ". Exiting...");
                System.exit(0);
            }
        }

        System.out.println("No negative cycles!");

        List<EdgeEnd>[] reweightedGraph = new ArrayList[nNew];

        for (int v = 0; v < nNew; v++) {
            if (reversedGraph[v] != null) {
                for (EdgeEnd ee : reversedGraph[v]) {
                    if (reweightedGraph[ee.vertex] == null) {
                        reweightedGraph[ee.vertex] = new ArrayList<EdgeEnd>(nNew);
                    }

                    int delta = DP[nNew - 1][ee.vertex] - DP[nNew - 1][v];
                    int newCost = ee.cost + delta;
                    if (newCost < 0) {
                        throw new IllegalArgumentException("Negative edge cost [" + newCost + "]! From [" + ee.vertex
                                + "], to [" + v + "] ");
                    }

                    EdgeEnd newEE = new EdgeEnd(v, newCost);
                    reweightedGraph[ee.vertex].add(newEE);
                }
            }
        }

        System.out.println("Reweighting done!");

        // printGraph(reweightedGraph);

        int[][] APSP = new int[reweightedGraph.length][reweightedGraph.length];

        for (int v = 0; v < APSP.length; v++) {
            Arrays.fill(APSP[v], Integer.MAX_VALUE);
        }

        for (int v = 0; v < reweightedGraph.length; v++) {
            System.out.println("vertex = " + v);

            Set<Integer> X = new HashSet<Integer>(reweightedGraph.length);
            X.add(v);

            int[] A = new int[reweightedGraph.length];
            Arrays.fill(A, Integer.MAX_VALUE);
            A[v] = 0;
            while (X.size() != reweightedGraph.length) {
                int from = -1;
                int to = -1;
                int c = Integer.MAX_VALUE;
                for (int i = 0; i < reweightedGraph.length; i++) {
                    if (X.contains(i)) {
                        if (reweightedGraph[i] != null) {
                            for (EdgeEnd ee : reweightedGraph[i]) {
                                if (!X.contains(ee.vertex)) {
                                    if (from == -1) {
                                        from = i;
                                        to = ee.vertex;
                                        c = ee.cost;
                                    } else {
                                        if (A[i] != Integer.MAX_VALUE && A[from] + c > A[i] + ee.cost) {
                                            from = i;
                                            to = ee.vertex;
                                            c = ee.cost;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (from != -1) {
                    A[to] = A[from] + c;
                    X.add(to);
                } else {
                    System.out.println("Was not able to find cut crossing edge!");
                    break;
                }
            }

            for (int i = 0; i < reweightedGraph.length; i++) {
                APSP[v][i] = A[i];
            }
        }

        System.out.println("Dijkstra done!");

        int min = Integer.MAX_VALUE;
        for (int from = 1; from < APSP.length; from++) {
            for (int to = 1; to < APSP[0].length; to++) {
                if (APSP[from][to] != Integer.MAX_VALUE) {
                    APSP[from][to] -= (DP[nNew - 1][from] - DP[nNew - 1][to]);
                }

                if (min > APSP[from][to]) {
                    min = APSP[from][to];
                }
            }
        }

        System.out.println("Answer: " + min);

    }

    public static void printGraph(final List<EdgeEnd>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print("[" + i + "]->");
            if (graph[i] != null) {
                for (EdgeEnd ee : graph[i]) {
                    System.out.print(ee);
                }
            }

            System.out.println();
        }
    }

    public static List<EdgeEnd>[] BellmanFord(final int nNew, final List<EdgeEnd>[] revertedGraph) {
        int[][] DP = new int[nNew + 1][nNew + 1];

        for (int v = 0; v < DP.length; v++) {
            Arrays.fill(DP[v], Integer.MAX_VALUE);
            DP[v][v] = 0;
        }

        for (int i = 1; i <= nNew; i++) {
            for (int v = 0; v < nNew; v++) {
                int prev = DP[i - 1][v];
                int dpStep = Integer.MAX_VALUE;
                List<EdgeEnd> inVertexes = revertedGraph[v];
                if (inVertexes != null) {
                    for (EdgeEnd ee : inVertexes) {
                        int currDpStep = Integer.MAX_VALUE;
                        if (DP[i - 1][ee.vertex] != Integer.MAX_VALUE) {
                            currDpStep = DP[i - 1][ee.vertex] + ee.cost;
                        }

                        dpStep = Math.min(dpStep, currDpStep);
                    }
                }

                DP[i][v] = Math.min(prev, dpStep);
            }
        }

        for (int v = 0; v < nNew; v++) {
            if (DP[nNew][v] != DP[nNew - 1][v]) {
                System.out.println("Has negative cycle for vertex [" + v + "] DP[n][v] = " + DP[nNew][v]
                        + ", DP[n-1][v] = " + DP[nNew - 1][v] + ". Exiting...");
                System.exit(0);
            }
        }

        System.out.println("No negative cycles!");

        List<EdgeEnd>[] reweightedGraph = new ArrayList[nNew];

        for (int v = 0; v < nNew; v++) {
            if (revertedGraph[v] != null) {
                for (EdgeEnd ee : revertedGraph[v]) {
                    if (reweightedGraph[ee.vertex] == null) {
                        reweightedGraph[ee.vertex] = new ArrayList<EdgeEnd>(nNew);
                    }

                    int delta = DP[nNew - 1][ee.vertex] - DP[nNew - 1][v];
                    int newCost = ee.cost + delta;
                    if (newCost < 0) {
                        throw new IllegalArgumentException("Negative edge cost [" + newCost + "]! From [" + ee.vertex
                                + "], to [" + v + "] ");
                    }

                    EdgeEnd newEE = new EdgeEnd(v, newCost);
                    reweightedGraph[ee.vertex].add(newEE);
                }
            }
        }

        return reweightedGraph;
    }

}
