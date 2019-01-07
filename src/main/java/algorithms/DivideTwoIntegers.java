package algorithms;

class DivideTwoIntegers {
    public static void main(String[] args) {
        final int result = new DivideTwoIntegers().divide(1100540749, -1090366779);

        System.out.println(result);
    }

    public int divide(int n, int d) {

        if (n == Integer.MIN_VALUE) {
            if (d == -1) {
                return (1 << 31) - 1;
            }
        }

        int nneg = -Math.abs(n);
        int dneg = -Math.abs(d);

        int currn = nneg;
        int currd = dneg;

        int mult = 1;
        int total = 0;
        while (currn <= dneg) {
            int nextNabs = currn - currd;
            if (nextNabs > 0) {
                mult = 1;
                currd = dneg;
            } else {
                total += mult;
                mult += mult;
                currn = nextNabs;
                int nextD = currd + currd;
                if (nextD >= 0) {
                    mult = 1;
                    currd = dneg;
                } else {
                    currd += currd;
                }
            }
        }

        if ((n > 0 && d > 0) || (n < 0 && d < 0)) return total;
        else return -total;
    }
}