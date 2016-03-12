package algorithms;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class NQueensTest {

    @org.junit.Test
    public void testCountValidCombinations() throws Exception {
        MatcherAssert.assertThat(new NQueens().countValidCombinations(1, 1), Matchers.is(1L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(2, 2), Matchers.is(0L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(3, 3), Matchers.is(0L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(4, 4), Matchers.is(2L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(5, 5), Matchers.is(10L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(6, 6), Matchers.is(4L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(7, 7), Matchers.is(40L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(8, 8), Matchers.is(92L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(9, 9), Matchers.is(352L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(10, 10), Matchers.is(724L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(11, 11), Matchers.is(2680L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(12, 12), Matchers.is(14200L));
        MatcherAssert.assertThat(new NQueens().countValidCombinations(13, 13), Matchers.is(73712L));
        //MatcherAssert.assertThat(new NQueens().countValidCombinations(14, 14), Matchers.is(365596L));
        //MatcherAssert.assertThat(new NQueens().countValidCombinations(15, 15), Matchers.is(2279184L));
    }
}