package algorithms;

public class EditDistance {
    public static void main(String[] args) {
        final int result = new EditDistance().minDistance("horse", "ros");

        System.out.println(result);
    }

    public int minDistance(String w1, String w2) {
        if (w1 == null || w2 == null) return 0;

        char[] w1cs = w1.toCharArray();
        char[] w2cs = w2.toCharArray();

        int[][] dp = new int[w1cs.length][w2cs.length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int cost = cost(w1cs, w2cs, w1cs.length - 1, w2cs.length - 1, dp);

        for (int j = 0; j < dp[0].length; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < dp.length; i++) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(dp[i][j]);
            }
            System.out.println("[" + sb.toString() + "]");
        }


        return cost;
    }

    int cost(char[] A, char[] B, int i, int j, int[][] dp) {
        if (i <= 0 && j <= 0) {
            dp[0][0] = 0;
            return dp[0][0];
        }
        else if (i <= 0) {
            dp[0][j] = j + 1;
            return dp[0][j];
        }
        else if (j <= 0) {
            dp[i][0] = i + 1;
            return dp[i][0];
        }

        if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j];

        int min = A[i] == B[j] ? cost(A, B, i - 1, j - 1, dp) : Integer.MAX_VALUE;

        min = Math.min(min, 1 + cost(A, B, i - 1, j, dp));
        min = Math.min(min, 1 + cost(A, B, i, j - 1, dp));
        min = Math.min(min, 1 + cost(A, B, i - 1, j - 1, dp));

        dp[i][j] = Math.min(dp[i][j], min);

        return dp[i][j];
    }

}
