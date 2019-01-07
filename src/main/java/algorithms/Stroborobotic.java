package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Stroborobotic {
    private static final int[] nums = new int[]{0, 1, 6, 8, 9};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Stroborobotic().findStrobogrammatic(4).toArray(new String[0])));
    }

    public List<String> findStrobogrammatic(int n) {
        return solve(0, n, new int[n / 2]);
    }

    List<String> solve(int idx, int n, int[] curr) {
        if (idx == n / 2) {
            StringBuilder first = new StringBuilder();
            for (int i = 0; i < curr.length; i++) {
                first.append(curr[i]);
            }
            StringBuilder second = new StringBuilder();
            for (int i = curr.length - 1; i >= 0; i--) {
                if (curr[i] == 9) second.append(6);
                else if (curr[i] == 6) second.append(9);
                else second.append(curr[i]);
            }
            List<String> result = new ArrayList<>();
            if (n % 2 == 0) {
                //System.out.println("Adding");
                result.add(first.toString() + second.toString());
            } else {
                result.add(first.toString() + "0" + second.toString());
                result.add(first.toString() + "1" + second.toString());
                result.add(first.toString() + "8" + second.toString());
            }
            return result;
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (idx == 0 && nums[i] != 0) {
                curr[idx] = nums[i];
                result.addAll(solve(idx + 1, n, curr));
            }
        }
        return result;
    }
}