package algorithms;

import java.util.Arrays;

public class HeapSort {
    public static void sort(final int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        heapify(arr);

        System.out.println("heapified:" + Arrays.toString(arr));

        int end = arr.length - 1;
        while (end > 0) {
            swap(arr, 0, end);
            end--;
            siftDown(arr, 0, end);
        }
    }

    private static void siftDown(final int[] arr, final int startIdx, final int endIdx) {
        int currIdx = startIdx;
        while (2 * currIdx + 1 <= endIdx) {
            int swapIdx = currIdx;
            int childIdx = 2 * currIdx + 1;
            if (arr[currIdx] < arr[childIdx]) {
                swapIdx = childIdx;
            }

            if (childIdx + 1 <= endIdx && arr[swapIdx] < arr[childIdx + 1]) {
                swapIdx = childIdx + 1;
            }

            if (swapIdx != currIdx) {
                swap(arr, swapIdx, currIdx);
                currIdx = swapIdx;
            } else {
                return;
            }
        }
    }

    private static void swap(final int[] arr, final int i1, final int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    private static void heapify(final int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            siftDown(arr, i, arr.length - 1);
        }
    }

    public static void main(final String[] args) {
        int[] a = new int[] {0, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
