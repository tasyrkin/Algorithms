package algorithms.crackingCodingInterview;

public class GCD {
    public static int gcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public static void main(final String[] args) {
        System.out.println(gcd(-10, 100));
    }
}
