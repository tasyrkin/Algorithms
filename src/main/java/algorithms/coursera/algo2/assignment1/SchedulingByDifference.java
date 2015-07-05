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
public class SchedulingByDifference {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split("\\s+");

        int n = Integer.parseInt(parts[0]);

        int[]weight = new int[n];
        int[]length = new int[n];
        //int[]diff = new int[n];

        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        for(int i = 0; i < n; i++){
            parts = br.readLine().split("\\s+");
            weight[i] = Integer.parseInt(parts[0]);
            length[i] = Integer.parseInt(parts[1]);
            int diff = weight[i] - length[i];
            List<Integer> list = map.get(diff);
            if(list == null){
                list = new ArrayList<Integer>();
            }
            list.add(i);
            map.put(diff, list);
        }

        Integer[] diffs = map.keySet().toArray(new Integer[0]);

        Arrays.sort(diffs);

        long res = 0;
        long end = 0;
        for(int i = diffs.length - 1; i >= 0; i--){
            List<Integer> list = map.get(diffs[i]);
            Map<Integer, List<Integer>> map1 = new HashMap<Integer, List<Integer>>();
            for(Integer idx : list){
                List<Integer> indexes = map1.get(weight[idx]);
                if(indexes == null){
                    indexes = new ArrayList<Integer>();
                }
                indexes.add(idx);
                map1.put(weight[idx], indexes);
            }
            Integer[] weights = map1.keySet().toArray(new Integer[0]);
            Arrays.sort(weights);

            for(int k = weights.length-1; k>=0; k--){
                int currentWeight = weights[k];
                List<Integer> idx = map1.get(currentWeight);
                for(Integer currIdx : idx){
                    end += length[currIdx];
                    res += end*currentWeight;
                }
            }
        }

        System.out.println(res);


    }

}
