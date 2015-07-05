package algorithms;

import java.util.Scanner;

class BinSearch {
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int toSearch = sc.nextInt();
            if (i > 0) {
                System.out.print(" ");
            }

            Integer found = null;
            int start = 0;
            int end = n - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (a[mid] < toSearch) {
                    start = mid + 1;
                } else if (a[mid] > toSearch) {
                    end = mid - 1;
                } else {
                    found = mid;
                    break;
                }
            }

            System.out.print(found != null ? (found + 1) : -1);
        }

        System.out.println();
    }
}
