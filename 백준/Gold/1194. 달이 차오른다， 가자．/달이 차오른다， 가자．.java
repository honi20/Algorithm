/**
 * 1. 미로의 세로, 가로 크기가 입력된다.
 * 2. 미로의 값이 입력된다.
 * 3. 미로를 탈출하는데 드는 이동 횟수의 최솟값을 구한다.
 * 	3-1. dp를 무한대 값으로 초기화한다. 시작 좌표의 0의 키 값만 0으로 저장
 * 	3-2. 우선순위 큐를 생성하고 시작 좌표를 넣는다. 우선순위 큐는 거리가 작은 것이 먼저 오도록 정렬된다.
 * 	3-3. 빈 큐가 될 때까지 반복한다.
 * 		3-3-1. 현재 칸이 목적지 좌표라면, 종료
 * 		3-3-2. 현재 칸이 목적지 좌표가 아니라면, 다음 칸으로 이동
 * 			1) 다음 칸이 범위를 벗어나면 패스
 * 			2) 다음 칸이 벽이라면 패스
 * 			3) 다음 칸이 문인 경우, 현재 다음 칸 문에 맞는 키를 가지고 있지 않다면 패스
 * 			4) 다음 칸이 키인 경우, "현재 경로의 거리 + 1 < 이동 칸의 해당 키를 가진 최단 경로 거리"라면 갱신 후 큐에 추가 & 패스
 * 			5) "현재 경로 거리 + 1 < 이동 칸의 경로 거리"인 경우 거리 갱신 후 큐에 추가
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	// 우선순위 큐에 넣을 객체. 거리가 작은 값이 앞으로 오도록 정렬됨
	static final class Point implements Comparable<Point> {
		int dist;	// 거리
		int keys;	// 현재 경로에 포함된 키 여부 (각 키의 존재 여부를 비트로 저장)
		int row, col;	// 좌표
		
		public Point(int dist, int keys, int row, int col) {
			super();
			this.dist = dist;
			this.keys = keys;
			this.row = row;
			this.col = col;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.dist - o.dist;
		}
	}
	
	// 미로 정보
	static final char START = '0';
	static final char DESTINATION = '1';
	static final char WALL = '#';
	static final int TYPE_CNT = 6;		// 키&문 종류의 개수
	static final int INF = Integer.MAX_VALUE;
	
	// 방향 (상하좌우)
	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static int mazeRow, mazeCol;	// 미로 세로, 가로 크기
	static char[][] maze;			// 미로 정보
	static int startRow, startCol;	// 시작 좌표
	
	// dp[row][col][keys] : 시작 좌표에서 각 좌표까지 올 수 있는 최단 거리 저장
	// keys : 해당 좌표까지 오는 경로 중 얻는 키들을 비트로 표시한 값 (0 ~ 2^TYPE_CNT)
	static int[][][] dp;
	
	// dp 초기화
	public static void init() {
		dp = new int[mazeRow][mazeCol][(int) (Math.pow(2, TYPE_CNT)+1)];
		
		for (int row = 0; row < mazeRow; row++) {
			for (int col = 0; col < mazeCol; col++) {
				Arrays.fill(dp[row][col], INF);
			}
		}
		
		dp[startRow][startCol][0] = 0;
	}
	
	public static int getMinDistance() {
		// 3-1. dp를 무한대 값으로 초기화한다. 시작 좌표의 0의 키 값만 0으로 저장
		init();
		
		// 3-2. 우선순위 큐를 생성하고 시작 좌표를 넣는다. 우선순위 큐는 거리가 작은 것이 먼저 오도록 정렬된다.
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(dp[startRow][startCol][0], 0, startRow, startCol));
		
		// 3-3. 빈 큐가 될 때까지 반복한다.
		while (!pq.isEmpty()) {
			// 큐의 맨 앞 정보
			Point point = pq.poll();
			int currentDist = point.dist;
			int currentKeys = point.keys;
			int currentRow = point.row;
			int currentCol = point.col;
			
			// 3-3-1. 현재 칸이 목적지 좌표라면, 종료
			if (maze[currentRow][currentCol] == DESTINATION) {
				return currentDist;
			}
			
			// 3-3-2. 현재 칸이 목적지 좌표가 아니라면, 다음 칸으로 이동
			for (int dir = 0; dir < DELTA_CNT; dir++) {
				int nextRow = currentRow + DELTA_ROW[dir];
				int nextCol = currentCol + DELTA_COL[dir];
				
				// 1) 다음 칸이 범위를 벗어나면 패스
				if (nextRow < 0 || nextCol < 0 || nextRow >= mazeRow || nextCol >= mazeCol)
					continue;
				
				char nextValue = maze[nextRow][nextCol];
				
				// 2) 다음 칸이 벽이라면 패스
				if (nextValue == WALL)
					continue;
				
				// 3) 다음 칸이 문이고, 현재 다음 칸 문에 맞는 키를 가지고 있지 않다면 패스
				else if (nextValue >= 'A' && nextValue <= 'F') {
					if ((currentKeys & (1 << (nextValue-'A'))) != (1 << (nextValue-'A'))) {
						continue;
					}
				}
				
				// 4) 다음 칸이 키인 경우, "현재 경로의 거리 + 1 < 이동 칸의 해당 키를 가진 최단 경로 거리"라면 갱신 후 큐에 추가
				else if (nextValue >= 'a' && nextValue <= 'f') {
					// 이동 칸의 키를 포함하는 비트 값
					int nextKeys = (currentKeys | (1 << (nextValue-'a')));
					
					if (dp[currentRow][currentCol][currentKeys] + 1 < dp[nextRow][nextCol][nextKeys]) {
						dp[nextRow][nextCol][nextKeys] = dp[currentRow][currentCol][currentKeys] + 1;
						pq.add(new Point(dp[nextRow][nextCol][nextKeys], nextKeys, nextRow, nextCol));
					}
					continue;
				}
				
				// 5) "현재 경로 거리 + 1 < 이동 칸의 경로 거리"인 경우 거리 갱신 후 큐에 추가
				if (dp[currentRow][currentCol][currentKeys] + 1 < dp[nextRow][nextCol][currentKeys]) {
					dp[nextRow][nextCol][currentKeys] = dp[currentRow][currentCol][currentKeys] + 1;
					pq.add(new Point(dp[nextRow][nextCol][currentKeys], currentKeys, nextRow, nextCol));
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 미로의 세로, 가로 크기가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		mazeRow = Integer.parseInt(st.nextToken());
		mazeCol = Integer.parseInt(st.nextToken());
		
		// 2. 미로의 값이 입력된다.
		maze = new char[mazeRow][mazeCol];
		for (int row = 0; row < mazeRow; row++) {
			String str = br.readLine().trim();
			
			for (int col = 0; col < mazeCol; col++) {
				maze[row][col] = str.charAt(col);
				
				// 출발 좌표 저장
				if (maze[row][col] == START) {
					startRow = row; startCol = col;
				}
			}
		}
		
		// 3. 미로를 탈출하는데 드는 이동 횟수의 최솟값을 구한다.
		System.out.println(getMinDistance());
	}
}
