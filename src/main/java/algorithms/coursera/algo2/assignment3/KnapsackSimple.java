package algorithms.coursera.algo2.assignment3;

import java.io.*;

public class KnapsackSimple {
    public static void main(String[]args) throws IOException {

        if(args.length != 1){
            throw new IllegalArgumentException("File name is expected!");
        }

        BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
        String[]parts = br.readLine().split("\\s+");

        int W = Integer.parseInt(parts[0]);
        int n = Integer.parseInt(parts[1]);

        long[] v = new long[n];
        long[] w = new long[n];

        for(int i = 0; i < n; i++){
            parts = br.readLine().split("\\s+");
            v[i] = Long.parseLong(parts[0]);
            w[i] = Long.parseLong(parts[1]);
        }

        long[][]DP = new long[n+1][W+1];

        for(int i = 1; i <= n; i++){
            for(int x = 0; x <= W; x++){
                long prev1 = 0;
                if(x>=w[i-1]){
                    prev1 = DP[i-1][(int)x-(int)w[i-1]];
                    DP[i][x] = Math.max(DP[i-1][x], prev1 + v[i-1]);
                } else {
                    DP[i][x] = DP[i-1][x];
                }
            }
        }

        System.out.println(DP[n][W]);
    }
}
