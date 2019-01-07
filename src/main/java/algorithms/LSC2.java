package algorithms;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LSC2 {

    private static final Map<Pair, Integer> callsCount = new HashMap<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String s1 = sc.next();
        final String s2 = sc.next();

        final int[][] DP = new int[s1.length()][s2.length()];

        final int maxLen = LSC(DP, s1.toCharArray(), s1.length() - 1, s2.toCharArray(), s2.length() - 1);

        System.out.println(maxLen);

        long totalCount = 0;
        for (Map.Entry<Pair, Integer> entry : callsCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            totalCount += entry.getValue();
        }
        System.out.println("totalCount = " + totalCount);
    }

    private static int LSC(final int[][] DP, final char[] s1, final int s1idx, final char[] s2, final int s2idx) {
        if (s1idx < 0 || s2idx < 0) {
            return 0;
        }
        if (DP[s1idx][s2idx] != 0) return DP[s1idx][s2idx];

        Pair key = new Pair(s1idx, s2idx);
        callsCount.putIfAbsent(key, 0);
        callsCount.put(key, callsCount.get(key) + 1);

        int maxLen = -1;
        if (s1[s1idx] == s2[s2idx]) {
            maxLen = 1 + LSC(DP, s1, s1idx - 1, s2, s2idx - 1);
        } else {
            maxLen = Math.max(maxLen, Math.max(LSC(DP, s1, s1idx - 1, s2, s2idx), LSC(DP, s1, s1idx, s2, s2idx - 1)));
        }


        DP[s1idx][s2idx] = maxLen;

        return maxLen;
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
