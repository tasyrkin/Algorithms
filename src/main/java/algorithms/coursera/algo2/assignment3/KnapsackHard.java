package algorithms.coursera.algo2.assignment3;

import java.io.*;
import java.util.*;

public class KnapsackHard {

    private static Map<String, Integer> mapMetKeys = new HashMap<String, Integer>();

    public static void main(String[]args) throws IOException {

        if(args.length != 1){
            throw new IllegalArgumentException("File name is expected!");
        }

        BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
        String[]parts = br.readLine().split("\\s+");

        int W = Integer.parseInt(parts[0]);
        int n = Integer.parseInt(parts[1]);

        int[] v = new int[n];
        int[] w = new int[n];

        for(int i = 0; i < n; i++){
            parts = br.readLine().split("\\s+");
            v[i] = Integer.parseInt(parts[0]);
            w[i] = Integer.parseInt(parts[1]);
        }

        int res = solve(n - 1, W, w, v);

        System.out.println("result = " + res);
    }

    private static int solve(int index, int weight, int[] weights, int[]values) {
        String key = index + "," + weight;
        if(mapMetKeys.containsKey(key)){
            System.out.println("key = " + key + " met!");
            return mapMetKeys.get(key);
        }
        //System.out.println("index=" + index + ", weight=" + weight);
        if(weight < 0 || index < 0){
            return 0;
        }
        int total1 = solve(index - 1, weight, weights, values);
        int total2 = 0;
        if(weight - weights[index]>=0){
            total2 = solve(index - 1, weight - weights[index], weights, values) + values[index];
        }
        int res = Math.max(total1, total2);
        mapMetKeys.put(key, res);
        return res;

    }
}
