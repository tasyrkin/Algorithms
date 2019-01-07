package algorithms;

import java.util.Arrays;

public class EditDistance2 {

    public static void main(String[] args) {
        //final int minDistance = new EditDistance2().minDistance("a", "b");
        //final int minDistance = new EditDistance2().minDistance("horse", "ros");
        final int minDistance = new EditDistance2().minDistance("sea", "eat");
        System.out.println(minDistance);
    }

    public int minDistance(String w1, String w2) {
        if (w1 == null || w2 == null) return 0;

        char[] w1cs = w1.toCharArray();
        char[] w2cs = w2.toCharArray();

        int maxLen = Math.max(w1cs.length, w2cs.length);
        char[] w1i = new char[maxLen];
        char[] w2i = new char[maxLen];
        copy(w1i, w1cs);
        copy(w2i, w2cs);

        int[][] dp = new int[maxLen + 1][maxLen + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(w1i, w1cs.length, w2i, w2cs.length, maxLen, dp);
    }

    private int solve(char[] w1, int l1, char[] w2, int l2, int maxLen, int[][] dp) {
        if (l1 <= 0) return l2;
        if (l2 <= 0) return l1;
        if (dp[l1][l2] != -1) return dp[l1][l2];
        int i1 = l1 - 1;
        int i2 = l2 - 1;
        int min = Integer.MAX_VALUE;
        if (w1[i1] == w2[i2]) {
            min = Math.min(min, solve(w1, l1 - 1, w2, l2 - 1, maxLen, dp));
        } else {
            min = Math.min(min, 1 + solve(w1, l1 - 1, w2, l2 - 1, maxLen, dp));
            min = Math.min(min, 1 + solve(w1, l1 - 1, w2, l2, maxLen, dp));
            min = Math.min(min, 1 + solve(w1, l1, w2, l2 - 1, maxLen, dp));
        }
        dp[l1][l2] = min;
        return min;
    }

    private void copy(char[] to, char[] from) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }
}
