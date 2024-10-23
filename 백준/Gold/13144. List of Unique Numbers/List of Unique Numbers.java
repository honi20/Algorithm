import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 100001;
	static final int PRE = 0;
	static final int AFTER = 1;
	
	static int N;
	static int[] numbers;
	static int[] pos;
	static int[][] dp;	// 뒤의 수열 중 현재 숫자와 중복되는 숫자가 위치하는 가장 가까운 위치
	static long result;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		numbers = new int[N];
		pos = new int[MAX];
		Arrays.fill(pos, -1);
		
		dp = new int[N][2];
		for (int idx = 0; idx < N; idx++) {
			dp[idx][PRE] = dp[idx][AFTER] = -1;
		}
		
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < N; idx++) {
			numbers[idx] = Integer.parseInt(st.nextToken());
			
			// 만약 해당 숫자가 이미 나온 경우
			if (pos[numbers[idx]] != -1) {
				int preIdx = pos[numbers[idx]];
				dp[preIdx][AFTER] = idx;
				dp[idx][PRE] = pos[numbers[idx]];
			}
			pos[numbers[idx]] = idx;
		}
		
		int minIdx = 0;
		for (int idx = 0; idx < N; idx++) {
			// 같은 숫자의 이전 위치가 minIdx보다 크거나 같은 경우
			if (dp[idx][PRE] != -1 && dp[idx][PRE] >= minIdx) {
				// 현재에서 이전까지의 값 중 중복되지 않는 숫자 개수 구하기
				int maxIdx = dp[idx][PRE];
				for (int k = dp[idx][PRE]+1; k <= idx; k++) {
					if (dp[k][AFTER] != -1 && dp[k][AFTER] < idx) {
						maxIdx = Math.max(maxIdx, dp[k][AFTER]);
					}
				}

				minIdx = maxIdx + 1;
			}
			
			result += (idx - minIdx + 1);
		}
		
		
		System.out.println(result);
	}
}
