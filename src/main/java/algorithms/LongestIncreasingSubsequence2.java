package algorithms;

public class LongestIncreasingSubsequence2 {

	public int lis(int[]s){
		int len = 0;
		for(int end = 0; end < s.length; end++){
			for(int start = 0; start <= end; start++){
				int currLen = 1;
				int lastMax = Integer.MIN_VALUE;
				for(int i = start; i <= end; i++){
					if(lastMax==Integer.MIN_VALUE){
						lastMax = s[i];						
					}
					else if(lastMax<s[i]){
						lastMax = s[i];
						currLen++;
					}
				}
				if(len<currLen){
					len = currLen;
				}
			}
		}
		return len;
	}
	private int[] lis2 = null;
	public int lis2intern(int[]s,int j){
		if(j==0)return 1;
		int len = 0;
		for(int i = 0; i < j; i++){
			if(s[i]<s[j]){				
				int currLen = lis2intern(s, i)+1;
				if(currLen>len)len = currLen;
			}
		}		
		return len;
	}
	public int lis2(int[]s){
		lis2 = new int[s.length];
		int len = 0;
		for(int j = 0; j < s.length; j++){
			int currLen = lis2intern(s,j);
			if(len<currLen)len=currLen;
		}
		return len;
	}
	public static void main(String[] args) {
		LongestIncreasingSubsequence2 lis2 = new LongestIncreasingSubsequence2();
		int[] s = {1,2,-1,3,-1,50,51,52,54,-10,-9,-8,-7,-6,-5};
		System.out.println(lis2.lis(s));
		System.out.println(lis2.lis2(s));
	}

}
