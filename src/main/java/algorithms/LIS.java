package algorithms;
import java.util.Arrays;


public class LIS {

	static int[] LIS = null;
	static int[] LastElem = null;
	static int[] LenLastElem = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[]seq = {1,2,1,2,5,6,7,3,2,1};
		LIS = new int [seq.length];
		LastElem = new int[seq.length];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < seq.length; i++){
			if(max<seq[i])max = seq[i];
		}
		LenLastElem = new int[max+1];		
		int res = LISMethod(seq);
		System.out.println(res);
	}
	
	public static int LISMethod(int[]seq){
		
		int maxLen = Integer.MIN_VALUE;
		for(int i = 0; i < seq.length; i++){
			int currLen = LISMethod(seq,i);
			if(currLen>maxLen){
				maxLen = currLen;
			}
		}		
		return maxLen;
	}
	
	public static int LISMethod2(int[]seq, int i){
		
		
		
		return 0;
	}

	private static int LISMethod(int[] seq, int i) {
		int maxLen = Integer.MIN_VALUE;
		for(int j = 0; j < i; j++){
			int currLen = 0;
			if(LIS[j]==0)LIS[j] = LISMethod(seq,j);
			currLen = LIS[j];
			//int currLen = LISMethod(seq,j);
			if(seq[j]<seq[i]){
				if(maxLen<currLen)
					maxLen = currLen;
			}
		}
		if(maxLen==Integer.MIN_VALUE)return 1;
		return maxLen+1;
	}

}
