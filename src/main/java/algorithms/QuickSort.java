package algorithms;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static Integer[] quickSort(Integer[] array, final int start, final int end) {

        if (start >= end) {
            return array;
        }

        Integer pivot = array[(start + end) / 2];
        int i = start;
        int j = end;
        do {
            while (array[i] < pivot) {
                i++;
                if (i > end) {
                    break;
                }
            }
            while (array[j] > pivot) {
                j--;
                if (j < start) {
                    break;
                }
            }

            if (i <= j) {
                Integer temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        array = quickSort(array, start, j);
        array = quickSort(array, i, end);

        return array;
    }

    // this is an attempt to clean the stack, but I think that this approach is not good for
    // code maintenance
    private static Integer pivot = 0;
    private static Integer temp = 0;
    private static int i = 0;
    private static int j = 0;

    public static Integer[] quickSortSedgewick(Integer[] array, final int start, final int end) {
        if (start >= end) {
            return array;
        }

        pivot = array[start];
        i = start + 1;
        j = end;
        while (i <= j) {
            while (array[i] <= pivot) {
                i++;
                if (i > end) {
                    break;
                }
            }
            while (array[j] >= pivot) {
                j--;
                if (j < start + 1) {
                    break;
                }
            }

            if (i < j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        temp = array[start];
        array[start] = array[j];
        array[j] = temp;
        array = quickSortSedgewick(array, start, j - 1);
        array = quickSortSedgewick(array, i, end);

        return array;
    }

    public static Integer[] quickSortRadixBinary(Integer[] array, final int start, final int end, final int pos) {

        if (start >= end || pos < 0) {
            return array;
        }

        int i = start;
        int j = end;

        while (i <= j) {
            while ((array[i] & 1 << pos) >> pos == 0) {
                i++;
                if (i > end) {
                    break;
                }
            }
            while ((array[j] & 1 << pos) >> pos == 1) {
                j--;
                if (j < start) {
                    break;
                }
            }

            if (i <= j) {
                Integer temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        array = quickSortRadixBinary(array, start, j, pos - 1);
        array = quickSortRadixBinary(array, i, end, pos - 1);

        return array;
    }

    private static void qsort(final int[] a, final int s, final int e) {
        if (s >= e) {
            return;
        }

        int pivot = a[s];
        int tmpi = s + 1;
        int border = s + 1;
        while (tmpi <= e) {
            if (a[tmpi] <= pivot) {
                int tmp = a[tmpi];
                a[tmpi] = a[border];
                a[border] = tmp;
                border++;
            }

            tmpi++;
        }

        int tmp = a[s];
        a[s] = a[border - 1];
        a[border - 1] = tmp;
        qsort(a, s, border - 2);
        qsort(a, border, e);
    }

    /**
     * @param  args
     */
    public static void main(final String[] args) {

        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100000; i++) {
            int size = r.nextInt(1000);
            int[] a = new int[size];
            int[] b = new int[size];
            for (int j = 0; j < size; j++) {
                a[j] = r.nextInt(100);
                b[j] = a[j];
            }

            // System.out.println("input=" + Arrays.toString(a));
            System.out.println("step=" + i);

            Arrays.sort(b);
            qsort(a, 0, a.length - 1);

            // System.out.println(Arrays.toString(a));
            // System.out.println(Arrays.toString(b));

            for (int j = 0; j < size; j++) {
                if (a[j] != b[j]) {
                    throw new IllegalStateException("Not equal [" + i + "]! a[j]=" + a[j] + ", b[j]=" + b[j]);
                }
            }
        }

    }

}
