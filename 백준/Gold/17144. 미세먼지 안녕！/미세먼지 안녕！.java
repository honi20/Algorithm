/**
 * 1. 세로 크기, 가로 크기, 시간이 입력된다.
 * 2. 방의 정보가 입력된다.
 * 	- 공기청정기 : -1 / 나머지 : 미세먼지 양
 * 
 * 3. 각 초마다 방 상태를 갱신한다.
 * 	3-1. 종료조건 : 현재 시간이 T초일 때, 종료
 * 	3-2. 각 칸의 미세먼지가 인접한 칸으로 확산된다.
 * 		3-2-1. 현재 칸이 공기청정기이거나 빈 칸이라면 패스
 * 		3-2-2. 현재 칸의 상하좌우 인접칸으로 칸의 값 / 5한 값을 보낸다.
 * 			1) 인접칸이 범위를 벗어나면, 패스
 * 			2) 인접칸이 공기청정기라면, 패스
 * 			3) 확산 개수를 1 증가시키고, 각 칸의 추가되는(확산으로 받은) 값을 저장하는 배열에 현재값/5한 결과를 추가한다.
 * 		3-2-3. 현재 값에서 확산개수 * (현재값 / 5)를 뺀다.
 *
 * 	3-3. 추가 값을 저장하는 배열의 값들을 현재 배열의 각 칸에 추가한다.
 * 
 * 	3-4. 공기청정기 방향대로 미세먼지를 한 칸씩 이동한다.
 * 		- 위쪽 : 바람이 반시계 방향으로 순환 -> 공기청정기 기준으로 시계 방향으로 탐색하며 각 칸의 값들을 이전 칸으로 옮긴다.
 * 		- 아래쪽 : 바람이 시계 방향으로 순환 -> 공기청정기 기준으로 반시계 방향으로 탐색하며 각 칸의 값들을 이전 칸으로 옮긴다.
 * 		3-4-1. 4가지의 방향을 돌 때까지 미세먼지를 한 칸씩 이동시킨다.
 * 			1) 현재 칸이 공기청정기라면 반복 종료
 * 			2) 현재 값을 이전 좌표의 값으로 저장한다.
 * 			3) 현재 방향으로 다음 칸의 좌표를 얻는다.
 * 			4) 다음 좌표가 범위를 벗어난다면, 방향 전환 후 다시 다음 칸의 좌표를 구한다.
 * 			5) 다음 좌표가 현재 방향으로 가능한 범위를 벗어난다면, 방향 전환 후 다시 다음 칸의 좌표를 구한다.
 * 				- 위쪽의 경우, 다음 좌표의 행 값 > 공기청정기 행 값 / 아래쪽인 경우, 다음 좌표의 행 값 < 공기청정기 행 값
 * 			6) 현재칸의 좌표를 이전칸의 좌표로, 다음 칸의 좌표를 현재칸의 좌표로 바꾼다.
 * 		3-4-2. 공기청정기 바로 이전 칸에 0을 저장한다.
 * 
 * 	3-5. 시간을 1 증가시킨 후, 다시 방 상태를 업데이트한다.
 * 
 * 4. 시간이 다 된 후, 방에 남은 미세먼지 양을 구한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	// 입력 정보
	static final int AIR_CLEANER = -1;	// 공기청정기
	static final int DIVIDE = 5;		// 확산 시 나누어지는 수
	
	// 방향 : 상우하좌 (시계방향)
	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,0,1,0};
	static final int[] DELTA_COL = {0,1,0,-1};

	static int rowSize, colSize;	// 세로, 가로 크기
	static int maxTime;				// 작동 최대 시간 (해당 초마다 미세먼지 업데이트 -> 초가 끝난 후 결과 출력)
	static int[][] room;			// 방 정보
	static int topRow, topCol;		// 반시계방향으로 순환하는 공기청정기 좌표 (위쪽)
	static int bottomRow, bottomCol;// 시계방향으로 순환하는 공기청정기 좌표 (아래쪽)
	
	// from 배열의 값을 to 배열에 추가한다.
	public static void addArray(int[][] from, int[][] to) {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				to[row][col] += from[row][col];
			}
		}
	}

	// 위쪽 : 시계 방향으로 값 갱신 => 상(0) -> 우(1) -> 하(2) -> 좌(3)
	// 아래쪽 : 반시계 방향으로 값 갱신 => 하(2 = 2-0) -> 우(1) -> 상(0 = 2-2) -> 좌(3)
	public static int getDirection(int dirIdx, boolean isBottom) {
		if (isBottom && (dirIdx % 2 == 0))
			return 2 - dirIdx;
		return dirIdx;
	}
	
	// 위쪽 : 바람이 반시계 방향으로 순환 -> 공기청정기 기준으로 시계 방향으로 탐색하며 각 칸의 값들을 이전 칸으로 옮긴다.
	// 아래쪽 : 바람이 시계 방향으로 순환 -> 공기청정기 기준으로 반시계 방향으로 탐색하며 각 칸의 값들을 이전 칸으로 옮긴다.
	public static void operateAirCleaner(int row, int col, boolean isBottom) {
		int dirIdx = 0;	// 이동 방향 순서, 실제 인덱스
		int dir = getDirection(dirIdx, isBottom);
		
		// 이전 칸의 좌표 => 공기청정기의 바로 다음 칸을 초기값으로 설정
		int preRow = row + DELTA_ROW[dir], preCol = col;
		// 현재 칸의 좌표 => 공기청정기로부터 두 칸 떨어진 칸의 좌표를 초기값으로 설정
		int currentRow = row + 2*DELTA_ROW[dir], currentCol = col;
		
		// 3-4-1. 4가지의 방향을 돌 때까지 미세먼지를 한 칸씩 이동시킨다.
		while (dirIdx < DELTA_CNT) {
			// 1) 현재 칸이 공기청정기라면 반복 종료
			if (room[currentRow][currentCol] == AIR_CLEANER)
				break;
			
			// 2) 현재 값을 이전 좌표의 값으로 저장한다.
			room[preRow][preCol] = room[currentRow][currentCol];
			
			// 3) 현재 방향으로 다음 칸의 좌표를 얻는다.
			int nextRow = currentRow + DELTA_ROW[dir];
			int nextCol = currentCol + DELTA_COL[dir];
			
			// 4) 다음 좌표가 범위를 벗어난다면, 방향 전환 후 다시 다음 칸의 좌표를 구한다.
			if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize) {
				++dirIdx;
				dir = getDirection(dirIdx, isBottom);
				nextRow = currentRow + DELTA_ROW[dir];
				nextCol = currentCol + DELTA_COL[dir];
			}
			
			// 5) 다음 좌표가 현재 방향으로 가능한 범위를 벗어난다면, 방향 전환 후 다시 다음 칸의 좌표를 구한다.
			// 위쪽의 경우, 다음 좌표의 행 값 > 공기청정기 행 값 / 아래쪽인 경우, 다음 좌표의 행 값 < 공기청정기 행 값
			if ((!isBottom && nextRow > row) || (isBottom && nextRow < row)) {
				++dirIdx;
				dir = getDirection(dirIdx, isBottom);
				nextRow = currentRow + DELTA_ROW[dir];
				nextCol = currentCol + DELTA_COL[dir];
			}
			
			// 6) 현재칸의 좌표를 이전칸의 좌표로, 다음 칸의 좌표를 현재칸의 좌표로 바꾼다.
			preRow = currentRow; preCol = currentCol;
			currentRow = nextRow; currentCol = nextCol;
		}
		
		// 3-4-2. 공기청정기 바로 이전 칸에 0을 저장한다.
		room[preRow][preCol] = 0;
	}
	
	// time초 까지의 방 상태
	public static void getRoomStatus(int time) {
		// 3-1. 종료조건 : 현재 시간이 T초일 때, 종료
		if (time == maxTime)
			return;
		
		// 3-2. 각 칸의 미세먼지가 인접한 칸으로 확산된다.
		int[][] plus = new int[rowSize][colSize];	// 확산으로 추가되는 미세먼지 양 저장
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				// 3-2-1. 현재 칸이 공기청정기이거나 빈 칸이라면 패스
				if (room[row][col] == AIR_CLEANER || room[row][col] == 0)
					continue;
				
				// 3-2-2. 현재 칸의 상하좌우 인접칸으로 칸의 값 / 5한 값을 보낸다.
				int cnt = 0;
				for (int dir = 0; dir < DELTA_CNT; dir++) {
					int nextRow = row + DELTA_ROW[dir];
					int nextCol = col + DELTA_COL[dir];
					
					// 1) 인접칸이 범위를 벗어나면, 패스
					if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize)
						continue;
					
					// 2) 인접칸이 공기청정기라면, 패스
					if (room[nextRow][nextCol] == AIR_CLEANER)
						continue;
					
					// 3) 확산 개수를 1 증가시키고, 각 칸의 추가되는(확산으로 받은) 값을 저장하는 배열에 현재값/5한 결과를 추가한다.
					++cnt;
					plus[nextRow][nextCol] += (room[row][col] / DIVIDE);
				}
				
				// 3-2-3. 현재 값에서 확산개수 * (현재값 / 5)를 뺀다. (인접칸으로 확산된 미세먼지 총 양만큼 뺌)
				room[row][col] -= (cnt * (room[row][col] / DIVIDE));
			}
		}
		
		// 3-3. 추가 값을 저장하는 배열의 값들을 현재 배열의 각 칸에 추가한다.
		addArray(plus, room);
		
		// 3-4. 공기청정기 방향대로 미세먼지를 한 칸씩 이동한다.
		// 위쪽 공기청정기
		operateAirCleaner(topRow, topCol, false);
		
		// 아래쪽 공기청정기
		operateAirCleaner(bottomRow, bottomCol, true);

		// 3-5. 시간을 1 증가시킨 후, 다시 방 상태를 업데이트한다.
		getRoomStatus(time+1);
	}
	
	// 방에 남은 미세먼지 양 반환
	public static int getTotalSum() {
		int totalSum = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (room[row][col] > 0)
					totalSum += room[row][col];
			}
		}
		return totalSum;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 세로 크기, 가로 크기, 시간이 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		maxTime = Integer.parseInt(st.nextToken());
		
		// 2. 방의 정보가 입력된다.
		room = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				room[row][col] = Integer.parseInt(st.nextToken());
				
				// 공기청정기 좌표
				if (row > 1 && room[row][col] == AIR_CLEANER && room[row-1][col] == AIR_CLEANER) {
					topRow = row-1; bottomRow = row;
					topCol = bottomCol = col;
				}
			}
		}
		
		// 3. 각 초마다 방 상태를 갱신한다.
		getRoomStatus(0);
		
		// 4. 시간이 다 된 후, 방에 남은 미세먼지 양을 구한다.
		System.out.println(getTotalSum());
	}
}