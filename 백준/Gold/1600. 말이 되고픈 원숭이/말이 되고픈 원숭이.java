/**
 * 1. 원숭이가 말처럼 움직일 수 있는 횟수가 입력된다.
 * 2. 격자판의 가로, 세로 길이가 입력된다.
 * 3. 격자판 정보가 입력된다.
 * 4. 원숭이가 시작지점(맨 왼쪽위)에서 도착지점(맨 오른쪽아래)까지 가는데 필요한 동작의 최솟값을 구한다.
 * 	
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Coord implements Comparable<Coord> {
		int row, col;	// 좌표
		int moveCnt;	// 동작 수
		int horseCnt;	// 사용한 말 움직임 수
		
		public Coord(int row, int col, int moveCnt, int horseCnt) {
			super();
			this.row = row;
			this.col = col;
			this.moveCnt = moveCnt;
			this.horseCnt = horseCnt;
		}

		@Override
		public int compareTo(Coord o) {
			return this.moveCnt - o.moveCnt;
		}
	}
	
	static final int WALL = 1;	// 장애물
	static final int START_ROW = 0;
	static final int START_COL = 0;
	
	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static final int HORSE_CNT = 8;
	static final int[] HORSE_ROW = {-2,-2,-1,-1,1,1,2,2};
	static final int[] HORSE_COL = {-1,1,-2,2,-2,2,-1,1};
	
	static int cntLikeHorse;
	static int rowSize, colSize;
	static int[][] array;
	
	static int[][][] minDist;
	
	public static void init() {
		minDist = new int[rowSize][colSize][cntLikeHorse + 1];
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				for (int cnt = 0; cnt <= cntLikeHorse; cnt++) {
					minDist[row][col][cnt] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int cnt = 0; cnt <= cntLikeHorse; cnt++) {
			minDist[START_ROW][START_COL][cnt] = 0;
		}
	}
	
	public static int getMinMoveCnt() {
		// init
		init();
		PriorityQueue<Coord> queue = new PriorityQueue<>();
		
		// 시작 좌표
		queue.offer(new Coord(START_ROW, START_COL, 0, 0));
		
		while (!queue.isEmpty()) {
			Coord current = queue.poll();
			int currentRow = current.row;
			int currentCol = current.col;
			int moveCnt = current.moveCnt;
			int horseCnt = current.horseCnt;

			// 도착 지점이면 종료
			if (currentRow == rowSize - 1 && currentCol == colSize - 1) {
				return moveCnt;
			}
			
			// 인접 방향으로 갈 수 있는 칸 중 최단경로값이 작은 칸의 경우, 최단값 갱신 후 큐에 push
			for (int dir = 0; dir < DELTA_CNT; dir++) {
				int nextRow = currentRow + DELTA_ROW[dir];
				int nextCol = currentCol + DELTA_COL[dir];
				
				// 범위를 벗어나는 경우
				if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize)
					continue;
				
				// 장애물인 경우
				if (array[nextRow][nextCol] == WALL)
					continue;
				
				if (minDist[nextRow][nextCol][horseCnt] > moveCnt + 1) {
					minDist[nextRow][nextCol][horseCnt] = moveCnt + 1;
					queue.offer(new Coord(nextRow, nextCol, moveCnt + 1, horseCnt));
				}
			}
			
			// 말 움직임 횟수가 남은 경우, 말 움직임을 하는 경우도 고려
			if (horseCnt < cntLikeHorse) {
				for (int dir = 0; dir < HORSE_CNT; dir++) {
					int nextRow = currentRow + HORSE_ROW[dir];
					int nextCol = currentCol + HORSE_COL[dir];
					
					// 범위를 벗어나는 경우
					if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize)
						continue;
					
					// 장애물인 경우
					if (array[nextRow][nextCol] == WALL)
						continue;
					
					if (minDist[nextRow][nextCol][horseCnt+1] > moveCnt + 1) {
						minDist[nextRow][nextCol][horseCnt+1] = moveCnt + 1;
						queue.offer(new Coord(nextRow, nextCol, moveCnt + 1, horseCnt+1));
					}
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 원숭이가 말처럼 움직일 수 있는 횟수가 입력된다.
		cntLikeHorse = Integer.parseInt(br.readLine().trim());
		
		// 2. 격자판의 가로, 세로 길이가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		array = new int[rowSize][colSize];
		
		// 3. 격자판 정보가 입력된다.
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for (int col = 0; col < colSize; col++) {
				array[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 4. 원숭이가 시작지점(맨 왼쪽위)에서 도착지점(맨 오른쪽아래)까지 가는데 필요한 동작의 최솟값을 구한다.
		System.out.println(getMinMoveCnt());
	}
}