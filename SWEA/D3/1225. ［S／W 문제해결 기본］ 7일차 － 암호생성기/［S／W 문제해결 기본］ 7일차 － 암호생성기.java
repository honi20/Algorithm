/**
 * 1. 테스트케이스 번호를 입력한다.
 * 2. 8개의 데이터가 주어진다.
 * 3. 암호 배열을 찾을 때까지 반복한다.
 * 	3-1. 한 사이클 안에서 큐의 맨 앞에 있는 수를 1~5를 뺀 후 뒤로 이동한다.
 * 	3-2. 만약 맨 앞의 수에서 감소시킨 결과가 0보다 같거나 작으면 반복을 종료한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int TESTCASE = 10;
	static final int PWD_COUNT = 8;
	static final int CYCLE_COUNT = 5;
	
	static Queue<Integer> queue;
	static int number;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		queue = new ArrayDeque<Integer>();
		
		for (int tc = 1; tc <= TESTCASE; tc++) {
			// 1. 테스트케이스 번호를 입력한다.
			br.readLine().trim();
			
			// 2. 8개의 데이터가 주어진다.
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < PWD_COUNT; idx++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			boolean flag = false;	// 0이 나왔는지 여부
			// 3. 암호 배열을 찾을 때까지 반복한다.
			while (true) {
				for (int minus = 1; minus <= CYCLE_COUNT; minus++) {
					// 3-1. 한 사이클 안에서 큐의 맨 앞에 있는 수를 1~5를 뺀 후 뒤로 이동한다.
					int frontNum = queue.poll();
					int pushNum = (frontNum - minus < 0) ? 0 : frontNum - minus;
					queue.offer(pushNum);
					
					// 3-2. 만약 맨 앞의 수에서 감소시킨 결과가 0보다 같거나 작으면 반복을 종료한다.
					if (pushNum == 0) {
						flag = true;
						break;
					}
				}
				
				if (flag)
					break;
				
			}
			
			sb.append("#").append(tc).append(" ");
			while (!queue.isEmpty()) {
				sb.append(queue.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
