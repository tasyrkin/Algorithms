package algorithms;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LongestIncreasingSubsequence3 {

    public List<Integer> solve(int[]arr){
        if(arr == null || arr.length == 0){
            return Lists.newArrayList();
        }
        int[] DP = new int[arr.length];
        Arrays.fill(DP, 1);

        int[] prev = new int[arr.length];
        Arrays.fill(prev, -1);

        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j] && DP[i] < DP[j]+1){
                    DP[i] = DP[j]+1;
                    prev[i] = j;
                }
            }
        }

        int result = 0;
        LinkedList<Integer> theSequence = Lists.newLinkedList();
        int maxLastIndex = -1;
        for(int i = 0; i < arr.length; i++){
            if(result < DP[i]){
                result = DP[i];
                maxLastIndex = i;
            }
        }

        int currIdx = maxLastIndex;
        while(currIdx != -1){
            theSequence.addFirst(arr[currIdx]);
            currIdx = prev[currIdx];
        }

        return theSequence;
    }
}
