import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 10001;
	
	static int testCase;
	static int[][] dp;
	static int num;
	
	public static void init() {
		dp = new int[MAX][4];
		dp[1][1] = 1;
		dp[2][1] = dp[2][2] = 1;
		dp[3][1] = dp[3][2] = dp[3][3] = 1;
		
		for (int idx = 4; idx < MAX; idx++) {
			dp[idx][1] = dp[idx-1][1];
			dp[idx][2] = dp[idx-2][1] + dp[idx-2][2];
			dp[idx][3] = dp[idx-3][1] + dp[idx-3][2] + dp[idx-3][3];
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		init();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			num = Integer.parseInt(br.readLine().trim());
			sb.append(dp[num][1] + dp[num][2] + dp[num][3]).append("\n");
		}
		
		System.out.println(sb);
	}
}