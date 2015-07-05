package structures.strings;

import java.util.Arrays;

public class ZFunction {

	public static int[] zFunction2(String string) {
		char[] str = string.toCharArray();
		int len = str.length;
		int[] Z = new int[len];

		if (0 == len) {
			return Z;
		}

		Z[0] = len;

		// main cycle over the string
		for (int curr = 1, left = 0, right = 1; curr < len; ++curr) {
			// the rightmost buffer is less or equal the current position
			// then look for the chars equalities from the string beginning
			if (curr >= right) {
				int off = 0;
				while (curr + off < len && str[curr + off] == str[off]) {
					++off;
				}
				Z[curr] = off;
				right = curr + Z[curr];
				left = curr;
				// the rightmost buffer contains the current position
			} else {
				int equiv = curr - left;
				// if Z[equiv] is less than the number of chars till the end of
				// the buffer
				// then Z[curr] = Z[equiv]
				if (Z[equiv] < right - curr) {
					Z[curr] = Z[equiv];
					// if equivalent is more or equal than the number of chars
					// till the end of the buffer
					// then check chars at the end of the buffer and
				} else {
					int off = 0;
					while (right + off < len && str[right - curr + off] == str[right + off]) {
						++off;
					}
					Z[curr] = right - curr + off;
					right += off;
					left = curr;
				}
			}
		}
		return Z;
	}

	public static int[] zFunction(String string) {
		char[] arr = string.toCharArray();
		int[] Z = new int[arr.length];
		if (arr.length == 0) {
			return Z;
		}
		Z[0] = arr.length;
		int i = 1;
		int r = 0;
		while (arr[i] == arr[r]) {
			i++;
			r++;
		}
		Z[1] = r;
		i = 2;
		r = 1;
		int rEnd = r;
		while (i < arr.length) {
			int k = i - r;
			if (Z[k] < rEnd - i + 1) {
				Z[i] = Z[k];
			} else {
				int j1 = -1;
				int j2 = -1;
				if (rEnd < i) {
					j1 = 0;
					j2 = i;
					int h = 0;
					while (j1 < arr.length && j2 < arr.length && arr[j1] == arr[j2]) {
						j1++;
						j2++;
						h++;
					}
					Z[i] = h;
					r = i;
					if (h == 0) {
						rEnd = i;
					} else {
						rEnd = j2 - 1;
					}
				} else {
					j1 = rEnd - i + 1;
					j2 = rEnd + 1;
					int h = 0;
					while (j1 < arr.length && j2 < arr.length && arr[j1] == arr[j2]) {
						j1++;
						j2++;
						h++;
					}
					Z[i] = rEnd - i + 1 + h;
					r = i;
					rEnd = j2 - 1;
				}
			}
			i++;
		}
		return Z;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int[] res = ZFunction.zFunction("abca", "ababcabcacab");
		System.out.println(Arrays.toString(ZFunction.zFunction("abcaababcabcacab")));
		System.out.println(Arrays.toString(ZFunction.zFunction2("abca$ababcabcacab")));
		System.out.println(Arrays.toString(ZFunction.zFunction("aaaaaaabaaaa")));
		System.out.println(Arrays.toString(ZFunction.zFunction2("aaa$aaaabaaaa")));
	}

}
