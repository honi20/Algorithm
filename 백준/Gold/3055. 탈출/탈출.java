/**
 * 1. 지도의 세로, 가로 크기가 입력된다.
 * 2. 지도의 정보가 입력된다.
 * 3. 비버의 동굴(목적지)까지 가는데 걸리는 최소 시간을 구한다.
 * 	3-1. 큐를 생성하고 물 칸의 좌표와 시작 좌표를 큐에 추가한다.
 * 	3-2. 빈 큐가 될 때까지 반복한다.
 * 		3-2-1. 현재 좌표가 목적지라면, 시간 반환 후 종료
 * 		3-2-2. 현재 좌표가 이미 방문되었다면, 패스
 * 		3-2-3. 인접 칸으로 이동
 * 			1) 범위를 벗어나는 경우, 패스
 * 			2) 이미 방문한 칸인 경우, 패스
 * 			3) 돌이 있는 칸인 경우, 패스
 * 			4) 현재 칸이 물 칸이고 인접 칸이 동굴인 경우, 패스
 * 			5) 큐에 추가
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 좌표
	static class Point {
		int row, col;

		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}	
	}
	
	// 큐에 넣을 정보 (시간이 작은게 앞으로, 시간이 같다면 물 칸이 앞으로 오도록 정렬됨)
	static class Node implements Comparable<Node> { 
		Point point;	// 좌표
		int time;		// 출발지에서 현재 좌표까지 오는데 걸린 시간
		int type;		// 칸의 타입 (물칸인지 여부 저장)
		
		public Node(Point point, int time, int type) {
			super();
			this.point = point;
			this.time = time;
			this.type = type;
		}
		
		@Override
		public int compareTo(Node o) {
			if (this.time == o.time)
				return this.type - o.type;
			return this.time - o.time;
		}
	}
	
	// 방향
	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	// 지도 값 정보
	static final char EMPTY = '.';
	static final char WATER = '*';
	static final char STONE = 'X';
	static final char DEST = 'D';
	static final char START = 'S';
	
	// 각 칸이 방문된 시간에 칸의 상태가 물인지 아닌지의 값
	static final int IS_WATER = 0;
	static final int IS_NOT_WATER = 1;
	
	static int rowSize, colSize;	// 지도의 세로, 가로 크기
	static int[][] map;				// 지도 정보
	static Point start;				// 시작 좌표
	static List<Point> waters;		// 물이 찬 좌표 정보 
	
	public static int goToDestination() {
		// 3-1. 큐를 생성하고 물 칸의 좌표와 시작 좌표를 큐에 추가한다.
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] isVisited = new boolean[rowSize][colSize];
		
		for (int idx = 0; idx < waters.size(); idx++) {
			Point water = waters.get(idx);
			pq.add(new Node(water, 0, IS_WATER));
			isVisited[water.row][water.col] = true; 
		}
		pq.add(new Node(start, 0, IS_NOT_WATER));
		isVisited[start.row][start.col] = true; 
		
		// 3-2. 빈 큐가 될 때까지 반복한다.
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int currentRow = node.point.row;
			int currentCol = node.point.col;
			int currentTime = node.time;
			int currentType = node.type;
			
			// 3-2-1. 현재 좌표가 목적지라면, 시간 반환 후 종료
			if (map[currentRow][currentCol] == DEST)
				return currentTime;

			// 3-2-3. 인접 칸으로 이동
			for (int dir = 0; dir < DELTA_CNT; dir++) {
				int nextRow = currentRow + DELTA_ROW[dir];
				int nextCol = currentCol + DELTA_COL[dir];
				
				// 1) 범위를 벗어나는 경우, 패스
				if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize)
					continue;
				
				// 2) 이미 방문한 칸인 경우, 패스
				if (isVisited[nextRow][nextCol])
					continue;
				
				// 3) 돌이 있는 칸인 경우, 패스
				if (map[nextRow][nextCol] == STONE)
					continue;
				
				// 4) 현재 칸이 물 칸이고 인접 칸이 동굴인 경우, 패스
				if (currentType == IS_WATER && map[nextRow][nextCol] == DEST)
					continue;
				
				// 5) 큐에 추가
				isVisited[nextRow][nextCol] = true; 
				pq.offer(new Node(new Point(nextRow, nextCol), currentTime + 1, currentType));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 지도의 세로, 가로 크기가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 2. 지도의 정보가 입력된다.
		map = new int[rowSize][colSize];
		waters = new ArrayList<>();
		for (int row = 0; row < rowSize; row++) {
			String str = br.readLine().trim();
			
			for (int col = 0; col < colSize; col++) {
				map[row][col] = str.charAt(col);
				
				if (map[row][col] == START)
					start = new Point(row, col);
				else if (map[row][col] == WATER)
					waters.add(new Point(row, col));
				
			}
		}
		
		// 3. 비버의 동굴(목적지)까지 가는데 걸리는 최소 시간을 구한다.
		int result = goToDestination();
		System.out.println(result == -1 ? "KAKTUS" : result);
	}
}