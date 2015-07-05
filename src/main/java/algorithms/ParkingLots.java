package algorithms;

import java.util.Arrays;

public class ParkingLots {
    public static void rearrange(final int[] slots) {
        if (slots == null || slots.length <= 1) {
            return;
        }

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == 0) {
                swap(slots, 0, i);
                break;
            }
        }

        for (int i = 1; i < slots.length; i++) {
            while (i != slots[i]) {
                swap(slots, slots[i], 0);
                swap(slots, slots[i], i);
                swap(slots, 0, i);
            }
        }
    }

    private static void swap(final int[] slots, final int from, final int to) {
        if (from == to) {
            return;
        }

        int tmp = slots[from];
        slots[from] = slots[to];
        slots[to] = tmp;
    }

    public static void main(final String[] args) {
        int[] arr = new int[] {1, 2, 3, 6, 0};
        rearrange(arr);
        System.out.println(Arrays.toString(arr));
    }
}
