package algorithms;

public class Searches {
	public static void main(String[] args) {
	}

	private static void binarySearch() {
		int[] arr = { 1, 2, 3, 4, 5, 6, 9, 10, 10, 10, 10, 15 };
		int i = 0;
		int j = arr.length - 1;
		int num = 9;
		int idx = -1;
		boolean isFound = false;
		while (i <= j) {
			idx = (j + i) >>> 1;
			if (arr[idx] == num) {
				isFound = true;
				break;
			}
			if (arr[idx] < num)
				i = idx + 1;
			if (arr[idx] > num)
				j = idx - 1;
		}
		System.out.println(isFound ? idx : -1);
	}
}
