/**
 * [조건]
 * - 명령 횟수: 1 ~ 10^5
 * - 사탕 총 개수 : 0 ~ 2 * 10^9
 */
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MAX = 1_000_000;
	
	static int height;
	static int inputCnt;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		init();
		
		inputCnt = Integer.parseInt(br.readLine().trim());
		while (inputCnt-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			int oper = Integer.parseInt(st.nextToken());
			
			if (oper == 1) {
				int value = Integer.parseInt(st.nextToken());
				
				int ans = query(1, 1, MAX, value);
				update(1, 1, MAX, ans, -1);
				
				sb.append(ans).append("\n");
			}
			else if (oper == 2) {
				int index = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				
				update(1, 1, MAX, index, value);
			}
		}
		System.out.println(sb);
	}
	
	private static int query(int node, int start, int end, int rank) {
		// 조건 범위 안에 속하는지 확인
		if (rank <= 0 || tree[node] < rank) {
			return -1;
		}
		
		// 순위에 해당하는 사탕 번호 반환
		if (start == end) {
			return start;
		}
		
		int mid = (start + end) / 2;
		return Math.max(query(node*2, start, mid, rank), query(node*2+1, mid+1, end, rank - tree[node*2]));
	}
	
	private static void update(int node, int start, int end, int index, int value) {
		if (index > end || index < start) {
			return;
		}
		
		tree[node] += value;
		
		if (start != end) {
			int mid = (start + end) / 2;
			update(node*2, start, mid, index, value);
			update(node*2+1, mid+1, end, index, value);
		}
	}
	
	// 트리 배열 초기화
	private static void init() {
		height = (int)Math.ceil(Math.log(MAX) / Math.log(2));
		tree = new int[1 << (height+1)];
		Arrays.fill(tree, 0);
	}
}
