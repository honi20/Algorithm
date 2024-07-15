import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int hillCnt;
	static List<Integer> hill;
	static int result;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		hillCnt = Integer.parseInt(br.readLine().trim());
		
		hill = new ArrayList<>();
		for (int idx = 0; idx < hillCnt; idx++) {
			hill.add(Integer.parseInt(br.readLine().trim()));
		}
		
		Collections.sort(hill);
		
		int minValue = hill.get(0);
		int maxValue = hill.get(hillCnt-1);
		
		result = Integer.MAX_VALUE;
		
		for (int idx = minValue; idx < maxValue; idx++) {
			int tmp = solve(idx, idx+17);
			result = Math.min(result, tmp);
		}
		
		if (result == Integer.MAX_VALUE)
			sb.append(0);
		else
			sb.append(result);
		
		System.out.println(sb);
	}
	
	public static int solve(int min, int max) {
		int tmp = 0;
		for (int idx = 0; idx < hillCnt; idx++) {
			int val = 0;
			
			if (hill.get(idx) > max) {
				val = (int) Math.pow(hill.get(idx) - max, 2);
			}
			else if (hill.get(idx) < min) {
				val = (int) Math.pow(min - hill.get(idx), 2);
			}
			
			tmp += val;
		}
		return tmp;
	}
}
