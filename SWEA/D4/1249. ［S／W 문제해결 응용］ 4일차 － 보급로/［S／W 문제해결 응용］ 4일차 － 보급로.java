/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 지도 크기가 입력된다.
 * 3. 지도 정보가 입력된다.
 * 4. 출발지에서 도착지까지의 경로 중 총 복구 시간이 최소인 경로의 값을 구한다.
 * 	4-1. 우선순위 큐를 생성하여 출발지 정보를 넣는다.
 * 	4-2. 빈 큐가 될 때까지 반복한다.
 * 		4-2-1. 현재 좌표가 도착지라면, 종료
 * 		4-2-2. 지금 복구 시간보다 현재 좌표까지의 최소 복구 시간이 더 작은 경우, 패스
 * 		4-2-3. 인접 칸으로 이동
 * 			1) 인접 칸이 범위를 벗어나는 경우, 패스
 * 			2) (출발지 -> 현재칸 + 현재칸 -> 인접칸)의 복구 시간 < (출발지 -> 인접칸)의 복구 시간 => 최소 복구 시간 갱신 & 큐에 push
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	// 큐에 저장되는 정보. 복구 시간이 짧은 정보가 앞에 오도록 정렬
	static class Point implements Comparable<Point> {
		int row, col;
		int time;
		
		public Point(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}
	}
	
	static final int INF = 100000;
	
	// 방향 : 상하좌우
	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static int testCase;	// 테스트케이스 개수
	static int mapSize;		// 지도 크기
	static int[][] map;		// 지도 정보
	static int[][] dijk;	// 각 칸까지의 경로 중 최소 복구 시간 저장
	
	// 최소 비용 저장 배열 초기화
	public static void init() {
		dijk = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++)
			Arrays.fill(dijk[row], INF);
		dijk[0][0] = 0;
	}
	
	public static void getMinDist(int startRow, int startCol) {
		// 4-1. 우선순위 큐를 생성하여 출발지 정보를 넣는다.
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(startRow, startCol, 0));
		
		// 4-2. 빈 큐가 될 때까지 반복한다.
		while (!pq.isEmpty()) {
			Point point = pq.poll();
			int currentRow = point.row;
			int currentCol = point.col;
			int currentTime = point.time;
			
			// 4-2-1. 현재 좌표가 도착지라면, 종료
			if (currentRow == mapSize-1 && currentCol == mapSize-1)
				break;
			
			// 4-2-2. 지금 복구 시간보다 현재 좌표까지의 최소 복구 시간이 더 작은 경우, 패스
			if (currentTime > dijk[currentRow][currentCol])
				continue;
			
			// 4-2-3. 인접 칸으로 이동
			for (int dir = 0; dir < DELTA_CNT; dir++) {
				int nextRow = currentRow + DELTA_ROW[dir];
				int nextCol = currentCol + DELTA_COL[dir];
				
				// 1) 인접 칸이 범위를 벗어나는 경우, 패스
				if (nextRow < 0 || nextCol < 0 || nextRow >= mapSize || nextCol >= mapSize)
					continue;
				
				// 2) (출발지 -> 현재칸 + 현재칸 -> 인접칸)의 복구 시간 < (출발지 -> 인접칸)의 복구 시간 => 최소 복구 시간 갱신
				if (currentTime + map[nextRow][nextCol] < dijk[nextRow][nextCol]) {
					dijk[nextRow][nextCol] = currentTime + map[nextRow][nextCol];
					pq.offer(new Point(nextRow, nextCol, dijk[nextRow][nextCol]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			
			// 2. 지도 크기가 입력된다.
			mapSize = Integer.parseInt(br.readLine().trim());
			
			// 3. 지도 정보가 입력된다.
			map = new int[mapSize][mapSize];
			for (int row = 0; row < mapSize; row++) {
				String str = br.readLine().trim();
				for (int col = 0; col < mapSize; col++) {
					map[row][col] = str.charAt(col) - '0';
				}
			}
			
			// 4. 출발지에서 도착지까지의 경로 중 총 복구 시간이 최소인 경로의 값을 구한다.
			init();
			getMinDist(0, 0);
			
			sb.append("#").append(tc).append(" ").append(dijk[mapSize-1][mapSize-1]).append("\n");
		}
		System.out.println(sb);
	}
}