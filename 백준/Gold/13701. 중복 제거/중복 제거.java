import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 33_554_432;
	
	static BitSet bitset = new BitSet();
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String[] input = br.readLine().trim().split(" ");
		for (int idx = 0; idx < input.length; idx++) {
			int num = Integer.parseInt(input[idx]);
			
			// 입력된 수인지 확인
			if (!bitset.get(num)) {
				bitset.set(num);
				sb.append(num).append(" ");
			}
		}
		
		System.out.println(sb);
	}
}