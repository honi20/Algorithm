import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int arrSize;
	static String[] arr;
	static int MOD;

	static int totalLen;
	static long denominator, numerator;

	static long[] remainder;
	static long[] remainder10;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		arrSize = Integer.parseInt(br.readLine().trim());

		arr = new String[arrSize];
		totalLen = 0;
		denominator = 1;
		for (int idx = 0; idx < arrSize; idx++) {
			arr[idx] = br.readLine().trim();
			totalLen += arr[idx].length();
			denominator *= (idx + 1);
		}

		MOD = Integer.parseInt(br.readLine().trim());

		initRemainder();
		initDp();

		numerator = solve(0, 0);

		if (numerator == 0) {
			denominator = 1;
		} 
		else {
			long tmp = gcd(numerator, denominator);
			numerator /= tmp;
			denominator /= tmp;
		}

		System.out.println(numerator + "/" + denominator);
	}

	private static long gcd(long num1, long num2) {
		// num1이 큰 수가 되도록 수정
		if (num1 < num2) {
			long tmp = num1;
			num1 = num2;
			num2 = tmp;
		}

		while (num2 != 0) {
			long tmp = num1 % num2;
			num1 = num2;
			num2 = tmp;
		}

		return num1;
	}

	private static long solve(int state, int remain) {
		// 수열의 모든 원소를 사용했을 경우, 나머지가 0인지 확인
		if (state == ((1 << arrSize) - 1)) {
			return (remain == 0) ? 1 : 0;
		}

		// 이미 값이 존재하는 경우, 패스
		if (dp[state][remain] != -1) {
			return dp[state][remain];
		}

		dp[state][remain] = 0;

		// 다른 원소를 뒤에 추가하는 경우 탐색
		for (int idx = 0; idx < arrSize; idx++) {
			// 이미 포함된 원소인 경우, 패스
			if ((state & (1 << idx)) == (1 << idx)) {
				continue;
			}

			int nextState = state | (1 << idx);
			int nextRemain = (int) ((remain * remainder10[arr[idx].length()] + remainder[idx]) % MOD);
			dp[state][remain] += solve(nextState, nextRemain);
		}

		return dp[state][remain];
	}

	private static void initDp() {
		dp = new long[(1 << arrSize)][MOD];
		for (int idx = 0; idx < (1 << arrSize); idx++) {
			Arrays.fill(dp[idx], -1);
		}
	}

	private static void initRemainder() {
		// 수열의 원소들의 나머지
		remainder = new long[arrSize];
		for (int idx = 0; idx < arrSize; idx++) {
			remainder[idx] = getRemainder(arr[idx]);
		}

		// 10의 배수들의 나머지
		remainder10 = new long[totalLen + 1];
		remainder10[0] = 1;
		for (int idx = 1; idx <= totalLen; idx++) {
			remainder10[idx] = remainder10[idx - 1] * 10 % MOD;
		}
	}

	private static long getRemainder(String number) {
		long result = 0;

		for (int idx = 0; idx < number.length(); idx++) {
			int num = number.charAt(idx) - '0';

			result = (result * 10 + num) % MOD;
		}

		return result;
	}
}
