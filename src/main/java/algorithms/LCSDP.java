package algorithms;

import java.util.Arrays;

public class LCSDP {

    public static void main(String[] args) {
        final int lcs = new LCSDP().LCS("intention".toCharArray(), "execution".toCharArray());

        System.out.println(lcs);
    }

    int LCS(char[] w1, char[] w2) {
        int[][] dp = new int[w1.length + 1][w2.length + 1];

        for (final int[] row : dp) {
            Arrays.fill(row, -1);
        }

        for (int j = 0; j < dp[0].length; j++) {
            for (int i = 0; i < dp.length; i++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (w1[i - 1] == w2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[w1.length][w2.length];
    }

}
