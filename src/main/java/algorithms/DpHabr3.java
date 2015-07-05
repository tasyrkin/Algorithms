package algorithms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DpHabr3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] parts = br.readLine().split("\\s+");
		int[] seq = new int[parts.length];
		for (int i = 0; i < parts.length; i++) {
			seq[i] = Integer.parseInt(parts[i]);
		}
		System.out.println("inp: " + Arrays.toString(seq));
		int[] cache = new int[seq.length];
		cache[0] = 1; 
		for (int i = 1; i < seq.length; i++) {
			int max = 1;
			for(int j = 0; j < i; j++){
				int add = 0;
				if(seq[i]>seq[j]){
					add = 1;
				}				
				if(max<cache[j]+add){
					max = cache[j]+add;
				}
			}
			cache[i] = max;
		}
		System.out.println(cache[parts.length-1]);
		System.out.println(Arrays.toString(cache));
	}
}
