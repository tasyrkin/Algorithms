package algorithms;

import java.util.Arrays;

public class LCS {

	public static int LCS(String a, String b) {
		char[] aArr = a.toCharArray();
		char[] bArr = b.toCharArray();
		int[][] lcsArray = new int[aArr.length][bArr.length];
		lcsArray[0][0] = aArr[0] == bArr[0] ? 1 : 0;
		for (int I = 0; I < aArr.length; I++) {
			for (int J = 0; J < bArr.length; J++) {
				int currMax = Integer.MIN_VALUE;
				for (int i = 0; i < I; i++) {
					for (int j = 0; j < J; j++) {
						if (currMax < lcsArray[i][j])
							currMax = lcsArray[i][j];
					}
				}
				lcsArray[I][J] = Math.max(lcsArray[I][J],
						(currMax == Integer.MIN_VALUE ? 0 : currMax)
								+ (aArr[I] == bArr[J] ? 1 : 0));
			}
		}
		// int max = Integer.MIN_VALUE;
		// for (int I = 0; I < aArr.length; I++) {
		// for (int J = 0; J < bArr.length; J++) {
		// if(max < lcsArray[I][J]){
		// max = lcsArray[I][J];
		// }
		// }
		// }
		return lcsArray[aArr.length - 1][bArr.length - 1];
	}

	public static int[][] array = null;

	public static int LCS2(char[] a, char[] b, int i, int j) {
		if (i < 0 || j < 0)
			return 0;
		if (array[i][j] > 0)
			return array[i][j];
		int equal = (a[i] == b[j] ? 1 : 0) + LCS2(a, b, i - 1, j - 1);
		int lessI = LCS2(a, b, i - 1, j);
		int lessJ = LCS2(a, b, i, j - 1);
		int res = Math.max(equal, Math.max(lessI, lessJ));
		array[i][j] = res;
		return array[i][j];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] a = "a1b1c1d1e111".toCharArray();
		char[] b = "bcde11111".toCharArray();
		array = new int[a.length][b.length];
		int lcsNum = LCS2(a, b, a.length - 1, b.length - 1);
		System.out.println(lcsNum);
		for (int i = 0; i < array.length; i++) {
			System.out.println(Arrays.toString(array[i]));
		}
		char[] result = new char[lcsNum];
		int i = a.length - 1;
		int j = b.length - 1;
		while (lcsNum > 0) {
			int curr = array[i][j];
			boolean need_i = false;
			for (int cnt_i = i - 1; cnt_i >= 0; cnt_i--) {
				if (array[i][j] == array[cnt_i][j] + 1) {
					i = cnt_i + 1;
					need_i = true;
					break;
				}
			}
			boolean need_j = false;
			for (int cnt_j = j - 1; cnt_j >= 0; cnt_j--) {
				if (array[i][j] == array[i][cnt_j] + 1) {
					j = cnt_j + 1;
					need_j = true;
					break;
				}
			}
			result[--lcsNum] = a[i];
			if(need_i)i--;
			if(need_j)j--;
		}
		// while (lcsNum > 0) {
		// int num = Integer.MAX_VALUE;
		// if (i > 0 && j > 0) {
		// num = array[i - 1][j - 1];
		// }
		// int num1 = Integer.MAX_VALUE;
		// if (i > 0) {
		// num1 = array[i - 1][j];
		// }
		// int num2 = Integer.MAX_VALUE;
		// if (j > 0) {
		// num2 = array[i][j - 1];
		// }
		// if ((num == lcsNum - 1 && num1 == lcsNum - 1 && num2 == lcsNum - 1)
		// || (i == 0 && j == 0)) {
		// result[--lcsNum] = a[i];
		// } else {
		// if (lcsNum == num) {
		// i--;
		// j--;
		// } else if (lcsNum == num1) {
		// i--;
		// } else {
		// j--;
		// }
		// }
		// }
		// while (lcsNum > 0) {
		// int num = array[i - 1][j - 1];
		// int num1 = Integer.MIN_VALUE;
		// if (j < b.length) {
		// num1 = array[i - 1][j];
		// }
		// int num2 = Integer.MIN_VALUE;
		// if (i < a.length) {
		// num2 = array[i][j - 1];
		// }
		// //int max = Math.max(num, Math.max(num1, num2));
		// if (lcsNum == num && a[i - 1] == b[j - 1]) {
		// result[--lcsNum] = a[i - 1];
		// i--;
		// j--;
		// } else if (lcsNum == num1 && j < b.length && a[i - 1] == b[j]) {
		// result[--lcsNum] = a[i - 1];
		// i--;
		// } else if (lcsNum == num2 && i < a.length && a[i] == b[j - 1]) {
		// result[--lcsNum] = a[j - 1];
		// j--;
		// }
		// }
		// for (int i = a.length - 1; i > 0; i--) {
		// for (int j = b.length - 1; j > 0; j--) {
		// int num = array[i][j];
		// if (num > array[i - 1][j] && num > array[i][j - 1]){
		// result[--lcsNum] = a[i];
		// }
		// }
		// }
		System.out.println(Arrays.toString(result));
	}

}
