/**
 * 1. 아이들의 수가 입력된다.
 * 2. 아이들 번호가 입력된다.
 * 3. 수가 오름차순으로 증가하는 최대 개수를 구한다.
 */
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int studentCnt;
	static int[] students;
	static int[] dp;
	static int maxVal = 0;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 아이들의 수가 입력된다.
		studentCnt = Integer.parseInt(br.readLine().trim());
		
		// 2. 아이들 번호가 입력된다.
		students = new int[studentCnt];
		for (int idx = 0; idx < studentCnt; idx++) {
			students[idx] = Integer.parseInt(br.readLine().trim());
		}
		
		// 3. 수가 오름차순으로 증가하는 최대 개수를 구한다.
		dp = new int[studentCnt];
		Arrays.fill(dp, 1);
		
		for (int idx = 1; idx < studentCnt; idx++) {
			for (int pre = 0; pre < idx; pre++) {
				// 만약 해당 값보다 증가하는 값
				if (students[pre] < students[idx]) {
					dp[idx] = Math.max(dp[idx], dp[pre]+1);
				}
			}
			maxVal = Math.max(maxVal, dp[idx]);
		}
		System.out.println(studentCnt - maxVal);
	}
}