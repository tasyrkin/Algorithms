package algorithms;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

	static float precision = 0.0001f; 
	
	public List<Float> getLIS2(float[] array){
		
		List<Float> maxList = null;
		for(int i = 0; i < array.length; i++){
			List<Float> currentList = getLIS2Intern(array, i);
			if(currentList.size() > (maxList != null ? maxList.size() : -1)){
					maxList = currentList;
			}
		}
		return maxList;
	}
	
	public List<Float> getLIS2Intern(float[] array, int j){
		
		if(j == 0){
			List<Float> returnList = new ArrayList<Float>();
			returnList.add(array[j]);
			return returnList;
		}
		
		List<Float> maxList = null;
		for(int i = 0; i < j; i++){
			if(array[i] < array[j] && Math.abs(array[i] - array[j]) > precision){
				List<Float> currentList = getLIS2Intern(array, i);
				currentList.add(array[j]);
				if(currentList.size() > (maxList != null ? maxList.size() : -1)){
					maxList = currentList;
				}
			}
		}
		return maxList;
	}
	
	public List<Float> getLIS(float[] array, int j){
		
		if(j == 0){
			List<Float> retList = new ArrayList<Float>();
			retList.add(array[0]);
			return retList;
		}
		
		List<Float> longestISOfCurrentStep = new ArrayList<Float>();
		
		float last = array[j];
		longestISOfCurrentStep.add(last);
		for(int cnt = j-1; cnt >= 0; cnt--){
			if(array[cnt] > last && Math.abs(array[cnt]-last) > precision){
				longestISOfCurrentStep.add(0,array[cnt]);
				last = array[cnt]; 
			}			
		}
		List<Float> lIS = getLIS(array, j-1);
		float lastOfLIS = lIS.get(lIS.size()-1);
		
		if(array[j] < lastOfLIS && Math.abs(array[j]-lastOfLIS) > precision){
			lIS.add(array[j]);
		}
		
		//boolean notAtCurrentStep = lIS.size() >= longestISOfCurrentStep.size(); 
		
		List<Float> returnList = lIS.size() >= longestISOfCurrentStep.size() ? lIS : longestISOfCurrentStep;
		
		//System.out.println(returnList.toString() + " is at current step:" + !notAtCurrentStep);
		
		return returnList;  
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float[] array = {-10,0,1,-3,-2,-1,5,7,3,1,4,4.1f,4.2f,4.3f,4.4f,-1,-2,-3,-4,5};

		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		List<Float> lisList = lis.getLIS(array, array.length-1);
		List<Float> lis2List = lis.getLIS2(array);

		System.out.println(lisList.toString());	
		System.out.println(lis2List);
	}

}
