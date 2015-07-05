package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestCommonSubsequence2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//generateSubsequences();
		//exampleLCSRecursive();
		lcsIterative();
		//lcsNoninterruptable();
	}

	private static void lcsIterative() {
		int[] a = {1,2,2,0};
		int[] b = {2,2,0,7,8};
		
		int[][] L = new int[a.length+1][b.length+1];
		for(int i = a.length; i >= 0; i--){
			for(int j = a.length; j >= 0; j--){
				if(i>=a.length || j>=b.length){
					L[i][j] = 0;
					continue;
				}
				if(a[i]==b[j]){
					L[i][j] = 1 + L[i+1][j+1]; 
				}
				else{
					L[i][j] = Math.max(L[i+1][j], L[i][j+1]);
				}
			}
		}		
		System.out.println("lcs length: " + L[0][0]);
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		int i = 0, j = 0;
		while(i < a.length && j < b.length){
			if(a[i] == b[j]){
				sequence.add(a[i]);
				i++;
				j++;
			}
			else if(L[i+1][j]>=L[i][j+1])i++;
			else j++;
		}
		for(Integer elem : sequence){
			System.out.print(elem + " ");
		}
	}

	private static void lcsNoninterruptable(){
		int[] a = {1,2,2,2,0,7,8};
		int[] b = {2,2,0,7,8};
		
		int[][] L = new int[a.length+1][b.length+1];
		int max = 0;
		for(int i = a.length; i >= 0; i--){
			for(int j = b.length; j >= 0; j--){
				if(i>=a.length || j>=b.length){
					L[i][j] = 0;
					continue;
				}
				if(a[i]==b[j]){
					L[i][j] = 1 + L[i+1][j+1];
					if(max < L[i][j])max = L[i][j]; 
				}
				else{
					L[i][j] = 0;
				}
			}
		}		
		
		System.out.println("lcs length: " + max);
//		ArrayList<Integer> sequence = new ArrayList<Integer>();
//		int i = 0, j = 0;
//		while(i < a.length && j < b.length){
//			if(a[i] == b[j]){
//				sequence.add(a[i]);
//				i++;
//				j++;
//			}
//			else if(L[i+1][j]>=L[i][j+1])i++;
//			else j++;
//		}
//		for(Integer elem : sequence){
//			System.out.print(elem + " ");
//		}
		
	}
	
	private static void exampleLCSRecursive() {
		int[] a = {1,4,3,5};
		int[] b = {2,0,7,8};

		ArrayList<Integer> res = lcsRecursive(a, a.length-1, b, b.length-1, "");
		for(int i : res){
			System.out.print(i + " ");
		}
	}

	public static ArrayList<Integer> lcsRecursive(int[]a, int i, int[]b, int j, String shift){
		if(i < 0 || j < 0){
			return new ArrayList<Integer>();
		}
		System.out.println(shift + i + " " + j + "; ");
		String currShift = shift + " ";
		if(a[i]==b[j]){
			ArrayList<Integer> listC = lcsRecursive(a, i-1, b, j-1, currShift);
			listC.add(a[i]);
		}
		ArrayList<Integer> listL = lcsRecursive(a, i-1, b, j, currShift);
		ArrayList<Integer> listR = lcsRecursive(a, i, b, j-1, currShift);
		if(listL.size() > listR.size())
			return listL;
		return listR;
	}

	private static void generateSubsequences() {
		int [] arr = {5,9};
		ArrayList<String> result = subSeq(arr, 0, arr.length-1);
		long[] resultOldInt = new long [result.size()];
		int jold = 0;
		for(String s : result){
			resultOldInt[jold++] = Long.parseLong(s); 
		}
		Arrays.sort(resultOldInt);
		for(Long longnum : resultOldInt){
			System.out.print(longnum + " ");
		}
		System.out.println();
		result = new ArrayList<String>();
		for(long i = 1; i<=Math.pow(2, arr.length)-1; i++){
			String currStr = "";
			for(int j = 0; j < arr.length; j++){
				if(((i>>j)&0x1)==1){
					currStr+=arr[j];
				}
			}
			result.add(currStr);
		}
		long[] resultArrayInt = new long[result.size()];
		int i = 0;
		for(String s : result){
			resultArrayInt[i++] = Long.parseLong(s);
		}
		for(long longnum : resultArrayInt){
			System.out.print(longnum + " ");
		}
	}

	public static ArrayList<String> subSeq(int[] a, int i, int j){
		if(i>j){
			return new ArrayList<String>();
		}
		ArrayList<String> list = new ArrayList<String>();
		list.add(""+a[i]);
		ArrayList<String> sublist = subSeq(a, i+1, j);
		list.addAll(sublist);
		for(String s : sublist){
			list.add(a[i] + s);
		}
		return list;
	}

}
