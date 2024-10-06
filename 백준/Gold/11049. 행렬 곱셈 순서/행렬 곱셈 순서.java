import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int n;
	static int[][] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine().trim());
		
		arr = new int[n+1][2];
		for (int idx = 1; idx <= n; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			arr[idx][0] = Integer.parseInt(st.nextToken());
			arr[idx][1] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n+1][n+1];
		for (int idx = 1; idx < n; idx++) {
			dp[idx][idx] = 0;
			dp[idx][idx+1] = arr[idx][0] * arr[idx][1] * arr[idx+1][1];
		}
		
		for (int idx = 3; idx <= n; idx++) {
			for (int i = 1; i <= n-idx+1; i++) {
				int j = i + idx - 1;
				
				dp[i][j] = Integer.MAX_VALUE;
				
				for (int k = i; k < j; k++) {
					int val = dp[i][k] + dp[k+1][j] + (arr[i][0] * arr[k][1] * arr[j][1]);
					dp[i][j] = Math.min(dp[i][j], val);
				}
			}
		}
		System.out.println(dp[1][n]);
	}
}
