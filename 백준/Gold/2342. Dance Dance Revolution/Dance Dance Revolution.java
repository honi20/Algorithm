import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int TO_SAME = 1;
	static final int FROM_CENTER = 2;
	static final int TO_ADJACENCY = 3;
	static final int TO_OPPOSITE = 4;
	
	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int WEIGHT = 2;
	
	static List<Integer> orders;
	static int[][][] dp;
	static int minResult = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		orders = new ArrayList<>();
		
		String[] arr = br.readLine().trim().split(" ");
		
		for (int idx = 0; idx < arr.length-1; idx++) {
			orders.add(Integer.parseInt(arr[idx]));
		}
		
		initDp();
		
		solve();
		
		for (int left = 0; left < 5; left++) {
			for (int right = 0; right < 5; right++) {
				minResult = Math.min(minResult, dp[orders.size()][left][right]);
			}
		}
		
		System.out.println(minResult);
	}
	
	private static void solve() {
		for (int idx = 1; idx <= orders.size(); idx++) {
			int curPos = orders.get(idx-1);
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					dp[idx][curPos][i] = Math.min(dp[idx][curPos][i], dp[idx-1][i][j] + getWeight(j, curPos));
					dp[idx][i][curPos] = Math.min(dp[idx][i][curPos], dp[idx-1][i][j] + getWeight(j, curPos));
				}
			}
		}
	}
	
	private static void initDp() {
		dp = new int[orders.size()+1][5][5];
		
		for (int idx = 0; idx <= orders.size(); idx++) {
			for (int k = 0; k < 5; k++) {
				Arrays.fill(dp[idx][k], 1234567890);
			}
		}
		
		dp[0][0][0] = 0;
	}
	
	private static int getWeight(int curIdx, int nextIdx) {
		if (curIdx == 0) {
			return FROM_CENTER;
		}
		else if (curIdx == nextIdx) {
			return TO_SAME;
		}
		else if (Math.abs(curIdx - nextIdx) == 2) {
			return TO_OPPOSITE;
		}
		
		return TO_ADJACENCY;
	}
}
