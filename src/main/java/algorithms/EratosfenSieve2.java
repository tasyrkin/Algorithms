package algorithms;

import java.util.Arrays;

public class EratosfenSieve2 {

	public static boolean[] primes(int n) {
		boolean[] primes = new boolean[n + 1];
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		for (int i = 2; i * i <= n; i++) {
			if (primes[i]) {
				for (int j = i * i; j <= n && j > 0; j += i) {
					primes[j] = false;
				}
			}
		}
		return primes;
	}

	public static void main(String[] args) {
		int n = 14;
		boolean[] primes = new EratosfenSieve2().primes(n);
		for (int i = 0; i < primes.length; i++) {
			if(primes[i])
				System.out.print(i + " ");			
		}
	}

}
