package algorithms.quora;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Wines {

    private static final Map<Pair, Integer> callsCount = new HashMap<>();
    private static int N;
    private static long[][] DP;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        DP = new long[N][N];
        int[] prices = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = sc.nextInt();
        }

        System.out.println(maxPrice(prices, 0, N - 1));

        for (Map.Entry<Pair, Integer> entry : callsCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

    private static long maxPrice(final int[] prices, final int start, final int finish) {
        if (start > finish) {
            return 0;
        }
        if (DP[start][finish] != 0) {
            return DP[start][finish];
        }

        final Pair arg = new Pair(start, finish);
        callsCount.putIfAbsent(arg, 0);
        callsCount.put(arg, callsCount.get(arg) + 1);
        int year = N - (finish - start);
        long p1 = maxPrice(prices, start + 1, finish);
        long p2 = maxPrice(prices, start, finish - 1);

        DP[start][finish] = Math.max(p1 + prices[start] * year, p2 + prices[finish] * year);

        return DP[start][finish];
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
