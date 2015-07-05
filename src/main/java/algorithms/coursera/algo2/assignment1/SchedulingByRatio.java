package algorithms.coursera.algo2.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tim
 * Date: 12/16/12
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class SchedulingByRatio {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split("\\s+");

        int n = Integer.parseInt(parts[0]);

        int[]weight = new int[n];
        int[]length = new int[n];
        //int[]diff = new int[n];

        Map<Double, List<Integer>> map = new HashMap<Double, List<Integer>>();

        long max = -1;

        for(int i = 0; i < n; i++){
            parts = br.readLine().split("\\s+");
            weight[i] = Integer.parseInt(parts[0]);
            length[i] = Integer.parseInt(parts[1]);
            double ratio = (double)weight[i] / length[i];
            List<Integer> list = map.get(ratio);
            if(list == null){
                list = new ArrayList<Integer>();
            }

            list.add(i);
            map.put(ratio, list);
        }

        Double[] ratios = map.keySet().toArray(new Double[0]);

        Arrays.sort(ratios);

        long res = 0;
        long end = 0;
        for(int i = ratios.length - 1; i >= 0; i--){
            List<Integer> indexes = map.get(ratios[i]);
            for(Integer idx : indexes){
                end += length[idx];
                res += end*weight[idx];
            }
        }

        System.out.println(res);


    }

    static long mcm(long a, long b){
        return (a * b) / gcd(a, b);
    }

    static long gcd(long a, long b){
        if(a < b) {
            return gcd(b,a);
        }
        if(b == 0){
            return a;
        }
        return gcd(b, a%b);
    }

}
