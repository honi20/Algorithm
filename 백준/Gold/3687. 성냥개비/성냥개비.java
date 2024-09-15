import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 101;
	
	static int testCase;
	static int number;
	static long[] minDp;
	static String[] maxDp;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		init();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < testCase; tc++) {
			number = Integer.parseInt(br.readLine().trim());
			System.out.println(minDp[number] + " " + maxDp[number]);
		}
	}
	
	private static void init() {
		minDp = new long[MAX];
		maxDp = new String[MAX];
		
		Arrays.fill(minDp, Long.MAX_VALUE);
		minDp[2] = 1;
		minDp[3] = 7;
		minDp[4] = 4;
		minDp[5] = 2;
		minDp[6] = 6;
		minDp[7] = 8;
		minDp[8] = 10;
		
		String[] arr = {"1", "7", "4", "2", "0", "8"};
		for (int idx = 9; idx < MAX; idx++) {
			for (int k = 2; k <= 7; k++) {
				String num = minDp[idx-k] + arr[k-2];
				minDp[idx] = Math.min(Long.parseLong(num), minDp[idx]);
			}
		}
		
		String[] arr2 = {"0", "0", "1", "7", "4", "2", "6", "8"};
		maxDp[2] = "1";
		for (int idx = 3; idx < MAX; idx++) {
			String num = "";
			
			if (idx % 2 == 0) {
				for (int k = 0; k < idx/2; k++) {
					num += "1";
				}
			}
			else {
				int tmp = idx/2 - 1;
				for (int k = 0; k < tmp; k++) {
					num += "1";
				}
				num = arr2[idx - (tmp*2)] + num;
			}
			
			maxDp[idx] = num;
		}
	}
}