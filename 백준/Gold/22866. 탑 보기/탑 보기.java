import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int LEFT = 0;
	static final int RIGHT = 1;

	static int buildingCnt;
	static int[] height;

	static Stack<Integer> stack;
	static int[][] cnt;
	static int[] minIdx;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		buildingCnt = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());

		height = new int[buildingCnt + 1];
		for (int idx = 1; idx <= buildingCnt; idx++) {
			height[idx] = Integer.parseInt(st.nextToken());
		}

		stack = new Stack<>();
		cnt = new int[buildingCnt + 1][2];
		minIdx = new int[buildingCnt + 1];
		Arrays.fill(minIdx, Integer.MAX_VALUE);

		// 왼쪽부터 확인
		solve(1, +1, LEFT);
		
		// 오른쪽 확인
		solve(buildingCnt, -1, RIGHT);

		getResult();

		System.out.println(sb);
	}

	private static void solve(int curIdx, int tmp, int direct) {
		stack.clear();

		while (true) {
			if (curIdx < 1 || curIdx > buildingCnt) {
				break;
			}

			// 스택의 맨 위 값이 현재 건물의 높이보다 높을 때까지 pop
			while (!stack.isEmpty() && height[stack.peek()] <= height[curIdx]) {
				stack.pop();
			}

			// 현재 스택에 남은 개수 = 볼 수 있는 건물 개수
			cnt[curIdx][direct] = stack.size();

			// 스택의 맨 위 건물의 인덱스
			if (!stack.isEmpty()) {
				minIdx[curIdx] = getMinIdx(minIdx[curIdx], stack.peek(), curIdx);
			}

			stack.push(curIdx);
			curIdx += tmp;
		}
	}

	private static int getMinIdx(int idx1, int idx2, int base) {
		int diff1 = Math.abs(base - idx1);
		int diff2 = Math.abs(base - idx2);

		if (diff1 < diff2) {
			return idx1;
		}
		else if (diff1 > diff2) {
			return idx2;
		}

		return Math.min(idx1, idx2);
	}

	private static void getResult() {
		for (int idx = 1; idx <= buildingCnt; idx++) {
			sb.append(cnt[idx][LEFT] + cnt[idx][RIGHT]);

			if (cnt[idx][LEFT] + cnt[idx][RIGHT] > 0) {
				sb.append(" ").append(minIdx[idx]);
			}

			sb.append("\n");
		}
	}
}