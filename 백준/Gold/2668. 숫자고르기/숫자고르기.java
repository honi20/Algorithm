import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int arrSize;
	static int[] arr;
	static boolean[] isVisited;
	static boolean[] isSelected;
	static int resultCnt;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		arrSize = Integer.parseInt(br.readLine());
		arr = new int[arrSize + 1];

		for (int idx = 1; idx <= arrSize; idx++) {
			arr[idx] = Integer.parseInt(br.readLine().trim());
		}

		isVisited = new boolean[arrSize + 1];
		isSelected = new boolean[arrSize + 1];
		resultCnt = 0;
		for (int idx = 1; idx <= arrSize; idx++) {
			if (!isSelected[idx]) {
				Arrays.fill(isVisited, false);
				solve(idx, idx);
			}
		}

		sb.append(resultCnt).append("\n");
		for (int idx = 1; idx <= arrSize; idx++) {
			if (isSelected[idx]) {
				sb.append(idx).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void solve(int curNode, int startNode) {
		// 사이클이 완성된 경우
		if (isVisited[curNode] && (curNode == startNode)) {
			for (int node = 1; node <= arrSize; node++) {
				if (isVisited[node]) {
					isSelected[node] = true;
					++resultCnt;
				}
			}
		}

		// 연결된 노드로 이동
		int nextNode = arr[curNode];

		if (!isVisited[nextNode]) {
			isVisited[nextNode] = true;
			solve(nextNode, startNode);
		}
	}
}
