import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int solutionCnt;
	static List<Integer> solutions;
	static int result1 = 0, result2 = 0;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		solutionCnt = Integer.parseInt(br.readLine().trim());
		
		solutions = new ArrayList<>();
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < solutionCnt; idx++) {
			solutions.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(solutions);
		
		int start = 0;
		int end = solutionCnt - 1;
		int minVal = Integer.MAX_VALUE;
		
		while (start < end) {
			int sum = solutions.get(start) + solutions.get(end);
			
			if (minVal > Math.abs(sum)) {
				minVal = Math.abs(sum);
				result1 = solutions.get(start);
				result2 = solutions.get(end);
				
				if (sum == 0)
					break;
			}
			
			if (sum < 0)
				++start;
			else
				--end;
		}
		
		System.out.println(result1 + " " + result2);
	}
}