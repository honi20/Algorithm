import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine().trim());
		String str = br.readLine().trim();
		
		int result = Integer.MAX_VALUE;
		result = Math.min(result, solveFront('R', 'B', N, str));
		result = Math.min(result, solveFront('B', 'R', N, str));
		result = Math.min(result, solveBack('R', 'B', N, str));
		result = Math.min(result, solveBack('B', 'R', N, str));
		
		System.out.println(result);
	}
	
	private static int solveBack(int baseColor, int otherColor, int N, String str) {
		boolean isFirst = true;
		int cnt = 0;
		
		for (int idx = N - 1; idx >= 0; idx--) {
			if (isFirst && str.charAt(idx) == otherColor) {
				isFirst = false;
			}
			
			if (!isFirst && str.charAt(idx) == baseColor) {
				++cnt;
			}
		}
		
		return cnt;
	}
	
	private static int solveFront(int baseColor, int otherColor, int N, String str) {
		boolean isFirst = true;
		int cnt = 0;
		
		for (int idx = 0; idx < N; idx++) {
			if (isFirst && str.charAt(idx) == otherColor) {
				isFirst = false;
			}
			
			if (!isFirst && str.charAt(idx) == baseColor) {
				++cnt;
			}
		}
		
		return cnt;
	}
}