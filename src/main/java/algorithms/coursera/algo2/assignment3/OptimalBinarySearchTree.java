package algorithms.coursera.algo2.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Oksana
 * Date: 12/29/12
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class OptimalBinarySearchTree {
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[]parts = br.readLine().split("\\s+");
        int[] ps = new int[parts.length];
        for(int i = 0; i < parts.length; i++){
            ps[i] = Integer.parseInt(parts[i]);
        }
        int[][]DP = new int[ps.length][ps.length];
        for(int s = 0; s < ps.length-1; s++){
            for(int i = 0; i < ps.length && i + s < ps.length; i++){
                int min = Integer.MAX_VALUE;
                int psSum = 0;
                for(int k = i; k <= i+s; k++){
                    psSum += ps[k];
                }
                for(int r = i; r <= i+s; r++){
                    int arrVal1 = 0;
                    if(r != i){
                        arrVal1 = DP[i][r-1];
                    }
                    int arrVal2 = 0;
                    if(r != i+s){
                        arrVal2 = DP[r+1][i+s];
                    }
                    int currMin = psSum + arrVal1 + arrVal2;
                    if(currMin < min){
                        min = currMin;
                    }
                }
                DP[i][i+s] = min;
            }
        }
        System.out.println("min cost:" + DP[0][ps.length-1]);
        for(int i = 0; i < DP.length; i++){
            System.out.println("row[" + i +"]" + Arrays.toString(DP[i]));
        }
    }
}
