package algorithms;

public class LexicalSort {
    public void sort(final int n) {
        if (n <= 0) {
            return;
        }

        int[] nums = new int[n];
        int[] p1 = new int[n];
        int[] p2 = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
            p1[i] = i;
        }

        int[] counter = new int[11];
        int[] pos = new int[11];
        for (int d = 0; d <= 9; d++) {
            for (int i = 0; i < counter.length; i++) {
                counter[i] = 0;
                pos[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                int curr = nums[p1[i]];
                int dig = getDig(curr, d);
                counter[dig]++;
            }

            for (int i = 1; i < pos.length; i++) {
                pos[i] = pos[i - 1] + counter[i - 1];
            }

            for (int i = 0; i < n; i++) {
                int curr = nums[p1[i]];
                int dig = getDig(curr, d);
                p2[pos[dig]] = p1[i];
                pos[dig]++;
            }

            for (int i = 0; i < n; i++) {
                p1[i] = p2[i];
            }

        }

        for (int i = 0; i < n; i++) {
            System.out.println(nums[p1[i]]);
        }
    }

    private String getNums(final int[] nums, final int[] p2) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            if (sb.length() > 0) {
                sb.append(" ");
            }

            sb.append(nums[p2[i]]);
        }

        return sb.toString();
    }

    private int getDig(final int num, final int dig) {
        int tmp = num;
        int len = 0;
        while (tmp > 0) {
            tmp /= 10;
            len++;
        }

        int firstIndex = 10 - len;
        int divs = dig - firstIndex;
        if (divs < 0) {
            return 0;
        }

        tmp = num;
        while (divs > 0) {
            tmp /= 10;
            --divs;
        }

        return tmp % 10 + 1;
    }

    public static void main(final String[] args) {
        new LexicalSort().sort(2000);
    }
}
