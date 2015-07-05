package algorithms;

public class Fibonacci {
	public static void main(String[]args){
		long[] fib = new long[101];
		fib[0] = 1;
		fib[1] = 1;
		for(int i = 2; i < fib.length; i++){
			fib[i] = fib[i-1] + fib[i-2];
		}
		long s = System.currentTimeMillis();
		long r1 = fib[64];
		long f1 = System.currentTimeMillis();
		System.out.println(r1 + ":" + (f1-s));
		s = System.currentTimeMillis();
		long r2 = fib(40);
		f1 = System.currentTimeMillis();
		System.out.println(r2 + ":" + (f1-s));		
	}
	
	public static long fib(int n){
		if(n<2)return 1;
		return fib(n-1) + fib(n-2);
	}
}
