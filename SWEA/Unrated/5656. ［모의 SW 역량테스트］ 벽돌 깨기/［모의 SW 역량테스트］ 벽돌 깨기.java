/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 구슬 개수, 가로, 세로 크기가 입력된다.
 * 3. 벽돌들의 정보가 입력된다.
 * 4. 백트레킹을 통해 모든 열에 구슬을 던져보며, 모든 구슬을 던졌을 때 남은 벽돌의 최소 개수를 구한다.
 * 	4-1. 종료 조건 1 : 만약 구슬 개수만큼 구슬을 발사한 경우, 남은 벽돌의 개수를 계산하고 결과값(최소값) 갱신
 * 	4-2. 종료 조건 2 : 벽돌을 모두 제거할 수 있는 경우를 이미 찾은 경우, 더이상 최소값을 찾을 수 없기 때문에 종료
 * 	4-3. 모든 각 열에 다음 구슬을 발사해본다.
 * 		4-3-1. 선택한 열에서 첫 번째로 명중되는 벽돌의 행 인덱스를 구한다.
 * 			1) 좌표의 값이 빈칸일 때까지 row 증가 (벽돌이 나오면 break)
 * 			2) 해당 열의 모든 행을 탐색했다면, 반복 종료
 * 
 * 		4-3-2. 해당 열에 벽돌이 존재하는 경우에만, 백트레킹이 진행된다.
 * 			1) 새로운 배열을 만들어 현재 배열 값을 저장한다.
 * 			2) 현재 명중된 벽돌을 기준으로 반응되는 모든 벽돌들을 제거한다.
 * 				2-1) 상하좌우로 현재 벽돌에 적힌 숫자만큼의 거리 안에 있는 칸들을 탐색한다.
 * 				2-2) 해당 칸이 범위를 벗어나면 패스
 * 				2-3) 해당 칸에 벽돌이 존재하는 경우, 벽돌 제거
 * 					- 해당 칸 벽돌의 숫자가 1보다 크다면, 해당 벽돌을 기준으로 다시 주변 벽돌 제거
 * 			3) 배열에 남은 벽돌을 밑으로 떨어뜨린다.
 * 				3-1) 벽돌이 보내질 행의 인덱스 값을 저장한다. (밑에서부터)
 * 				3-2) 밑에서부터 벽돌이 있는지 확인한다.
 * 				3-3) 벽돌이 존재한다면, 해당 벽돌을 밑으로 보낸다.
 * 					- 원래 위치와 이동된 위치가 다른 경우, 원래 위치의 벽돌 제거
 * 			4) 다음 구슬을 던진다.
 * 			5) 현재 구슬로 제거된 벽돌들을 다시 원래대로 돌려놓는다.
 * 		
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int INF = Integer.MAX_VALUE;
	static final int EMPTY = 0;		// 빈 칸 (벽돌이 아닌 칸)
	
	// 방향 - 상하좌우
	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};

	static int testCase;			// 테스트케이스 개수
	static int marbleCnt;			// 구슬 개수
	static int rowSize, colSize;	// 세로, 가로 크기
	static int[][] array;			// 배열 정보
	static int minBrickCnt;			// 결과값 (남은 벽돌 수의 최소)
	
	// 현재 열에서 명중되는 벽의 행 인덱스 값 반환
	public static int getBrickRow(int currentCol) {
		int row = 0;
		
		// 1) 해당 좌표의 값이 빈칸일 때까지 row 증가
		while (array[row][currentCol] == EMPTY) {
			row++;
			
			// 2) 해당 열의 모든 행을 탐색했다면, 반복 종료
			if (row == rowSize)
				break;
		}
		return row;
	}
	
	// 배열 복사하는 함수
	public static void copyArray(int[][] from, int[][] to) {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				to[row][col] = from[row][col];
			}
		}
	}
	
	// 좌표를 기준으로 해당 거리만큼 위치한 벽돌들을 제거하는 함수
	public static void destroyBricks(int row, int col, int distance) {
		// 2-1) 상하좌우로 현재 벽돌에 적힌 숫자만큼의 거리 안에 있는 칸들을 탐색한다.
		for (int dir = 0; dir < DELTA_CNT; dir++) {
			for (int dist = 0; dist < distance; dist++) {
				int nextRow = row + DELTA_ROW[dir] * dist;
				int nextCol = col + DELTA_COL[dir] * dist;
				
				// 2-2) 해당 칸이 범위를 벗어나면 패스
				if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize)
					continue;
				
				// 2-3) 해당 칸에 벽돌이 존재하는 경우, 벽돌 제거
				int brickNum = array[nextRow][nextCol];
				if (brickNum != EMPTY) {
					array[nextRow][nextCol] = EMPTY;
					
					// 해당 칸 벽돌의 숫자가 1보다 크다면, 해당 벽돌을 기준으로 다시 주변 벽돌 제거
					if (brickNum > 1)
						destroyBricks(nextRow, nextCol, brickNum);
				}
			}
		}
	}
	
	// 남은 벽돌들을 빈칸이 없도록 밑으로 떨어뜨리는 함수
	public static void dropBricks() {
		for (int col = 0; col < colSize; col++) {
			// 3-1) 벽돌이 보내질 행의 인덱스 값을 저장한다.
			int emptyRow = rowSize - 1;
			
			// 3-2) 밑에서부터 벽돌이 있는지 확인한다.
			for (int row = rowSize-1; row >= 0; row--) {
				// 3-3) 벽돌이 존재한다면, 해당 벽돌을 밑으로 보낸다.
				if (array[row][col] != EMPTY) {
					array[emptyRow][col] = array[row][col];
					
					// 원래 위치와 이동된 위치가 다른 경우, 원래 위치의 벽돌 제거
					if (emptyRow != row)
						array[row][col] = EMPTY;
					
					emptyRow--;
				}
			}
		}
	}
	
	// 배열에 남은 벽돌의 개수 반환
	public static int getBrickCnt() {
		int cnt = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (array[row][col] != EMPTY)
					cnt++;
			}
		}
		return cnt;
	}
	
	public static void shootMarble(int selectedCnt) {
		// 4-1. 종료 조건 1 : 만약 구슬 개수만큼 구슬을 발사한 경우, 남은 벽돌의 개수를 계산하고 결과값(최소값) 갱신
		if (selectedCnt == marbleCnt) {
			minBrickCnt = Math.min(minBrickCnt, getBrickCnt());
			return;
		}
		
		// 4-2. 종료 조건 2 : 벽돌을 모두 제거할 수 있는 경우를 이미 찾은 경우, 더이상 최소값을 찾을 수 없기 때문에 종료
		if (minBrickCnt == 0)
			return;
		
		// 4-3. 모든 각 열에 다음 구슬을 발사해본다.
		for (int col = 0; col < colSize; col++) {
			// 4-3-1. 선택한 열에서 첫 번째로 명중되는 벽돌의 행 인덱스를 구한다.
			int row = getBrickRow(col);
			
			// 4-3-2. 해당 열에 벽돌이 존재하는 경우에만, 백트레킹이 진행된다.
			if (row < rowSize) {
				// 1) 새로운 배열을 만들어 현재 배열 값을 저장한다.
				int[][] newArray = new int[rowSize][colSize];
				copyArray(array, newArray);
				
				// 2) 현재 명중된 벽돌을 기준으로 반응되는 모든 벽돌들을 제거한다.
				destroyBricks(row, col, array[row][col]);
				
				// 3) 배열에 남은 벽돌을 밑으로 떨어뜨린다.
				dropBricks();
				
				// 4) 다음 구슬을 던진다.
				shootMarble(selectedCnt + 1);
				
				// 5) 현재 구슬로 제거된 벽돌들을 다시 원래대로 돌려놓는다.
				copyArray(newArray, array);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			minBrickCnt = INF;
			
			// 2. 구슬 개수, 가로, 세로 크기가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			marbleCnt = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			rowSize = Integer.parseInt(st.nextToken());
			
			// 3. 벽돌들의 정보가 입력된다.
			array = new int[rowSize][colSize];
			for (int row = 0; row < rowSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < colSize; col++) {
					array[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 4. 백트레킹을 통해 모든 열에 구슬을 던져보며, 모든 구슬을 던졌을 때 남은 벽돌의 최소 개수를 구한다.
			shootMarble(0);

			// 만약 벽돌의 개수가 초기화값이라면, 모든 구슬을 던지기 전에 이미 벽돌이 모두 제거된 것이기 때문에 0으로 출력
			sb.append("#").append(tc).append(" ");
			sb.append(minBrickCnt == Integer.MAX_VALUE ? 0 : minBrickCnt).append("\n");
		}
		System.out.println(sb);
	}
}
