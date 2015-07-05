package algorithms;
import java.io.*;
import java.util.*;

public class ksort_pk implements Runnable {
	public static void main(String[] args) {
		new Thread(new ksort_pk()).start();
	}

	BufferedReader br;
	StringTokenizer in;
	PrintWriter out;
	int ans;
	int[] a;

	public String nextToken() throws IOException {
		while (in == null || !in.hasMoreTokens()) {
			in = new StringTokenizer(br.readLine());
		}
		return in.nextToken();
	}

	public int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	public long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	public double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}

	public void swap(int b, int c) {
		int t = a[b];
		a[b] = a[c];
		a[c] = t;
		ans++;
	}
	
	public void solve() throws IOException {
		int n = nextInt();
		assert(n >= 1);
		assert(n <= 300);
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			assert(a[i] >= 1);
			assert(a[i] <= 1000000000);
		}
		ans = 0;
		int k = nextInt();
		assert(k >= 1);
		assert(k < n);
		for (int i = 0; i < n; i++)
			for (int j = k; j < n; j++)
				if (a[j] < a[j - k]) 
					swap(j, j - k);
		for (int i = 1; i < n; i++)
			if (a[i] < a[i - 1])
				ans = -1;
		out.println(ans);
	}

	public void run() {
		try {
			br = new BufferedReader(new FileReader("ksort.in"));
			out = new PrintWriter("ksort.out");

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}