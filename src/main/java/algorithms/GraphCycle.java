package algorithms;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphCycle {
    public static void main(String[] args) {

        final List[] graph = new List[3];
        graph[0] = Lists.newArrayList(1, 2);
        //graph[1] = Lists.newArrayList(0, 2);
        graph[2] = Lists.newArrayList(1);

        System.out.println(hasCycleRecursive(graph));
    }

    private static boolean hasCycleIterative(List[]graph) {
        for(int vertex = 0; vertex < graph.length; vertex++){
            final Set<Integer> onStack = new HashSet<>();

            //final boolean hasCycle = solve(vertex, onStack, graph);
            //if(hasCycle) return true;
        }

        return false;
    }

    private static boolean hasCycleRecursive(List[]graph) {

        for(int vertex = 0; vertex < graph.length; vertex++){
            final Set<Integer> onStack = new HashSet<>();
            final boolean hasCycle = solve(vertex, onStack, graph);
            if(hasCycle) return true;
        }

        return false;
    }

    private static boolean solve(final int vertex, final Set<Integer> onStack, final List[] graph) {
        if(onStack.contains(vertex)){
            return true;
        }
        onStack.add(vertex);

        final List<Integer> adjVs = graph[vertex];

        if(adjVs != null) {
            for(int adjVertex : adjVs) {
                final boolean hasCycle = solve(adjVertex, onStack, graph);
                if(hasCycle) return true;
            }
        }

        onStack.remove(vertex);
        return false;
    }

    private static List[] createGraph(final int numVertexes) {


        return new List[0];
    }
}
