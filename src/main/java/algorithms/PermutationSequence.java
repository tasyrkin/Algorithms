package algorithms;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

    public String getPermutation(int n, int k) {
        if(n <= 0 && k <= 0) return "";

        List<Integer> nums = new ArrayList<>(n);
        int[]factors = new int[n];
        factors[0] = 1;
        for(int i = 0; i < n; i++) {
            nums.add(i + 1);
            if(i > 0) {
                factors[i] = i * factors[i - 1];
            }
        }

        StringBuilder result = new StringBuilder();
        for(int idx = n-1; idx >=0; idx--) {
            int currIdx = k / factors[idx];
            if(currIdx < 0 || currIdx >= n) return "";

            int num = nums.get(currIdx);
            result.append(num);
            nums.remove(Integer.valueOf(num));
            k -= currIdx * factors[idx];
        }


        return result.toString();
    }

    public static void main(String[] args) {
        final String result = new PermutationSequence().getPermutation(3, 3);

        System.out.println(result);
    }

}
