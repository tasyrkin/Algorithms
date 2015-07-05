package algorithms;

public class LongestCommonSubsequence {

	int[][]cache = null;
	
	int getLCS(int[]a, int[]b){
		
		for(int a_size=a.length;a_size>0;a_size--){
			for(int start_a=0;start_a<a.length-a_size+1;start_a++){
				for(int start_b=0;start_b<b.length-a_size+1;start_b++){
					boolean isTheLongestCommon = true;
					for(int i=0;i<a_size;i++){
						if(a[start_a+i]!=b[start_b+i]){
							isTheLongestCommon = false;
							break;
						}
					}
					if(isTheLongestCommon)return a_size;
				}
			}
		}
		return 0;
	}
	
	int getLCSNotSeq(int[]a, int[]b){

		cache = new int[a.length][b.length];
		
		int max = 0;
		for(int i_a = 0; i_a<a.length;i_a++){
			for(int i_b = 0; i_b<b.length;i_b++){
				int curr = getLCSNotSeq(a,b,i_a,i_b);
				if(curr>max)max = curr;
			}
		}
		return max;
	}
	
	int getLCSNotSeq(int[]a, int[]b, int j_a, int j_b){
		
		if(j_a<0||j_b<0)return 0;
		
		int max = 0;
		for(int i_a = 0; i_a<j_a;i_a++){
			for(int i_b = 0; i_b<j_b;i_b++){
				int curr = 0;
				if(cache[i_a][i_b]!=0){
					curr = cache[i_a][i_b]; 
				}
				else{
					curr = getLCSNotSeq(a,b,i_a,i_b);
					cache[i_a][i_b] = curr;
				}
				if(curr>max)max = curr;
			}
		}
		return max+(a[j_a]==b[j_b]?1:0);		
	}
	
	int getLCS(int[]a, int[]b, int size){
		
		if(size<=0)return 0;
		
		for(int start_a=0;start_a<a.length-size+1;start_a++){
			for(int start_b=0;start_b<b.length-size+1;start_b++){
				boolean isTheLongestCommon = true;
				for(int i=0;i<size;i++){
					if(a[start_a+i]!=b[start_b+i]){
						isTheLongestCommon = false;
						break;
					}
				}
				if(isTheLongestCommon)return size;
			}
		}		
		return getLCS(a, b, size-1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		int[] a = {1,2,4,3,4,4,5,7};
		int[] b = {1,2,3,4,5,6,7};
		//int max = lcs.getLCS(a, b);
		int max = lcs.getLCSNotSeq(a, b);
		System.out.println(max);
	}

}
