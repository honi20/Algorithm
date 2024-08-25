import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int n, k;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new int[n + 1];
		dp = new int[k + 1];

		for (int idx = 1; idx <= n; idx++) {
			st = new StringTokenizer(br.readLine());
			arr[idx] = Integer.parseInt(st.nextToken());
		}

		for (int idx = 1; idx <= k; idx++) {
			dp[idx] = Integer.MAX_VALUE - 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = arr[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
			}
		}

		System.out.println(dp[k] == Integer.MAX_VALUE - 1 ? -1 : dp[k]);
	}
}