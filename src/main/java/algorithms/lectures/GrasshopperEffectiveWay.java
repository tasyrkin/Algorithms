package algorithms.lectures;

import java.util.TreeSet;

/**
 * http://sis.khashaev.ru/2013/august/c-prime/Rh9RHwH5Vj8/
 */
public class GrasshopperEffectiveWay {

    private long effectiveWay(final int[] profits, final int k) {
        if (k <= 0) {
            return 0;
        }

        if (profits == null || profits.length == 0) {
            return 0;
        }

        long[] A = new long[profits.length];
        A[0] = profits[0];

        TreeSet<Long> prevSteps = new TreeSet<>();
        prevSteps.add(A[0]);
        for (int i = 1; i < profits.length; i++) {
            Long max = prevSteps.last();
            A[i] = profits[i] + max;
            prevSteps.add(A[i]);
            if (prevSteps.size() > k) {
                prevSteps.remove(A[i - k]);
            }
        }

        return A[A.length - 1];
    }

    public static void main(final String[] args) {
        // Long effectiveWay = new GrasshopperEffectiveWay().effectiveWay(new int[] {1, 1, -3, 1}, 2);
        // System.out.println(effectiveWay);

        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(1);
        ts.add(1);
        System.out.println(ts.size());
    }

}
