package algorithms;

import java.util.Arrays;

public class EratosfenSieve3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean[]primes = new boolean[1000000];
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false; 
		for(int i = 2; i*i < primes.length; i++){
			if(primes[i]){
				System.out.print(i + " ");
				for(int j = i*i; j < primes.length; j+=i){
					primes[j] = false;
				}
			}
		}
	}

}
