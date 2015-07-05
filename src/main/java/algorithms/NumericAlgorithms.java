package algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class NumericAlgorithms {

	public String convert(long num, int base){
		if(base>18||base<=1)return "NOT SUPPORTED";
		long res = num;
		Stack<Long> convNum = new Stack<Long>(); 
		while(res>0){			
			convNum.push(res%base);
			res/=base;
		}
		StringBuffer sb = new StringBuffer();
		while(!convNum.empty()){
			long rem = convNum.pop();
			sb.append(convChar((int)rem));
		}
		return sb.toString();
	}

	public void increase(int[]num, int base){
		increase(num, 0, base);
	}

	public void increase(int[]num, int i, int base){
		if(i>=num.length)return;
		if(++num[i]>=base){
			num[i] = 0;
			increase(num, i+1, base);
		}		
	}

	private char convChar(int num){
		char ch;
		switch (num) {
		case 0:ch = '0';break;
		case 1:ch = '1';break;
		case 2:ch = '2';break;
		case 3:ch = '3';break;
		case 4:ch = '4';break;
		case 5:ch = '5';break;
		case 6:ch = '6';break;
		case 7:ch = '7';break;
		case 8:ch = '8';break;
		case 9:ch = '9';break;
		case 10:ch = 'A';break;
		case 11:ch = 'B';break;
		case 12:ch = 'C';break;
		case 13:ch = 'D';break;
		case 14:ch = 'E';break;
		case 15:ch = 'F';break;
		case 16:ch = 'J';break;
		case 17:ch = 'H';break;
		default:
			ch = ' ';
			break;
		}
		return ch;
	}

	private void generateSubsets(int n, int k){
		generateSubsets(n, k, new HashSet<Integer>());
	}
	private void generateSubsets(int n, int k, HashSet<Integer> subset){
		if(k == 0){
			Iterator<Integer> iter = subset.iterator();
			System.out.print("(");
			while(iter.hasNext()){
				System.out.print(iter.next() + " ");
			}
			System.out.println(")");
			return;
		}
		for(int i = 1; i <= n; i++){
			if(!subset.contains(i)){
				HashSet<Integer> newSubset = (HashSet<Integer>)subset.clone();
				newSubset.add(i);
				generateSubsets(n, k-1, newSubset);
			}
		}
	}
	
	private void printSubset(int[]array){
		boolean isFirst = true;
//		int count = 0;
//		for(int i = 1; i < array.length; i++){
//			if(array[i]==1)count++;
//		}
//		if(count!=2)return;
		System.out.print("{");
		for(int i = 1; i < array.length; i++){
			if(array[i]==1){
				System.out.print((isFirst?"":" ") + i);
				isFirst = false; 
			}
		}
		System.out.println("}");
	}
	
	public void generateSubsetsOrderedLexicographically(int n){
		int[]array = new int[n+1];
		generateSubsetsOrderedLexicographically(array,n);
	}
	public void generateSubsetsOrderedLexicographically(int[]array,int idx){
		if(idx == 0)printSubset(array);
		else{
			array[idx] = 0;
			generateSubsetsOrderedLexicographically(array,idx-1);
			array[idx] = 1;
			generateSubsetsOrderedLexicographically(array,idx-1);
		}
	}
	
	private void generateSubsetsWithBinaryCounting(int n){
		if(n>=64){
			System.out.println("NO WAY for " + n);
			return;
		}
		long countMax = (long)Math.pow((int)2,(int)n)-1;
		for(long count = 0;count<=countMax; count++){
			System.out.print(count+": {");
			boolean isFirst = true;
			for(int i = 0; i < 64; i++){				
				if(((count>>i)&0x1)==1){
					System.out.print((isFirst?"":" ")+(i+1));
					isFirst = false;
				}
			}
			System.out.println("}");
		}
	}
	

	
	
	public static void main(String[] args) throws InterruptedException {
		//previousMain();
		NumericAlgorithms na = new NumericAlgorithms();
		na.generateSubsets(1000, 2);
		//na.generateSubsetsWithBinaryCounting(10);
		//na.generateSubsetsOrderedLexicographically(10);
	}

	private static void previousMain() throws InterruptedException {
		NumericAlgorithms nc = new NumericAlgorithms();
		//System.out.println(nc.convert(13123123123123123123l, 18));
		//System.out.println(nc.convert(1000000000000000000l, 18));
		int[] num = {0,0,0};
		int cnt = 0;
		while(cnt<1000){
			nc.increase(num, 2);
			for(int i = num.length-1; i >= 0; i--){
				System.out.print(num[i] + " ");
			}
			System.out.println();
			cnt++;
			Thread.sleep(250);
		}
	}

}
