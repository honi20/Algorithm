import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 100_000;

	static int inputCnt;
	static int[] arr;		// 인덱스만큼의 선물 수를 가진 상자 개수 저장
	static int childCnt;
	static int[] present;	// 아이들이 원하는 선물 개수
	static int[] box;		// 아이들의 배려심
	static int[] tree;

	public static void main(String[] args) throws Exception {
		input();

		init();

		for (int idx = 0; idx < childCnt; idx++) {
			// 현재 학생이 받을 상자의 선물 개수 탐색
			int rank = getRank(box[idx]);
			int presentCnt = query(1, 0, MAX, rank);

			// 상자의 선물 개수가 원하는 선물 수보다 작은 경우, 0 출력 후 종료
			if (presentCnt < present[idx]) {
				System.out.println("0");
				return;
			}

			update(1, 0, MAX, presentCnt, -1);
			update(1, 0, MAX, presentCnt - present[idx], 1);
		}

		System.out.println("1");
	}

	// 리프노트가 오름차순 정렬되어 있기 때문에 순위 변환
	private static int getRank(int num) {
		return inputCnt - num + 1;
	}

	private static void update(int node, int start, int end, int index, int value) {
		if (index > end || index < start) {
			return;
		}

		tree[node] += value;

		if (start != end) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, index, value);
			update(node * 2 + 1, mid + 1, end, index, value);
		}
	}

	private static int query(int node, int start, int end, int rank) {
		// 순위에 해당하는 선물 개수 반환
		if (start == end) {
			return start;
		}

		int mid = (start + end) / 2;

		// 왼쪽 자식 서브트리에 속하는 경우
		if (tree[node * 2] >= rank) {
			return query(node * 2, start, mid, rank);
		}

		// 오른쪽 자식 서브트리에 속하는 경우
		else {
			return query(node * 2 + 1, mid + 1, end, rank - tree[node * 2]);
		}
	}

	// 리프 노드는 총 10^5개. 해당 숫자(1~10^5)만큼 선물 개수를 가지는 상자의 개수가 저장된다.
	private static int init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		int mid = (start + end) / 2;
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}

	// 세그먼트 트리 초기화
	private static void init() {
		int height = (int) Math.ceil(Math.log(MAX) / Math.log(2));
		tree = new int[1 << (height + 1)];
		Arrays.fill(tree, 0);

		init(1, 0, MAX);
	}

	private static void input() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		inputCnt = Integer.parseInt(st.nextToken());
		childCnt = Integer.parseInt(st.nextToken());

		// 선물 상자의 선물 개수
		arr = new int[MAX + 1];
		st = new StringTokenizer(br.readLine().trim());
		while (st.hasMoreTokens()) {
			int val = Integer.parseInt(st.nextToken());
			++arr[val];
		}

		// 각 아이들이 원하는 선물
		present = new int[childCnt];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < childCnt; idx++) {
			present[idx] = Integer.parseInt(st.nextToken());
		}

		// 각 아이들의 배려심
		box = new int[childCnt];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < childCnt; idx++) {
			box[idx] = Integer.parseInt(st.nextToken());
		}
	}
}
