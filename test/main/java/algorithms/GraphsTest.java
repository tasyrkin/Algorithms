package algorithms;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import static algorithms.Graphs.createGraph;
import static algorithms.Graphs.numOfPaths;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GraphsTest {

    @org.junit.Test
    public void testNumOfPaths() throws Exception {
        assertThat(numOfPaths(createGraph(2, 0, 1), 0, 1), is(new Graphs.NumPathsResult(1L, 1L)));
        assertThat(numOfPaths(createGraph(3, 0, 1, 0, 2, 1, 2), 0, 2), is(new Graphs.NumPathsResult(2L, 2L)));
        assertThat(numOfPaths(createGraph(4, 0, 1, 0, 2, 1, 2, 1, 3), 0, 3), is(new Graphs.NumPathsResult(1L, 1L)));
        assertThat(numOfPaths(createGraph(5, 0, 1, 0, 2, 1, 2, 1, 3), 0, 4), is(new Graphs.NumPathsResult(0L, 0L)));
        assertThat(numOfPaths(createGraph(6, 0, 1, 0, 2, 1, 2, 1, 3, 2, 4, 4, 5), 0, 5), is(new Graphs.NumPathsResult(2L, 2L)));
    }
}