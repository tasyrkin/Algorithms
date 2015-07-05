package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgramming {

	static int TRUE = 1;
	static int FALSE = 0;
	static int UNDEF = -1;
	static int[] stones = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] X = { 0, 1, 2, 3, 4, 1, 2, 3 };
		int n = X.length;
		int[] M = new int[n];
		int[] P = new int[n];
		int L = 0;
		int low = 0;
		int high = 0;
		for (int i = 0; i < n; i++) {
//			int low = 1;
//			int high = n - 1;
			int j = 0;
			while (low < high) {
//				j = ((low + high) >>> 1) + ((1==((low + high)&0x1))?1:0);
//				if(X[i]<=X[M[j]]){
//					high = j-1;
//				} else {
//					low = j;
//				}
				j = (low + high) >>> 1;
				if(X[M[j]]<X[i])low = j+1;
				else high = j;
			}
			j = high;
			P[i] = M[j];
			if(j==L||X[i]<X[M[j+1]]){
				M[j+1] = i;
				L = Math.max(L, j+1);
			}
		}
		System.out.println(L);
	}

	private static void stonesGameMain() {
		int[] a = { 2 };
		int num = 3;
		stones = new int[num + 1];
		Arrays.fill(stones, UNDEF);
		stonesGame(num, a);
		for (int i = 0; i < stones.length; i++) {
			if (i != 0)
				System.out.print(" ");
			System.out.print(i + ":" + stones[i]);
		}
		System.out.println(" ");
	}

	public static int stonesGame(int j, int[] a) {
		boolean lessThanAll = true;
		for (int i = 0; i < a.length; i++) {
			if (j >= a[i]) {
				lessThanAll = false;
				break;
			}
		}
		if (lessThanAll) {
			if (j >= 0)
				stones[j] = FALSE;
			return stones[j];
		}
		int cnt = 0;
		for (int i = 0; i < a.length; i++) {
			int curr = -UNDEF;
			if (j - a[i] >= 0 && stones[j - a[i]] != UNDEF) {
				curr = stones[j - a[i]];
			} else {
				curr = stonesGame(j - a[i], a);
				if (j - a[i] >= 0)
					stones[j - a[i]] = curr;
			}
			cnt += curr;
		}
		int res = UNDEF;
		if (cnt == a.length) {
			res = FALSE;
		} else {
			res = TRUE;
		}
		stones[j] = res;
		return stones[j];
	}

}
