package algorithms.timus;

import java.util.Scanner;

public class Sums {
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n >= 1) {
            System.out.println(n * (n + 1) / 2);
        } else {
            int m = -n;
            int res = m * (m + 1) / 2;
            res = -res + 1;
            System.out.println(res);
        }
    }
}
