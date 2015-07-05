package algorithms;

import java.util.Arrays;
import java.util.HashSet;

public class Dividers{
	public static Integer[] divs(int num){
		HashSet<Integer> divs = new HashSet<Integer>();
		for(int i = 1; i*i <= num; i++){
			if(num%i==0){
				divs.add(i);
				divs.add(num/i);
			}
		}
		Integer[] divsArr = divs.toArray(new Integer[divs.size()]);
		Arrays.sort(divsArr);
		return divsArr;
	}
}