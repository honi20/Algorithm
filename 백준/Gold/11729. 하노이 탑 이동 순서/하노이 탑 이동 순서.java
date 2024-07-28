/**
 * 1. 원판의 개수를 입력받는다.
 * 2. 원판을 세 번째 장대로 옮기는 데 필요한 최소 횟수를 구한다.
 * 3. 세 번째 장대로 옮기는 이동 과정을 구한다.
 */
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int cnt;
	
	public static void hanoi(int cnt, int from, int mid, int to) {
		if (cnt >= 1) {
			hanoi(cnt-1, from, to, mid);
			sb.append(from).append(" ").append(to).append("\n");
			hanoi(cnt-1, mid, from, to);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 원판의 개수를 입력받는다.
		cnt = Integer.parseInt(br.readLine().trim());
		
		// 2. 원판을 세 번째 장대로 옮기는 데 필요한 최소 횟수를 구한다.
		sb.append((int)Math.pow(2, cnt)-1).append("\n");
		
		// 3. 세 번째 장대로 옮기는 이동 과정을 구한다.
		hanoi(cnt, 1, 2, 3);
		
		System.out.println(sb);
	}
}