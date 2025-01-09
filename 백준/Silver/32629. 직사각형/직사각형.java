import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int MAX = 1000_000_000;
	
	static int testCase;
	static int rectCnt;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		while (testCase-- > 0) {
			rectCnt = Integer.parseInt(br.readLine().trim());
			
			sb.append(solve()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int solve() {
		long start = 1;
		long end = rectCnt;
		
		while (start < end) {
			long mid = (start + end) / 2;
			
			long total = (mid / 2 + mid % 2) * (mid / 2 + 1);
			
			if (total < rectCnt) {
				start = mid + 1;
			}
			else {
				end = mid;
			}
		}
		
		return 4 + 2 * (int)(start-1);
	}
}