package algorithms.quora;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Money {

    private static final Map<Pair, Integer> callsCount = new HashMap<>();
    private static int[] coins;
    private static int[][] DP;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        final int n = sc.nextInt();
        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        int money = sc.nextInt();
        DP = new int[money + 1][n];

        final long start = System.nanoTime();
        System.out.println(calculateCounts(money, 0));
        long finish = (System.nanoTime() - start);
        System.out.println("Calculated took: " + finish + " ns OR " + finish / 1000 / 1000 + " ms");


        int callsTotalCount = 0;
        for (Map.Entry<Pair, Integer> entry : callsCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            callsTotalCount += entry.getValue();
        }
        System.out.println("callsTotalCount = " + callsTotalCount);

    }

    private static int calculateCounts(final int money, int start) {

        if (money < 0) {
            return 0;
        } else if (money == 0) {
            return 1;
        }

        if (DP[money][start] != 0) {
            return DP[money][start];
        }

        Pair key = new Pair(money, start);
        callsCount.putIfAbsent(key, 0);
        callsCount.put(key, callsCount.get(key) + 1);

        int counts = 0;
        for (int coinIndex = start; coinIndex < coins.length; coinIndex++) {
            counts += calculateCounts(money - coins[coinIndex], coinIndex);
        }

        DP[money][start] = counts;

        return counts;
    }

    private static class Pair {
        final int v1;
        final int v2;

        public Pair(final int v1, final int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Pair pair = (Pair) o;
            return v1 == pair.v1 &&
                    v2 == pair.v2;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(v1, v2);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("v1", v1)
                    .add("v2", v2)
                    .toString();
        }
    }
}
