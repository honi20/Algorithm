import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 33_554_432;
	
	static boolean[] isInput;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		isInput = new boolean[MAX];
		Arrays.fill(isInput, false);
		
		String[] input = br.readLine().trim().split(" ");
		for (int idx = 0; idx < input.length; idx++) {
			int num = Integer.parseInt(input[idx]);
			
			// set에 포함되었는지 확인
			if (!isInput[num]) {
				sb.append(num).append(" ");
				isInput[num] = true;
			}
		}
		
		System.out.println(sb);
	}
}