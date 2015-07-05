package algorithms;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class StrangeNumbers {

    public static boolean isInRange(long num, long left, long right){
        return left <= num && num <= right;
    }

    public static boolean isDividableByLength(long num, long mult){
        return String.valueOf(num).length() == mult;
    }

    public static void evaluate(long num, long mult, long left, long right, Set<Long> numbers){
        if(num<0||num>right)return;
        if(isInRange(num, left, right)&&isDividableByLength(num, mult)){
            numbers.add(num);
        }
        long startMult = mult == 1 ? 2 : mult;
        for(long currMult = startMult; currMult <= 18; currMult++){
            long next = num*currMult;
            int nextStrLen = String.valueOf(next).length();
            if(next<0||next>right||nextStrLen<currMult)break;
            if(nextStrLen>currMult)continue;
            evaluate(next, currMult, left, right, numbers);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            long left = sc.nextLong();
            long right = sc.nextLong();
            Set<Long> numbers = new HashSet<>();

            for(long i = 0; i < 10; i++) {
                if (isInRange(i, left, right)) numbers.add(i);
            }

            for(long num = 2; num < 10; num++){
                for(long mult = 1; mult <= 3; mult++){
                    evaluate(num*mult, mult, left, right, numbers);
                }
            }
            System.out.println(numbers.size());
            //System.out.println(Arrays.toString(numbers.toArray()));
        }
    }
}