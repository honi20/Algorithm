import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int testCase;
	static int x, y;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			y -= x;
			int tmp = (int) Math.sqrt(y);
			y -= tmp * tmp;
			
			sb.append(2*tmp - 1 + (y / tmp) + (y % tmp == 0 ? 0 : 1));
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
