package algorithms;

import java.util.Arrays;
import java.util.Stack;

public class DPHabr {
	public static void main(String[] args) {
		char[] arr = "ABACCBA".toCharArray();
		int n = arr.length;
		int[][] dp = new int[n][n];
//		int[][]ref = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
//			ref[i][i] = i;
		}
		for (int l = 1; l <= n + 1; l++) {
			for (int i = 0; i < n; i++) {
				int j = i + l;
				if(j>=n)continue;
				if (arr[i] == arr[j]) {
					if (j - i == 2) {
//						ref[i][i] = i+1;
						dp[i][j] = 3;
					} else {
						int min = 0;
						int idxI = -1;
						int idxJ = -1;
						for (int i1 = i + 1; i1 < j; i1++) {
							for (int j1 = i1 + 1; j1 < j; j1++) {
								if (min < dp[i1][j1]) {
									min = dp[i1][j1];
									idxI = i1;
									idxJ = j1;
								}
							}
						}
						dp[i][j] = min + 2;
//						ref[i][j] = idxI;
					}
				}
			}
		}
		int len = Integer.MIN_VALUE;
		int idxI = -1;
		int idxJ = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (len < dp[i][j]) {
					len = dp[i][j];
					idxI = i;
					idxJ = j;
				}
			}
		}
		System.out.println(len);
		Stack<Character> stack = new Stack<Character>();
		int currLen = len;
		while(currLen>0){
			stack.add(arr[idxI]);
			if(currLen==1)break;
			currLen-=2;
			for(int i = idxI+1; i < idxJ; i++){
				for(int j = idxJ; j >= i; j--){
					if(dp[i][j]==currLen){
						idxI = i;
						idxJ = j;
					}
				}
			}
		}
		String res = "";
		if(len%2==1){
			res += stack.pop();
		} 
		while(!stack.isEmpty()){
			char ch = stack.pop();
			res = ch + res + ch;
		}		
		System.out.println(res);
	}

	private static void longestIncreasingSubsequence() {
		int[] nums = { 2, 8, 5, 9, 12, 6 };
		int n = nums.length;
		int[] dp = new int[n];
		int[] prev = new int[n];
		dp[0] = 1;
		prev[0] = -1;
		for (int i = 1; i < n; i++) {
			int max = 0;
			int step = -1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					if (max < dp[j]) {
						max = dp[j];
						step = j;
					}
				}
				dp[i] = max + 1;
				if (dp[i] == 1) {
					prev[i] = -1;
				}
				prev[i] = step;
			}
		}
		int len = Integer.MIN_VALUE;
		int maxPos = -1;
		for (int i = 0; i < n; i++) {
			if (len < dp[i]) {
				len = dp[i];
				maxPos = i;
			}
		}
		System.out.println(len);
		while (maxPos >= 0) {
			System.out.print(nums[maxPos] + " ");
			maxPos = prev[maxPos];
			// --len;
		}
		System.out.println();
		System.out.println(Arrays.toString(dp));
		System.out.println(Arrays.toString(prev));
	}

	public static void lessWeightedPath() {
		int n = 4;
		int m = 4;
		int[][] matrix = { { 10, 4, 1, 10 }, { 4, 100, 9, 100 }, { 4, 10, 1, 5 }, { 4, 10, 1, 5 } };
		int[][] dp = new int[n][m];
		int[][] prev = new int[n][m];
		dp[0][0] = matrix[0][0];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0)
					continue;
				int min = Integer.MAX_VALUE;
				int up = Integer.MAX_VALUE;
				if (i > 0) {
					up = dp[i - 1][j];
				}
				int left = Integer.MAX_VALUE;
				if (j > 0) {
					left = dp[i][j - 1];
				}
				int upLeft = Integer.MAX_VALUE;
				if (i > 0 && j > 0) {
					upLeft = dp[i - 1][j - 1];
				}
				if (up <= left && up <= upLeft) {
					min = up;
					prev[i][j] = 0;
				} else if (upLeft <= up && upLeft <= left) {
					min = upLeft;
					prev[i][j] = 1;
				} else if (left <= upLeft && left <= up) {
					min = left;
					prev[i][j] = 2;
				}
				dp[i][j] = min + matrix[i][j];
			}
		}
		System.out.println(dp[n - 1][m - 1]);
		System.out.println("Path:");
		System.out.println(formatPair(n - 1, m - 1));
		myloop: for (int i = n - 1; i >= 0;) {
			for (int j = m - 1; j >= 0;) {
				if (i == 0 && j == 0) {
					break myloop;
				}
				if (prev[i][j] == 0) {
					System.out.println(formatPair(i - 1, j));
					--i;
				} else if (prev[i][j] == 1) {
					System.out.println(formatPair(i - 1, j - 1));
					--i;
					--j;
				} else if (prev[i][j] == 2) {
					System.out.println(formatPair(i, j - 1));
					--j;
				}
			}
		}
	}

	public static String formatPair(int i, int j) {
		return "(" + i + "," + j + ")";
	}

	public static void countOnesAndZeroes() {
		int n = 64;
		long[][] num = new long[n + 1][2];
		num[1][0] = 1;
		num[1][1] = 1;
		for (int i = 2; i <= n; i++) {
			num[i][0] = num[i - 1][0] + num[i - 1][1];
			num[i][1] = num[i - 1][0];
		}
		System.out.println(num[n][0] + num[n][1]);
	}
}