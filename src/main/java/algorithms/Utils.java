package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	private static List<Integer[]> arrays = new ArrayList<Integer[]>();
	
	static{
		arrays.add(new Integer[] {});
		arrays.add(new Integer[] {5});
		arrays.add(new Integer[] {4,6});
		arrays.add(new Integer[] {6,4,5});
		arrays.add(new Integer[] {4,6,5});
		arrays.add(new Integer[] {4,6,5,5,5,5});
		arrays.add(new Integer[] {4,6,5,7,5,7,5,7,5});
		arrays.add(new Integer[] {5,5,5,5,5,5,5});
		arrays.add(new Integer[] {10,9,8,7,6,5,4,3,2,1});
		arrays.add(new Integer[] {1,2,3,4,5,6,7,8,9,10});		
		arrays.add(new Integer[] {10000,10000,10000,9999,9999,9999,9999,9999,9999,9999});
	}
	

	public static void printArray(Integer[] array){
		System.out.print("{");
		for(int cnt = 0; cnt < array.length; cnt++){
			System.out.print(array[cnt] + (cnt < array.length-1 ? "," : ""));
		}
		System.out.print("}");
	}

	public static void printlnArray(Integer[] array){
		printArray(array);
		System.out.println("\n");
	}
	
	public static int getDigit(Integer number, int currentDigit, int base) {
		Integer upperNumber = (int)Math.pow(base, currentDigit+1);
		Integer currentNumber = (int)Math.pow(base, currentDigit);
		Integer result = number - (number / upperNumber)*upperNumber;
		result = result / currentNumber;
		return result;
	}
	
	public static Integer[] getArray(int index){
		if(index >= arrays.size()){
			return null;
		}
		return arrays.get(index);
	}
	
	public static boolean checkIfArraySorted(Integer[] array){
		for(int cnt=0; cnt<array.length-1; cnt++){
			if(array[cnt] > array[cnt+1]){
				return false;
			}
		}
		return true;
	}

}
