package algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class LongestIncreasingSubsequence3Test {

    @org.junit.Test
    public void testSolve() throws Exception {
        LongestIncreasingSubsequence3 worker = new LongestIncreasingSubsequence3();

        assertThat(worker.solve(new int[]{1, -2, 3, 4, -5, 8}).size(), is(4));
        assertThat(worker.solve(new int[]{1, -2, 3, 4, -5, 8}), hasItems(1, 3, 4, 8));

        assertThat(worker.solve(new int[]{1}).size(), is(1));
        assertThat(worker.solve(new int[]{1}), hasItems(1));

        assertThat(worker.solve(new int[]{1,1,1}).size(), is(1));
        assertThat(worker.solve(new int[]{1,1,1}), hasItems(1));

        assertThat(worker.solve(new int[]{10,9,8,7,6,5,4,3,2,1}).size(), is(1));
        assertThat(worker.solve(new int[]{10,9,8,7,6,5,4,3,2,1}), hasItems(10));

        assertThat(worker.solve(new int[]{10,9,8,7,6,5,4,3,2,11}).size(), is(2));
        assertThat(worker.solve(new int[]{10,9,8,7,6,5,4,3,2,11}), hasItems(10, 11));

        assertThat(worker.solve(new int[]{1,-1,2,-2,3,-3,4}).size(), is(4));
        assertThat(worker.solve(new int[]{1,-1,2,-2,3,-3,4}), hasItems(1, 2, 3, 4));

        assertThat(worker.solve(new int[]{10,-1,2,-2,3,-3,4}).size(), is(4));
        assertThat(worker.solve(new int[]{10,-1,2,-2,3,-3,4}), hasItems(-1, 2, 3, 4));

        assertThat(worker.solve(new int[]{10,-1,2,-2,3,-3,-2}).size(), is(3));
        assertThat(worker.solve(new int[]{10,-1,2,-2,3,-3,-2}), hasItems(-1, 2, 3));
    }
}