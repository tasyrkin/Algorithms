package algorithms.coursera.algo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Knapsack {
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[]parts = br.readLine().split("\\s+");

        int n = Integer.parseInt(parts[0]);
        int W = Integer.parseInt(parts[1]);

        int[] v = new int[n];
        int[] w = new int[n];

        parts = br.readLine().split("\\s+");

        for(int i = 0; i < n; i++){
            v[i] = Integer.parseInt(parts[i]);
        }

        parts = br.readLine().split("\\s+");

        for(int i = 0; i < n; i++){
            w[i] = Integer.parseInt(parts[i]);
        }

        int[]Vs = new int[n];
        int[]Ws = new int[n];

        Vs[0] = w[0] <= W ? v[0] : 0;
        Ws[0] = w[0] <= W ? w[0] : 0;

        for(int i = 1; i < n; i++){
            for(int j = i-1; j >= 0; j--){
                if(Ws[j]+w[i] <= W){
                    if(Vs[j] < Vs[j] + v[i]){
                        Vs[j] += v[i];
                        Ws[j] += w[i];
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            if(max < Vs[i]){
                max = Vs[i];
            }
        }
        System.out.println(max);
    }
}
