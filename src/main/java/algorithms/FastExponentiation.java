package algorithms;

public class FastExponentiation {

	public long fastExponent(long x, int exp){
		if(exp==0)return 1;
		if(exp%2==1){
			long newx = fastExponent(x, (exp-1)/2);
			return x*newx*newx;
		}
		long newx = fastExponent(x, exp/2);
		return newx*newx;
	}
	
	public long exponent(long x, int exp){
		if(exp == 0)return 1;
		long result = x;
		for(int e = 0; e < exp-1; e++){
			result*=x;
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FastExponentiation fe = new FastExponentiation();
		long x = 5;
		int exp = 15;
		System.out.println("fast:" + fe.fastExponent(x, exp));
		System.out.println("norm:" + fe.exponent(x, exp));
	}
}
