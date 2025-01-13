/**
 * [조건]
 * - 세트 마리 수는 피보나치 수열을 이용한다.
 * - 항상 '사람의 수 > 닭의 수'이어야 한다.
 */
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MIN = 0;
	static final int MAX = 1;
	
	static int personCnt;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		personCnt = Integer.parseInt(br.readLine().trim());
		
		if (personCnt < 4) {
			dp = new int[5][2];
		}
		else {
			dp = new int[personCnt+1][2];
		}
		
		initFibo();
		
		solve();
		
		System.out.println(dp[personCnt][MIN] + " " + dp[personCnt][MAX]);
	}
	
	public static void solve() {
		if (personCnt < 4) {
			return;
		}
		
		for (int idx = 4; idx <= personCnt; idx++) {
			for (int pre = 2; pre < idx; pre++) {
				dp[idx][MIN] = Math.min(dp[idx][MIN], dp[pre][MIN] + dp[idx-pre][MIN]);
				dp[idx][MAX] = Math.max(dp[idx][MAX], dp[pre][MAX] + dp[idx-pre][MAX]);
			}
		}
	}
	
	private static void initFibo() {
		for (int idx = 0; idx <= personCnt; idx++) {
			dp[idx][MIN] = 123_456_890;
			dp[idx][MAX] = 0;
		}

		dp[2][MIN] = dp[2][MAX] = 1;
		dp[3][MIN] = dp[3][MAX] = 2;
		
		int idx1 = 2;
		int idx2 = 3;
		while ((idx1 + idx2) <= personCnt) {
			dp[idx1+idx2][MIN] = dp[idx1+idx2][MAX] = dp[idx1][MIN] + dp[idx2][MIN];
			
			int tmp = idx1;
			idx1 = idx2;
			idx2 += tmp;
		}
	}
}
