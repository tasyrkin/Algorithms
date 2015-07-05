package algorithms;

public class CountSort {

	public static void countSort(Integer[] array, int max){
		int[] indexArray = new int[max];
		for(int cnt=0; cnt < array.length; cnt++){
			indexArray[array[cnt]]++;
		}
		for(int cnt=0; cnt < indexArray.length; cnt++){
			if(indexArray[cnt]!=0){
				for(int cntIntern = 0; cntIntern < indexArray[cnt]; cntIntern++){
					System.out.print(cnt + ",");
				}
			}
		}
	}

	public static void countSortWithData(int[] a, String[]data){
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < a.length; i++){
			if(a[i]>max)max=a[i];
		}
		int[]num = new int[max+1];
		for(int i = 0; i < a.length; i++){
			num[a[i]]++;
		}		
		int[]pos = new int[max+1];
		for(int i = 1; i <= max; i++){
			pos[i] = pos[i-1] + num[i-1];
		}		
		for(int i = 0; i < pos.length; i++){
			System.out.print(pos[i] + " ");
		}
		System.out.println();
		int[] anew = new int[a.length];
		String[] datanew = new String[a.length];
		for(int i = 0; i < a.length; i++){
			anew[pos[a[i]]] = a[i];
			datanew[pos[a[i]]] = data[i];
			pos[a[i]]++;
		}
		for(int i = 0; i < a.length; i++){
			System.out.print("(" + anew[i] + "," + datanew[i] + ") ");
		}
		System.out.println();
	}

	public static void countSortWithDataWithPermutations(int[] a, String[]data){
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < a.length; i++){
			if(a[i]>max)max=a[i];
		}
		int[]num = new int[max+1];
		for(int i = 0; i < a.length; i++){
			num[a[i]]++;
		}		
		int[]pos = new int[max+1];
		for(int i = 1; i <= max; i++){
			pos[i] = pos[i-1] + num[i-1];
		}
		int[] perm = new int[a.length];
		for(int i = 0; i < a.length; i++){
			perm[pos[a[i]]] = i;
			pos[a[i]]++;
		}
		for(int i = 0; i < perm.length; i++){
			System.out.print(perm[i] + " ");
		}
		System.out.println();
		for(int i = 0; i < perm.length; i++){
			System.out.print("(" + a[perm[i]] + "," + data[perm[i]] + ") ");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		//		countSort(new Integer[]{10,10,10,9,8,7,6,5,4,3,4,2,1}, 1024);
//		//		System.out.println("");
//		//		countSort(new Integer[]{1,1,1,1,1,1,1,1,1,1}, 1024);
//		int[] a = {10, 15, 10, 5, 5, 10};
//		String[] data = {"Ten","Fifteen","Ten","Five","Five","Ten"};
//		//countSortWithData(a, data);
//		countSortWithDataWithPermutations(a, data);
		int[]a = {3,8,9,1,2};
		int[]p = {3,4,0,1,2};
		for(int i = 0; i < p.length; i++){
			if(i!=0)System.out.print(" ");
			System.out.print(a[p[i]]);
		}
		System.out.println();
	}

}
