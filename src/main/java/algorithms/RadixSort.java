package algorithms;

public class RadixSort {	
	
	public static Integer[] radixSortWithCountSort(Integer[] array, int numOfDigits, int base){
		
		int[] countArray = new int[base];
		int[] startArray = new int[base];
		Integer[] nextArray = new Integer[array.length];
	
		for(int currentDigit = 0; currentDigit < numOfDigits; currentDigit++){
	
			cleanArray(countArray);
			cleanArray(startArray);
			cleanArray(nextArray);
			
			for(int cnt = 0; cnt < array.length; cnt++){
				int index = Utils.getDigit(array[cnt], currentDigit, base);
				countArray[index]++;
			}
			startArray[0] = 0;
			for(int cnt = 1; cnt < countArray.length; cnt++){
				startArray[cnt] = startArray[cnt-1]+countArray[cnt-1];
			}
			for(int cnt = 0; cnt < array.length; cnt++){
				int digit = Utils.getDigit(array[cnt], currentDigit, base);
				nextArray[startArray[digit]]=array[cnt];
				startArray[digit]++;
			}
			for(int cnt = 0; cnt < array.length; cnt++){
				array[cnt]=nextArray[cnt];				
			}
		}
		return array;
	}

	private static void cleanArray(Integer[] array) {
		for(int cnt = 0; cnt < array.length; cnt++){
			array[cnt] = 0;
		}
	}

	private static void cleanArray(int[] array) {
		for(int cnt = 0; cnt < array.length; cnt++){
			array[cnt] = 0;
		}
	}

	public static void radixSort(Integer[] array, int positions, int maxNums){
		int[][] even = new int[maxNums][array.length];
		
		for(int cnt = 0; cnt < array.length; cnt++){
			int[] valuesList = even[array[cnt]%maxNums];
			for(int vals = 0; vals < valuesList.length; vals++){
				if(valuesList[vals]==0){
					valuesList[vals] = array[cnt];
					break;
				}
			}
		}
		
		int[][] odd = null;
		for(int pos = 1; pos < positions; pos++){
			if(pos % 2 == 1){
				odd = passValues(even, pos, maxNums);
			}
			else{
				even = passValues(odd, pos, maxNums);
			}
		}
		if((positions-1) % 2 == 0){
			print(even);
		}
		else{
			print(odd);
		}
	}
	
	private static void print(int[][] input){
		for(int cnt = 0; cnt < input.length; cnt++){
			for(int cntIntern = 0; cntIntern < input[cnt].length; cntIntern++){
				if(input[cnt][cntIntern]!=0){
					System.out.print(input[cnt][cntIntern] + " ");
				}
			}
		}
	}

	private static int[][] passValues(int[][] input, int pos, int maxNums) {
		int[][] output = new int[input.length][input[0].length];
		
		for(int cnt = 0; cnt < input.length; cnt++){
			for(int cntIntern = 0; cntIntern < input[cnt].length; cntIntern++){
				if(input[cnt][cntIntern]!=0){					
					Integer rest = (input[cnt][cntIntern] % (int)Math.pow(maxNums, pos+1)) / (int)Math.pow(maxNums, pos);
					for(int cntOut = 0; cntOut < output[rest].length; cntOut++){
						if(output[rest][cntOut]==0){
							output[rest][cntOut] = input[cnt][cntIntern];
							break;
						}
					}
				}
			}
		}
		return output;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//radixSort(new Integer[]{8,77,1023,90000}, 31, 2);
		//radixSort(new Integer[]{10,654,654,10,8,77,5,4,3,4,2,3,4,5,6,2,1,3,4,1023}, 4, 10);
		//radixSort(new Integer[]{1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1}, 4, 10);

		Integer[]array = radixSortWithCountSort(new Integer[]{4,6}, 1, 10);

		Utils.printlnArray(array);
	}

}
