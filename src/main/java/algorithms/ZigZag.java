package algorithms;

public class ZigZag {
    public int zigZag(final int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[][] dp = new int[arr.length][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    if (dp[j][1] > max) {
                        max = dp[j][1];
                    }
                }
            }

            dp[i][0] = max + 1;
            max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (dp[j][0] > max) {
                        max = dp[j][0];
                    }
                }
            }

            dp[i][1] = max + 1;
        }

        int res = 1;

        for (int i = 0; i < arr.length; i++) {
            res = Math.max(dp[i][0], Math.max(dp[i][1], res));
        }

        return res;
    }

    public static void main(final String[] args) {
        System.out.println(new ZigZag().zigZag(
                new int[] {
                    374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                    67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                    249, 22, 176, 279, 23, 22, 617, 462, 459, 244
                }));
    }
}
