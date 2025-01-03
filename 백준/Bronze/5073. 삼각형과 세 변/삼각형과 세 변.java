import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int a, b, c;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine().trim());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			if (a == 0 && b == 0 && c == 0) {
				break;
			}
			
			int sum = a + b + c;
			int maxNum = Math.max(a, Math.max(b, c));
			
			if (maxNum >= sum - maxNum) {
				sb.append("Invalid\n");
			}
			else {
				if (a == b && b == c) {
					sb.append("Equilateral\n");
				}
				
				else if (a == b || b == c || a == c) {
					sb.append("Isosceles\n");
				}
				
				else {
					sb.append("Scalene\n");
				}
			}
		}
		
		System.out.println(sb);
	}
}