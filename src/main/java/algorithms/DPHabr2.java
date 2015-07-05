package algorithms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DPHabr2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] parts = br.readLine().split("\\s+");
		int n = Integer.parseInt(parts[0]);
		int m = Integer.parseInt(parts[1]);
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(arr[j] + "");
			}
		}
		int[][] cache = new int[n][m];
		for (int i = 0; i < cache.length; i++) {
			Arrays.fill(cache[i], Integer.MAX_VALUE);
		}
		cache[0][0] = map[0][0];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i - 1 >= 0 && j - 1 >= 0) {
					cache[i][j] = Math.min(cache[i][j], map[i][j]
							+ cache[i - 1][j - 1]);
				}
				if (i - 1 >= 0) {
					cache[i][j] = Math.min(cache[i][j], map[i][j]
							+ cache[i - 1][j]);
				}
				if (j - 1 >= 0) {
					cache[i][j] = Math.min(cache[i][j], map[i][j]
							+ cache[i][j - 1]);
				}
			}
		}
		System.out.println(cache[n - 1][m - 1]);
		System.out.println("----------------------------");
		for (int i = 0; i < cache.length; i++) {
			System.out.println(Arrays.toString(cache[i]));
		}
		System.out.println("----------------------------");		
		int i = n - 1;
		int j = m - 1;
		System.out.println("(" + i + "," + j + ")");
		while (!(i == 0 && j == 0)) {
			int currmin = Integer.MAX_VALUE;
			int step = -1;
			if (i - 1 >= 0 && j - 1 >= 0) {
				if (currmin > cache[i - 1][j - 1]) {
					currmin = cache[i - 1][j - 1];
					step = 1;
				}
			}
			if (i - 1 >= 0) {
				if (currmin > cache[i - 1][j]) {
					currmin = cache[i - 1][j];
					step = 2;
				}
			}
			if (j - 1 >= 0) {
				if (currmin > cache[i][j - 1]) {
					currmin = cache[i][j - 1];
					step = 3;
				}
			}
			if (step == 1) {
				i -= 1;
				j -= 1;				
			} else if (step == 2) {
				i -= 1;
			} else if (step == 3) {
				j -= 1;
			}
			System.out.println("(" + i + "," + j + ")");
		}
		// for(int i = n-1; i >=0; i--){
		// for(int j = m-1; j >= 0; j--){
		// int i_curr = i;
		// int j_curr = j;
		// if(i-1>=0&&j-1>=0){
		// min = Math.min(min,cache[i-1][j-1]);
		// if(min==cache[i-1][j-1]){
		// i_curr = i-1;
		// j_curr = j-1;
		// }
		// }
		// if(i-1>=0){
		// min = Math.min(min,cache[i-1][j]);
		// if(min==cache[i-1][j]){
		// i_curr = i-1;
		// j_curr = j;
		// }
		// }
		// if(j-1>=0){
		// min = Math.min(min,cache[i][j-1]);
		// if(min==cache[i][j-1]){
		// i_curr = i;
		// j_curr = j-1;
		// }
		// }
		// System.out.println("("+i_curr+","+j_curr+")");
		// }
		// }
	}
}
