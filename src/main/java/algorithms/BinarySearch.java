package algorithms;

public class BinarySearch {

	public static int binarySearch(Integer[] array, int start, int end, Integer searchedValue){
		int index = -1;		
		if(start==end){
			Integer value = array[start];
			if(searchedValue==value){
				index = start;
			}
			else{
				index = -1;
			}
			return index;
		}
		
		int divider = (end+start)/2;
		
		Integer dividerValue = array[divider];
		if(searchedValue < dividerValue){
			index = binarySearch(array, start, divider, searchedValue);
		}
		else if(searchedValue > dividerValue){
			index = binarySearch(array, divider+1, end, searchedValue);
		}
		else{
			index = divider;
			for(int cnt = divider+1; cnt < array.length; cnt++){
				if(searchedValue != array[cnt]){
					break;
				}
				else{
					index = cnt;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer [] array = new Integer[]{1,2,3,4,5,6,7,7,7,7,7,7,7,7,7,7,7,7,8,9,10,10,10};
		int index = binarySearch(array, 0, 4, 4);
		System.out.println(index);
	}

}
