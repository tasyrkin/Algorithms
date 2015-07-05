package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oksana
 * Date: 12/26/12
 * Time: 9:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class SumOfDigits {
    private static int N = -1;
    private static int maxBorderPos = -1;
    private static int[]inputNumber = null;
    private static int[]x = null;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        maxBorderPos = String.valueOf(N).length();
        inputNumber = new int[maxBorderPos];
        for(int i = 0; i < maxBorderPos; i++){
            int pow = (int)Math.pow(10, i+1);
            int pow2 = (int)Math.pow(10, i);
            inputNumber[i] = N%pow/pow2;
        }

        //System.out.println(Arrays.toString(inputNumber) + ", " + buildNumberFromDigits(inputNumber));

        x = new int[maxBorderPos];

        int res = ans(0, 0);

        System.out.println("result=" + res);
    }

    static int ans(int position, int sumOfDigits){
        if(position == maxBorderPos){
            if(buildNumberFromDigits(x) <= buildNumberFromDigits(inputNumber)){
                return sumOfDigits;
            }
            return 0;
        }
        int res = 0;
        for(int digit = 0; digit < 10; digit++){
            x[position] = digit;
            res += ans(position + 1, sumOfDigits + digit);
        }
        return res;
    }

    static int buildNumberFromDigits(int[]arr){
        int res = 0;

        for(int i = 0; i < arr.length; i++){
            res += arr[i] * Math.pow(10,i);
        }

        return res;
    }
}
