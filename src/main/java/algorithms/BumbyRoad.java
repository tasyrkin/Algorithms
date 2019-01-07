package algorithms;

import java.util.Arrays;

public class BumbyRoad {
    public static void main(String[] args) {
        final boolean canStop = new BumbyRoad().canStop(
                new boolean[]{
                        true, true, false, true
                },
                0,
                2);

        System.out.println(canStop);
    }

    public boolean canStop(boolean[] road, int initPosition, int initSpeed) {
        if (road == null || road.length == 0 || initPosition < 0 || initPosition >= road.length || initSpeed <= 0) {
            throw new IllegalArgumentException("Bad argument");
        }
        Boolean[][] dp = new Boolean[road.length + 1][initSpeed + road.length + 1];

        boolean canStop = solve(road, initPosition, initSpeed, dp);

        for (int idx = 0; idx < dp.length; idx++) {
            System.out.println(idx + ": " + Arrays.toString(dp[idx]));
        }

        return canStop;
    }

    private boolean solve(final boolean[] road, final int pos, final int speed, final Boolean[][] dp) {
        if (pos >= road.length) return false;
        if (!road[pos]) return false;
        if (speed == 0) return true;
        if (dp[pos][speed] != null) return dp[pos][speed];

        boolean result = false;
        for (int speedDiff = -1; speedDiff <= 1; speedDiff++) {
            int currSpeed = speed + speedDiff;
            result |= solve(road, pos + currSpeed, currSpeed, dp);
        }

        dp[pos][speed] = result;

        return result;
    }
}
