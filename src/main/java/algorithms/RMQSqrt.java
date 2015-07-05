package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RMQSqrt {
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int sqrt = (int)Math.sqrt(n);
        int[]preproz = new int[n % sqrt == 0 ? n / sqrt : n /sqrt + 1];
        Arrays.fill(preproz, -1);
        int[]arr = new int[n];

        String[]parts = br.readLine().split("\\s+");

        int minIdx = 0;
        int prprIdx = 0;
        for(int i = 0, idx = 0; i < n; i++){
            arr[i] = Integer.parseInt(parts[i]);
            if(idx >= sqrt){
                idx = 1;
                preproz[prprIdx++] = minIdx;
                minIdx = i;
            } else {
                if(arr[minIdx] > arr[i]){
                    minIdx = i;
                }
                ++idx;
            }
        }
        if(n % sqrt != 0){
            preproz[preproz.length - 1] = minIdx;
        }

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            parts = br.readLine().split("\\s+");
            int b = Integer.parseInt(parts[0]);
            int e = Integer.parseInt(parts[1]);

            int ppIdxB = b/sqrt;
            int ppIdxE = e/sqrt;

            int resIdx = -1;
            for(int j = b; j < ppIdxB*sqrt+sqrt; j++){
                if(resIdx == -1){
                    resIdx = j;
                } else {
                    if(arr[resIdx]>arr[j]){
                        resIdx = j;
                    }
                }
            }
            for(int j = ppIdxE*sqrt; j <= e; j++){
                if(resIdx == -1){
                    resIdx = j;
                } else {
                    if(arr[resIdx]>arr[j]){
                        resIdx = j;
                    }
                }
            }
            for(int j = ppIdxB+1; j < ppIdxE-1; j++){
                if(resIdx == -1){
                    resIdx = preproz[j];
                } else{
                    if(arr[resIdx] > arr[preproz[j]]){
                        resIdx = preproz[j];
                    }
                }
            }
            System.out.println(resIdx);
        }

    }
}
