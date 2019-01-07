package algorithms;

public class MaximumSubarray {

    public static void main(String[] args) {
        int max = new MaximumSubarray().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(max);
    }

    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int next = 0, prev = 0, curr = 0;
        while(next < nums.length) {
            boolean moved = false;
//            System.out.println(String.format("before next: next = [%s], prev = [%s], curr = [%s]", next, prev, curr));
            while(next < nums.length && (!moved || curr + nums[next] >= curr)) {
                moved = true;
                curr += nums[next];
                if(max < curr) max = curr;
                ++next;
            }
            //System.out.println(String.format("before prev: next = [%s], prev = [%s], curr = [%s]", next, prev, curr));
            while(prev < next && curr - nums[prev] >= curr) {
                if(max < curr) max = curr;
                curr -= nums[prev];
                ++prev;
            }
//            System.out.println(String.format("after prev: next = [%s], prev = [%s], curr = [%s]", next, prev, curr));
        }
        return max;
    }
}