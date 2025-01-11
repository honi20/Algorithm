import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 1000;
	static final int[] PARENTHESES = {1,2};
	static final int[] CURLY = {3,4};
	static final int[] SQUARE = {5,6};
	
	static int testCase;
	static int number;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		init();
		
		while (testCase-- > 0) {
			number = Integer.parseInt(br.readLine().trim());
			
			sb.append(changeToString(dp[number])).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static String changeToString(int value) {
		String result = "";
		
		while (value > 0) {
			int num = value % 10;
			
			if (num == PARENTHESES[0]) {
				result = '(' + result;
			}
			else if (num == PARENTHESES[1]) {
				result = ')' + result;
			}
			else if (num == CURLY[0]) {
				result = '{' + result;
			}
			else if (num == CURLY[1]) {
				result = '}' + result;
			}
			else if (num == SQUARE[0]) {
				result = '[' + result;
			}
			else if (num == SQUARE[1]) {
				result = ']' + result;
			}
			
			value /= 10;
		}
		
		return result;
	}
	
	private static void init() {
		dp = new int[MAX+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = getValueOfBracket(PARENTHESES);
		dp[2] = getValueOfBracket(CURLY);
		dp[3] = getValueOfBracket(SQUARE);
		
		for (int idx = 2; idx <= MAX; idx++) {
			// [idx-1] + 1
			dp[idx] = Math.min(dp[idx], getAdditionValue(idx, 1, PARENTHESES));
			
			// [idx-2] + 2
			dp[idx] = Math.min(dp[idx], getAdditionValue(idx, 2, CURLY));
			
			// [idx-3] + 3
			dp[idx] = Math.min(dp[idx], getAdditionValue(idx, 3, SQUARE));
			
			// [idx/2] * 2
			dp[idx] = Math.min(dp[idx], getMultipleValue(idx, 2, PARENTHESES));
			
			// [idx/3] * 3
			dp[idx] = Math.min(dp[idx], getMultipleValue(idx, 3, CURLY));
			
			// [idx/5] * 5
			dp[idx] = Math.min(dp[idx], getMultipleValue(idx, 5, SQUARE));
		}
	}
	
	private static int getAdditionValue(int index, int add, int[] brackets) {
		if (index - add < 1) {
			return Integer.MAX_VALUE;
		}
		
		int curValue = dp[index - add];
		int addValue = getValueOfBracket(brackets);
		
		// sum과 curValue의 상위 두 자리를 비교하여 위치 순서 결정
		if (isSmaller(addValue, curValue)) {
			int digit = getDigitOfNum(curValue);
			
			return addValue * (int) Math.pow(10, digit) + curValue;
		}
		
		return curValue * 100 + addValue;
	}
	
	private static int getMultipleValue(int index, int multiple, int[] brackets) {
		if (index % multiple != 0) {
			return Integer.MAX_VALUE;
		}
		
		int digit = getDigitOfNum(dp[index / multiple]);		
		
		return brackets[0] * (int) Math.pow(10, digit+1) + dp[index/multiple] * 10 + brackets[1];
	}
	
	private static boolean isSmaller(int base, int cmp) {
		int baseDigit = getDigitOfNum(base);
		int cmpDigit = getDigitOfNum(cmp);
		
		if (baseDigit < cmpDigit) {
			base *= Math.pow(10, cmpDigit-baseDigit);
		}
		else if (baseDigit > cmpDigit) {
			cmp *= Math.pow(10, baseDigit-cmpDigit);
		}
		
		return base < cmp;
	}
	
	private static int getDigitOfNum(int number) {
		int digit = 0;
		
		while (number > 0) {
			number /= 10;
			++digit;
		}
		
		return digit;
	}
	
	private static int getValueOfBracket(int[] brackets) {
		return 10 * brackets[0] + brackets[1];
	}
}
