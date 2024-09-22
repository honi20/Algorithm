import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final String[] STAR = {
			"  *  ",
			" * * ",
			"*****"
	};
	static int lineCnt;
	static char[][] result;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		lineCnt = Integer.parseInt(br.readLine().trim());
		
		result = new char[lineCnt][lineCnt*2-1];
		
		for (int idx = 0; idx < lineCnt; idx++) {
			Arrays.fill(result[idx], ' ');
		}
		
		solve(0, lineCnt-1, lineCnt);
		
		for (int i = 0; i < lineCnt; i++) {
			for (int j = 0; j < lineCnt * 2 - 1; j++) {
				sb.append(result[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void solve(int row, int col, int N) {
		if (N == 3) {
			result[row][col] = '*';
			result[row+1][col-1] = result[row+1][col+1] = '*';
			result[row+2][col-2] = result[row+2][col-1] = result[row+2][col] 
					= result[row+2][col+1] = result[row+2][col+2] = '*';		
			return;
		}
		else {
			int tmp = N / 2;
			solve(row, col, tmp);
			solve(row+tmp, col-tmp, tmp);
			solve(row+tmp, col+tmp, tmp);
		}
	}
}