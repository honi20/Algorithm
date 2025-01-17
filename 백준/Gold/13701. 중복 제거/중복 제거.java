import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static Set<Integer> numbers;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		numbers = new HashSet<>();
		
		String[] input = br.readLine().trim().split(" ");
		for (int idx = 0; idx < input.length; idx++) {
			int num = Integer.parseInt(input[idx]);
			
			// set에 포함되었는지 확인
			if (!numbers.contains(num)) {
				sb.append(num).append(" ");
				numbers.add(num);
			}
		}
		
		System.out.println(sb);
	}
}