/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 맥주를 파는 편의점 개수가 입력된다.
 * 3. 상근이네 집, 편의점, 페스티벌의 좌표가 입력된다.
 * 4. 집으로부터 이동 가능한 장소로 탐색한다.
 * 	4-1. 큐를 생성하고 집을 넣는다.
 * 	4-2. 빈 큐가 될 때까지 반복한다.
 * 		4-2-1. 현재 장소가 페스티벌인 경우, happy 처리 후 종료
 * 		4-2-2. 현재 장소와 인접(이동 가능)한 장소로 이동
 * 			1) 이동 장소가 이미 방문한 장소라면 패스
 * 			2) 방문 처리 후 큐에 넣음
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 좌표
	static class Point {
		int row, col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + "]";
		}
	}
	
	// 인접 리스트의 노드
	static class Node {
		int data;
		Node link;
		
		public Node() {};
		public Node(int data, Node link) {
			super();
			this.data = data;
			this.link = link;
		}
	}

	static final int MAX_DIST = 20 * 50;	// 최대 이동 거리
	static final int HOME_IDX = 0;	// 집 좌표가 저장된 인덱스
	
	static int testCase;		// 테스트케이스 개수
	static int storeCnt;		// 편의점 개수
	static List<Point> places;	// 각 장소의 좌표 ([0] : home , [1 ~ storeCnt] : store, [storeCnt+1] : festival
	static Node[] adjList;		// 인접 리스트
	static boolean[] isVisited;	// 방문 처리

	// 두 장소가 이동 가능한지 여부 반환
	public static boolean isAvailToGo(Point point1, Point point2) {
		if (Math.abs(point1.row - point2.row) + Math.abs(point1.col - point2.col) <= MAX_DIST)
			return true;
		return false;
	}
	
	public static boolean goToFestival() {
		// 4-1. 큐를 생성하고 집을 넣는다.
		Queue<Integer> queue = new ArrayDeque<>();
		isVisited = new boolean[storeCnt + 2];
		
		queue.offer(HOME_IDX);
		isVisited[HOME_IDX] = true;
		
		// 4-2. 빈 큐가 될 때까지 반복한다.
		while (!queue.isEmpty()) {
			int currentIdx = queue.poll();

			// 4-2-1. 현재 장소가 페스티벌인 경우, happy 처리 후 종료
			if (currentIdx == (storeCnt + 1))
				return true;
			
			// 4-2-2. 현재 장소와 인접(이동 가능)한 장소로 이동
			for (Node next = adjList[currentIdx]; next != null; next = next.link) {
				// 1) 이동 장소가 이미 방문한 장소라면 패스
				if (isVisited[next.data])
					continue;
				
				// 2) 방문 처리 후 큐에 넣음
				isVisited[next.data] = true;
				queue.offer(next.data);
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 맥주를 파는 편의점 개수가 입력된다.
			storeCnt = Integer.parseInt(br.readLine().trim());
			
			// init
			places = new ArrayList<>();
			adjList = new Node[storeCnt + 2];
			
			// 3. 상근이네 집, 편의점, 페스티벌의 좌표가 입력된다.
			for (int idx = 0; idx < storeCnt + 2; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				places.add(new Point(row, col));
				
				if (idx > 0) {
					// 이전 장소 중 갈 수 있는 장소를 연결 처리 해둔다. (최대 20개의 맥주를 마시고 이동 가능한 거리)
					for (int pre = 0; pre < idx; pre++) {
						if (isAvailToGo(places.get(pre), places.get(idx))) {
							adjList[idx] = new Node(pre, adjList[idx]);
							adjList[pre] = new Node(idx, adjList[pre]);
						}
					}
				}
			}
			
			// 4. 집으로부터 이동 가능한 장소로 탐색한다.
			if (goToFestival())
				sb.append("happy\n");
			else
				sb.append("sad\n");
		}
		System.out.println(sb);
	}
}
