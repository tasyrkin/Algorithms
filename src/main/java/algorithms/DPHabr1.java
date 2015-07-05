package algorithms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DPHabr1 {

	private static int[] cache = null;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if (n == 1) {
			System.out.println(0);
			return;
		} else if (n == 2) {
			System.out.println(1);
			return;
		} else if (n == 3) {
			System.out.println(2);
			return;
		} else if (n == 4) {
			System.out.println(5);
			return;
		}
		cache = new int[n + 1];
		cache[1] = 2;
		cache[2] = 3;
		for (int i = 3; i <= n; i++) {
			cache[i] = cache[i - 1] + cache[i - 2];
		}
		int res = 0;
		for (int i = 0; i < n - 1; i++) {
			if (i == 0) {
				res += n - 3 > 0 ? cache[n - 3] : 0;
			}
			if (i == 1) {
				res += n - 4 > 0 ? cache[n - 4] : 0;
			}
			if (i + 2 == n - 1) {
				res += n - 4 > 0 ? cache[n - 4] : 0;
			}
			if (i + 1 == n - 1) {
				res += n - 3 > 0 ? cache[n - 3] : 0;
			}
			if (i != 0 && i != 1 && i + 2 != n - 1 && i + 1 != n - 1) {
				int left = i - 1;
				int right = n - 4 - left;
				res += (left > 0 ? cache[left] : 1)
						* (right > 0 ? cache[right] : 1);
			}
		}
		System.out.println(res);
	}
}
