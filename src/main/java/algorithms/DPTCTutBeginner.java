package algorithms;

import java.util.Arrays;

/**
 * @author: tasyrkin
 * @since: 2013/12/23
 */
public class DPTCTutBeginner {
    public int getMinForSum(int sum, int[] coins){
        int [] dp = new int[sum+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for(int s = 0; s <= sum; s++){
            for(int c = 0; c < coins.length; c++){
                if(s + coins[c] <= sum){
                    dp[s+coins[c]] = Math.min(dp[s + coins[c]], dp[s] + 1);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < dp.length; i++){
            if(sb.length() > 0)sb.append("; ");
            sb.append(i + ":" + dp[i]);
        }
        System.out.println(sb.toString());
        return dp[sum];
    }

    public static void main(String[] args) {
        DPTCTutBeginner solver = new DPTCTutBeginner();
        System.out.println(solver.getMinForSum(11, new int[]{1,2,3}));
    }
}
