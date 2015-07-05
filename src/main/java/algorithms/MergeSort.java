package algorithms;

public class MergeSort {

	static Integer [] sortedArray = null;
	
	public static Integer[] mergeSort(Integer[] array, int start, int finish) throws Exception{
		if(finish-start==0){
			return array;
		}
		else if(finish-start==1){
			Integer firstValue = array[start];
			Integer secondValue = array[finish];
			if(secondValue<firstValue){
				array[start] = secondValue;
				array[finish] = firstValue;
			}
			return array;
		}
		
		int divider = start+(finish-start)/2;
		Integer[] leftArray = mergeSort(array, start, divider);
		Integer[] rightArray = mergeSort(array, divider+1, finish);

		int leftIndex = start;
		int rightIndex = divider+1;
		//loop invariant: sortedArray contains sorted values from both parts of an array array (start,divider) and (divider+1,finish)
		for(int cnt = start; cnt <= finish; cnt++){
			Integer leftValue = null; 
			if(leftIndex <= divider){
				leftValue = leftArray[leftIndex]; 
			}
			Integer rightValue = null;
			if(rightIndex<=finish){
				rightValue = rightArray[rightIndex];
			}
			if(leftValue != null && rightValue != null){
				if(leftValue <= rightValue){
					sortedArray[cnt] = leftValue;
					leftIndex++;
				}
				else if(rightValue < leftValue){
					sortedArray[cnt] = rightValue;
					rightIndex++;
				}
			}
			else if(leftValue != null && rightValue == null){
				sortedArray[cnt] = leftValue;
				leftIndex++;				
			}
			else if(leftValue == null && rightValue != null){
				sortedArray[cnt] = rightValue;
				rightIndex++;				
			}
			else{
				throw new Exception("Internal error: both values are null");
			}
		}
		
		for(int cnt = start; cnt <= finish; cnt++){
			array[cnt] = sortedArray[cnt];
		}
		
		return array;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Integer [] array = new Integer[] {10,9,8,7,6,5,4,3,2,1};
		sortedArray = new Integer[array.length];
		Utils.printArray(array);		
		Utils.printArray(mergeSort(array, 0, array.length-1));
	}

}
