package algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NQueensTest {

    @org.junit.Test
    public void testCountValidCombinations() throws Exception {
        assertThat(new NQueens().countValidCombinations(1, 1), is(1L));
        assertThat(new NQueens().countValidCombinations(2, 2), is(0L));
        assertThat(new NQueens().countValidCombinations(3, 3), is(0L));
        assertThat(new NQueens().countValidCombinations(4, 4), is(2L));
        assertThat(new NQueens().countValidCombinations(5, 5), is(10L));
        assertThat(new NQueens().countValidCombinations(6, 6), is(4L));
        assertThat(new NQueens().countValidCombinations(7, 7), is(40L));
        assertThat(new NQueens().countValidCombinations(8, 8), is(92L));
        assertThat(new NQueens().countValidCombinations(9, 9), is(352L));
        assertThat(new NQueens().countValidCombinations(10, 10), is(724L));
        assertThat(new NQueens().countValidCombinations(11, 11), is(2680L));
        assertThat(new NQueens().countValidCombinations(12, 12), is(14200L));
        assertThat(new NQueens().countValidCombinations(13, 13), is(73712L));
        //MatcherAssert.assertThat(new NQueens().countValidCombinations(14, 14), Matchers.is(365596L));
        //MatcherAssert.assertThat(new NQueens().countValidCombinations(15, 15), Matchers.is(2279184L));
    }
}