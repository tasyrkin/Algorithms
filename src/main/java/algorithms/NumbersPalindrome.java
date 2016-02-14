package algorithms;

import java.util.Arrays;

public class NumbersPalindrome {
    public boolean isPalindrome(long n){
        if(n == 0){
            return true;
        }
        if(n < 0){
            throw new IllegalArgumentException("The number must be positive, was: " + n);
        }
        int[]arr = new int[40];
        int idx = 0;
        long tmp = n;
        while(tmp > 0){
            arr[idx++] = (int)(tmp%10);
            tmp = tmp / 10;
        }
        System.out.println(Arrays.toString(arr));
        for(int i = 0; i < idx / 2; i++){
            if(arr[i] != arr[idx-i-1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new NumbersPalindrome().isPalindrome(11));
        System.out.println(new NumbersPalindrome().isPalindrome(10));
        System.out.println(new NumbersPalindrome().isPalindrome(1001));
        System.out.println(new NumbersPalindrome().isPalindrome(1002));
        System.out.println(new NumbersPalindrome().isPalindrome(101));
        System.out.println(new NumbersPalindrome().isPalindrome(Long.MAX_VALUE));
        System.out.println(new NumbersPalindrome().isPalindrome(7085774586854775807L));
    }
}
