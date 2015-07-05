package algorithms;

public class MillerRabin {
	
	public boolean isPrime2(int n, int t){
		if(n%2==0)return false;
		if(n==1)return false;
		if(n==3)return true;
		int r = n-1;
		int s = 0;
		while(r%2==0){
			r=r/2;
			s++;
		}
		for(int i = 1; i <= t; i++){
			long a = (long)((n-4)*Math.random()+2);
			long rest = 1;
			int pow = r;
			long a1 = a;
			while(pow>0){
				if(pow%2==1)rest=(rest*a1)%n;
				pow/=2;
				a1 = (a1*a1)%n;
			}
			long y = rest;
			if(y!=1&&y!=n-1){
				int j = 1;
				while(j<=s-1&&y!=n-1){
					y = (y*y)%n;
					if(y==1)return false;
					j++;
				}
				if(y!=n-1)return false;
			}
		}
		return true;		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MillerRabin mr = new MillerRabin();
		int n = 50000017;
		System.out.println("Is " + n + " prime? " + mr.isPrime2(n, 2));
	}

}
