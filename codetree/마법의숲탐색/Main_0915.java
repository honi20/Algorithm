/**
 * [풀이]
 * 1. [입력]
 * 	1-1. 숲의 가로, 세로 크기, 정령 수가 입력된다.
 * 	1-2. 골렘의 출발 열, 출구 방향이 입력된다.
 * 2. 최대한 남쪽으로 골렘을 내려보낸다.
 * 	2-1. 남쪽으로 한 칸 이동
 * 		1) 중심 기준 남*2쪽 칸이 비어있는지 확인
 * 		2) 중심 기준 남서쪽 칸이 비어있는지 확인
 * 		3) 중심 기준 남동쪽 칸이 비어있는지 확인
 * 	2-2. 서쪽으로 회전 -> 남쪽으로 한 칸 이동
 * 		1) 중심 기준 서*2쪽 칸이 비어있는지 확인
 * 		2) 중심 기준 서북쪽 칸이 비어있는지 확인
 * 		3) 중심 기준 서남쪽 칸이 비어있는지 확인
 * 		4) 갱신된 중심을 기준으로 2-1 만족하는지 확인
 * 	2-3. 동쪽으로 회전 -> 남쪽으로 한 칸 이동
 * 		1) 중심 기준 동*2쪽 칸이 비어있는지 확인
 * 		2) 중심 기준 동북쪽 칸이 비어있는지 확인
 * 		3) 중심 기준 동남쪽 칸이 비어있는지 확인
 * 		4) 갱신된 중심을 기준으로 2-1 만족하는지 확인
 * 3. 골렘의 일부가 숲에 포함되지 않는다면, 숲을 초기화
 * 4. 골렘의 최종 위치를 맵에 표시
 * 5. 정령이 갈 수 있는 가장 남쪽 칸으로 이동
 * 	5-1. 현재 골렘을 Queue에 넣은 후, 인접 골렘을 탐색하며 이동한다.
 * 	5-2. 현재 골렘이 방문된 골렘이면, 패스
 * 	5-3. 현재 골렘의 출구와 인접한 골렘을 탐색한다.
 * 6. 정령의 최종 위치 행을 결과에 누적
 * 
 * [출력]
 * - 각 정령들의 최종 위치 행의 총합
 */
import java.io.*;
import java.util.*;

public class Main_0915 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Golem {
		int row, col;
		int exitDir;
		
		public Golem(int row, int col, int exitDir) {
			this.row = row;
			this.col = col;
			this.exitDir = exitDir;
		}
		
		public void updateRow(int newRow) {
			this.row = newRow;
		}
		
		public void updateCol(int newCol) {
			this.col = newCol;
		}
		
		public void updateDir(int newDir) {
			this.exitDir = newDir;
			
			if (this.exitDir < 0)
				this.exitDir = 3;
			else if (this.exitDir > 3)
				this.exitDir = 0;
		}
	}
	
	static final int[] DELTA_ROW = {-1,0,1,0};
	static final int[] DELTA_COL = {0,1,0,-1};
	static final int NORTH = 0;
	static final int EAST = 1;
	static final int SOUTH = 2;
	static final int WEST = 3;
	
	static int rowSize, colSize, golemCnt;
	static int[][] map;
	static Golem[] golems;
	static boolean[] isVisited;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1-1. 숲의 가로, 세로 크기, 정령 수가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		golemCnt = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize+1][colSize+1];
		golems = new Golem[golemCnt+1];
		
		for (int idx = 1; idx <= golemCnt; idx++) {
			// 1-2. 골렘의 출발 열, 출구 방향이 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			int col = Integer.parseInt(st.nextToken());
			int exitDir = Integer.parseInt(st.nextToken());
			
			// 2. 최대한 남쪽으로 골렘을 내려보낸다.
			Golem golem = new Golem(-1, col, exitDir);
			movingDown(golem);
			
			// 3. 골렘의 일부가 숲에 포함되지 않는다면, 숲을 초기화
			if (golem.row < 2) {
				initMap();
				continue;
			}
			
			// 4. 골렘의 최종 위치를 맵에 표시
			map[golem.row][golem.col] = idx;
			for (int dir = 0; dir < 4; dir++) {
				map[golem.row+DELTA_ROW[dir]][golem.col+DELTA_COL[dir]] = idx;
			}
			golems[idx] = golem;
			
			// 5. 정령이 갈 수 있는 가장 남쪽 칸으로 이동
			int maxRow = movePlayer(idx);
			
			// 6. 정령의 최종 위치 행을 결과에 누적
			result += maxRow;
		}
		
		System.out.println(result);
	}
	
	private static int movePlayer(int idx) {
		isVisited = new boolean[golemCnt+1];
		Arrays.fill(isVisited, false);
		int maxRow = golems[idx].row;
		
		// 5-1. 현재 골렘을 Queue에 넣은 후, 인접 골렘을 탐색하며 이동한다.
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(idx);
		
		while (!queue.isEmpty()) {
			// 5-2. 현재 골렘이 방문된 골렘이면, 패스
			int curIdx = queue.poll();
			int exitRow = golems[curIdx].row + DELTA_ROW[golems[curIdx].exitDir];
			int exitCol = golems[curIdx].col + DELTA_COL[golems[curIdx].exitDir];
			maxRow = Math.max(maxRow, golems[curIdx].row + DELTA_ROW[SOUTH]);
			
			if (isVisited[curIdx])
				continue;
			
			isVisited[curIdx] = true;
			
			// 5-3. 현재 골렘의 출구와 인접한 골렘을 탐색한다.
			for (int dir = 0; dir < 4; dir++) {
				int nextRow = exitRow + DELTA_ROW[dir];
				int nextCol = exitCol + DELTA_COL[dir];
				
				if (nextRow <= 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize)
					continue;
				
				if (map[nextRow][nextCol] == 0)
					continue;
				
				if (isVisited[map[nextRow][nextCol]])
					continue;
				
				queue.offer(map[nextRow][nextCol]);
			}
		}
		return maxRow;
	}
	
	private static void initMap() {
		for (int row = 0; row <= rowSize; row++) {
			for (int col = 0; col <= colSize; col++) {
				map[row][col] = 0;
			}
		}
	}
	
	private static void movingDown(Golem golem) {
		while (true) {
			// 2-1. 남쪽으로 한 칸 이동
			if (isAvailToMoveSouth(golem.row, golem.col)) {
				golem.updateRow(golem.row+1);
			}
			
			// 2-2. 서쪽으로 회전 -> 남쪽으로 한 칸 이동
			else if (isAvailToMoveWest(golem)) {
				golem.updateRow(golem.row+1);
				golem.updateCol(golem.col-1);
				golem.updateDir(golem.exitDir-1);
			}
			
			// 2-3. 동쪽으로 회전 -> 남쪽으로 한 칸 이동
			else if (isAvailToMoveEast(golem)) {
				golem.updateRow(golem.row+1);
				golem.updateCol(golem.col+1);
				golem.updateDir(golem.exitDir+1);
			}
			
			else
				break;
		}
	}

	private static boolean isAvailToMoveSouth(int curRow, int curCol) {
		int nextRow, nextCol;
		
		// 1) 중심 기준 남*2쪽 칸이 비어있는지 확인
		nextRow = curRow + DELTA_ROW[SOUTH]*2;
		nextCol = curCol + DELTA_COL[SOUTH]*2;
		if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
			return false;
		}
		if (map[nextRow][nextCol] != 0) {
			return false;
		}
		
		if (curRow == -1) {
			return true;
		}
		
		// 2) 중심 기준 남서쪽 칸이 비어있는지 확인
		nextRow = curRow + DELTA_ROW[SOUTH] + DELTA_ROW[WEST];
		nextCol = curCol + DELTA_COL[SOUTH] + DELTA_COL[WEST];
		if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
			return false;
		}
		if (map[nextRow][nextCol] != 0) {
			return false;
		}
		
		// 3) 중심 기준 남동쪽 칸이 비어있는지 확인
		nextRow = curRow + DELTA_ROW[SOUTH] + DELTA_ROW[EAST];
		nextCol = curCol + DELTA_COL[SOUTH] + DELTA_COL[EAST];
		if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
			return false;
		}
		if (map[nextRow][nextCol] != 0) {
			return false;
		}
		
		return true;
	}
	
	private static boolean isAvailToMoveWest(Golem golem) {
		int curRow = golem.row;
		int curCol = golem.col;
		int nextRow, nextCol;
		
		if (curCol < 3)
			return false;
	
		if (curRow == -1) {
			return true;
		}
		
		if (curRow > 0) {
			// 1) 중심 기준 서*2쪽 칸이 비어있는지 확인
			nextRow = curRow + DELTA_ROW[WEST]*2;
			nextCol = curCol + DELTA_COL[WEST]*2;
			if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
				return false;
			}
			if (map[nextRow][nextCol] != 0) {
				return false;
			}
			
			// 2) 중심 기준 서북쪽 칸이 비어있는지 확인
			nextRow = curRow + DELTA_ROW[WEST] + DELTA_ROW[NORTH];
			nextCol = curCol + DELTA_COL[WEST] + DELTA_COL[NORTH];
			if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
				return false;
			}
			if (map[nextRow][nextCol] != 0) {
				return false;
			}
		}
	
		// 3) 중심 기준 서남쪽 칸이 비어있는지 확인
		nextRow = curRow + DELTA_ROW[WEST] + DELTA_ROW[SOUTH];
		nextCol = curCol + DELTA_COL[WEST] + DELTA_COL[SOUTH];
		if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
			return false;
		}
		if (map[nextRow][nextCol] != 0) {
			return false;
		}
		
		// 4) 갱신된 중심을 기준으로 2-1 만족하는지 확인
		return isAvailToMoveSouth(curRow + DELTA_ROW[WEST], curCol + DELTA_COL[WEST]);
	}

	private static boolean isAvailToMoveEast(Golem golem) {
		int curRow = golem.row;
		int curCol = golem.col;
		int nextRow, nextCol;

		if (curCol > colSize-2)
			return false;
		
		if (curRow == -1) {
			return true;
		}
		
		if (curRow > 0) {
			// 1) 중심 기준 동*2쪽 칸이 비어있는지 확인
			nextRow = curRow + DELTA_ROW[EAST]*2;
			nextCol = curCol + DELTA_COL[EAST]*2;
			if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
				return false;
			}
			if (map[nextRow][nextCol] != 0) {
				return false;
			}
			
			// 2) 중심 기준 동북쪽 칸이 비어있는지 확인
			nextRow = curRow + DELTA_ROW[EAST] + DELTA_ROW[NORTH];
			nextCol = curCol + DELTA_COL[EAST] + DELTA_COL[NORTH];
			if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
				return false;
			}
			if (map[nextRow][nextCol] != 0) {
				return false;
			}
		}
		
		// 3) 중심 기준 동남쪽 칸이 비어있는지 확인
		nextRow = curRow + DELTA_ROW[EAST] + DELTA_ROW[SOUTH];
		nextCol = curCol + DELTA_COL[EAST] + DELTA_COL[SOUTH];
		if (nextRow < 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize) {
			return false;
		}
		if (map[nextRow][nextCol] != 0) {
			return false;
		}
		
		// 4) 갱신된 중심을 기준으로 2-1 만족하는지 확인
		return isAvailToMoveSouth(curRow + DELTA_ROW[EAST], curCol + DELTA_COL[EAST]);
	}
}
