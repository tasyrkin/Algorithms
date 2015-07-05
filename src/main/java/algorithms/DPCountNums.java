package algorithms;

import java.util.Arrays;

public class DPCountNums {

    public static long count(final int N) {
        long[] dp = new long[N + 1];
        dp[0] = Integer.MIN_VALUE;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 1;

        for (int i = 4; i < dp.length; i++) {
            dp[i] += dp[i - 1] + dp[i - 3] + (i - 4 >= 1 ? dp[i - 4] : 0);
        }

        System.out.println(Arrays.toString(dp));

        return dp[N];
    }

    public static void main(final String[] args) {
        long count = count(10);

        System.out.println(count);
    }

}
