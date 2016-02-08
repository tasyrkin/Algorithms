package algorithms;

import com.google.common.collect.Lists;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsIterableContainingInOrder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

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

    public static void main(String[] args) {
        LongestIncreasingSubsequence3 worker = new LongestIncreasingSubsequence3();

        assertThat(worker.solve(new int[]{1, -2, 3, 4, -5, 8}).size(), is(4));
        assertThat(worker.solve(new int[]{1, -2, 3, 4, -5, 8}), hasItems(1, 3, 4, 8));

        assertThat(worker.solve(new int[]{1}).size(), is(1));
        assertThat(worker.solve(new int[]{1}), hasItems(1));

        assertThat(worker.solve(new int[]{1,1,1}).size(), is(1));
        assertThat(worker.solve(new int[]{1,1,1}), hasItems(1));

        assertThat(worker.solve(new int[]{10,9,8,7,6,5,4,3,2,1}).size(), is(1));
        assertThat(worker.solve(new int[]{10,9,8,7,6,5,4,3,2,1}), hasItems(10));

        assertThat(worker.solve(new int[]{10,9,8,7,6,5,4,3,2,11}).size(), is(2));
        assertThat(worker.solve(new int[]{10,9,8,7,6,5,4,3,2,11}), hasItems(10, 11));

        assertThat(worker.solve(new int[]{1,-1,2,-2,3,-3,4}).size(), is(4));
        assertThat(worker.solve(new int[]{1,-1,2,-2,3,-3,4}), hasItems(1, 2, 3, 4));

        assertThat(worker.solve(new int[]{10,-1,2,-2,3,-3,4}).size(), is(4));
        assertThat(worker.solve(new int[]{10,-1,2,-2,3,-3,4}), hasItems(-1, 2, 3, 4));

        assertThat(worker.solve(new int[]{10,-1,2,-2,3,-3,-2}).size(), is(3));
        assertThat(worker.solve(new int[]{10,-1,2,-2,3,-3,-2}), hasItems(-1, 2, 3));
    }
}
