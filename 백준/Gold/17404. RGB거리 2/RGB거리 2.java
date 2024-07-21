import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int RED = 0;
	static final int GREEN = 1;
	static final int BLUE = 2;
	
	static int homeCnt;
	static int[][] home;
	static int[][] dp;
	static int result;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		homeCnt = Integer.parseInt(br.readLine().trim());
		
		home = new int[homeCnt][3];
		dp = new int[homeCnt][3];
	
		for (int idx = 0; idx < homeCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			home[idx][RED] = Integer.parseInt(st.nextToken());
			home[idx][GREEN] = Integer.parseInt(st.nextToken());
			home[idx][BLUE] = Integer.parseInt(st.nextToken());
		}
		
		result = Integer.MAX_VALUE;
		for (int start = 0; start < 3; start++) {
			for (int idx = 0; idx < 3; idx++) {
				if (start == idx) {
					dp[0][idx] = home[0][idx];
				}
				else {
					dp[0][idx] = 1010;
				}
			}
			
			for (int idx = 1; idx < homeCnt; idx++) {
				dp[idx][RED] = Math.min(dp[idx-1][GREEN], dp[idx-1][BLUE]) + home[idx][RED];
				dp[idx][GREEN] = Math.min(dp[idx-1][RED], dp[idx-1][BLUE]) + home[idx][GREEN];
				dp[idx][BLUE] = Math.min(dp[idx-1][RED], dp[idx-1][GREEN]) + home[idx][BLUE];
			}
			
			for (int idx = 0; idx < 3; idx++) {
				if (idx == start)
					continue;
				
				result = Math.min(result, dp[homeCnt-1][idx]);
			}
		}
		
		System.out.println(result);
	}
}
