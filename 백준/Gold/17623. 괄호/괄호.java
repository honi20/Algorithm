import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 1000;
	static final int LEFT = 0;
	static final int RIGHT = 1;
	
	static final String PARENTHESES = "()";
	static final String CURLY = "{}";
	static final String SQUARE = "[]";
	
	static final Map<Character, Integer> brackets;
	
	static {
		brackets = Map.of(
				'(', 1,
				')', 2,
				'{', 3,
				'}', 4,
				'[', 5,
				']', 6
				);
	}
	
	static int testCase;
	static int number;
	static String[] dp;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		init();
		
		while (testCase-- > 0) {
			number = Integer.parseInt(br.readLine().trim());
			
			sb.append(dp[number]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void init() {
		dp = new String[MAX + 1];
		Arrays.fill(dp, null);
		dp[1] = PARENTHESES;
		dp[2] = CURLY;
		dp[3] = SQUARE;
		
		for (int idx = 2; idx <= MAX; idx++) {
			// +1
			dp[idx] = getMinValue(dp[idx], getAddition(idx-1, PARENTHESES));
			
			// +2
			dp[idx] = getMinValue(dp[idx], getAddition(idx-2, CURLY));
			
			// +3
			dp[idx] = getMinValue(dp[idx], getAddition(idx-3, SQUARE));
			
			// *2
			dp[idx] = getMinValue(dp[idx], getMultiple(idx, 2, PARENTHESES));
			
			// *3
			dp[idx] = getMinValue(dp[idx], getMultiple(idx, 3, CURLY));
			
			// *5
			dp[idx] = getMinValue(dp[idx], getMultiple(idx, 5, SQUARE));
		}
	}
	
	private static String getAddition(int curIdx, String bracket) {
		if (curIdx <= 0) {
			return null;
		}
		
		String curStr = dp[curIdx];
		String addStr = bracket;
		
		return getMinValue(curStr+addStr, addStr+curStr);
	}
	
	private static String getMultiple(int curIdx, int denominator, String bracket) {
		if (curIdx % denominator != 0) {
			return null;
		}
		
		String curStr = dp[curIdx / denominator];
		
		return bracket.charAt(0) + curStr + bracket.charAt(1);
	}
	
	private static String getMinValue(String str1, String str2) {
		if (str1 == null) {
			return str2;
		}
		else if (str2 == null) {
			return str1;
		}
		
		if (str1.length() == str2.length()) {
			for (int idx = 0; idx < str1.length(); idx++) {
				int value1 = brackets.get(str1.charAt(idx));
				int value2 = brackets.get(str2.charAt(idx));
				
				if (value1 == value2) continue;
				
				return (value1 < value2) ? str1 : str2;
			}
		}
		
		return (str1.length() < str2.length()) ? str1 : str2;
	}
}
