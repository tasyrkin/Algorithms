package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class EratosfenSieve {
	public static void main(String[] args){
		getPrimes();
		for(Integer i : primesList){
			//System.out.println(i + " ");
		}
	}
	static boolean[] primes = new boolean[1000001];
	static ArrayList<Integer> primesList = new ArrayList<Integer>();
	
	public static void getPrimes(){
		Arrays.fill(primes,true);
		primes[0] = false;
		primes[1] = false;
		for(int i = 2; i*i <= 1000000; i++){
			if(primes[i]){				
				int j = 2; 
				while(i*j<=1000000){
					primes[i*j++] = false;
				}
			}
		}
		for(int i = 2; i <= 1000000; i++){
			if(primes[i])primesList.add(i);
		}
	}
	
	
	private static void eratosthenSieve(int n) {
		boolean[] isPrime = new boolean[n+1];
		for(int i = 0; i < isPrime.length; i++)isPrime[i] = true;
		isPrime[0] = isPrime[1] = false;		
		for(int i = 2; i*i <= n; i++){
			System.out.print(i + " ");
			if(isPrime[i]){
				for(int j = i*i; j <= n; j+=i)isPrime[j] = false;
			}
		}
		for(int i = 0; i < isPrime.length; i++){
			if(isPrime[i])//System.out.print(i+":" + isPrime[i] + " ");
				System.out.println(i + " ");
		}
}
}
