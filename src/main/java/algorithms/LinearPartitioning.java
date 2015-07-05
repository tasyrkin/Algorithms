package algorithms;

public class LinearPartitioning {

	public int minimalSum(int[]s, int n, int k){
		if(n==0&&k>0)return s[0];
		if(k==1){
			int sum = 0;
			for(int i = 0; i <= n; i++)
				sum+=s[i];
			return sum;
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++){
			int lastSum = 0;
			for(int m = i+1; m <= n; m++){
				lastSum += s[m];
			}
			int prevSum = minimalSum(s,i,k-1);
			if(Math.max(lastSum, prevSum)<min){
				min = Math.max(lastSum, prevSum);
			}
		}
		return min;
	}


	public static void main(String[] args) {
		LinearPartitioning lp = new LinearPartitioning();
		int[] s = {100,200,300,400,500,600,700,800,900};
		int min = lp.minimalSum(s, s.length-1, 3);
		System.out.println(min);
	}

}
