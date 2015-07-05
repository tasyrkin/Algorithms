package algorithms;

public class MaximumValueContiguousSubsequence {
	int kleft = 0;
	int kright = 0;
	float maximalSum = Float.MIN_VALUE;
	
	
	public float getMVCS(float[] array){
		float best = Float.MIN_VALUE;
		int tkleft = -1;
		int tkright = -1;
		
		for(int cnt = 0; cnt < array.length; cnt++){
			maximalSum = array[cnt];
			kleft = cnt;
			kright = cnt;
			getCurrentMVCS(array, cnt, cnt, maximalSum);
			if(maximalSum >= best){
				best = maximalSum;
				tkleft = kleft;
				tkright = kright;
			}
		}		
		
		maximalSum = best;
		kleft = tkleft;
		kright = tkright;

		return best;
	}
	
	private void getCurrentMVCS(float[] array, int cnt, int cnt2, float sum) {
		if(cnt-1 < 0 && cnt2+1 >= array.length){
			return;
		}
		
		float best = maximalSum;
		
		if(cnt -1 >= 0 && best < (sum + array[cnt-1])){
			best = sum + array[cnt-1];
			kleft = cnt-1;
		}
		if(cnt2 + 1 < array.length &&  best < (sum + array[cnt2+1])){
			best = sum + array[cnt2+1];
			kright = cnt2 + 1;
		}
		if(cnt - 1 >= 0 && cnt2 + 1 < array.length && best < (array[cnt-1] + sum + array[cnt2+1])){
			best = array[cnt-1] + sum + array[cnt2+1];
			kleft = cnt - 1;
			kright = cnt2 + 1;
		}
		maximalSum = best;
		getCurrentMVCS(array, cnt - 1, cnt2 + 1, (cnt-1>=0?array[cnt-1]:0) + sum + (cnt2+1<array.length?array[cnt2+1]:0));		
		
	}

	public float getMVCSLinear(float[] array, int j){
		
		if(j == 0){
			return array[0];
		}
		float current = getMVCSLinear(array, j-1);
		if(current+array[j] >= array[j]){
			maximalSum = current+array[j];
			kright = j;
		}
		else{
			maximalSum = array[j];
			kleft = j;
			kright = j;
		}		
		return maximalSum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		float[] array = {1,2,-3,4,5,6,-7,8,8};
		MaximumValueContiguousSubsequence mvcs = new MaximumValueContiguousSubsequence();
		//mvcs.getMVCS(array);
		mvcs.getMVCSLinear(array,array.length-1);
		System.out.println("kleft:" + mvcs.kleft + " kright:" + mvcs.kright);
		
		float anotherSum = 0;
		System.out.println("sum: " + mvcs.maximalSum);
		for(int cnt = mvcs.kleft; cnt <= mvcs.kright; cnt++){
			anotherSum += array[cnt];
			System.out.print(array[cnt] + " ");
		}
		System.out.println();
		System.out.println("another sum: " + anotherSum);
		
	}

}
