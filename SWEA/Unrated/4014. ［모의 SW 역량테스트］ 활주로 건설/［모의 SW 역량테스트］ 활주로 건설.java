/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 지도의 한 변의 길이와 경사로의 길이가 입력된다.
 * 3. 지도의 지형 정보가 입력된다.
 * 4. 가로 방향에 건설 가능한 활주로 개수를 구한다.
 * 	1) 이전 칸의 높이 == 현재 칸의 높이 => 패스
 * 	2) 이전 칸의 높이와 현재 칸의 높이 차가 2 이상인 경우 => 해당 라인에 활주로 건설 불가
 * 	3) 이전 칸의 높이 > 현재 칸의 높이 => 이전 칸을 기준으로 오른쪽에 경사로를 놓을 수 있는지 확인한다.
 * 		(1) 경사로가 지도의 범위를 벗어나는 경우, 설치 불가능
 * 		(2) 현재 좌표를 기준으로 입력 방향으로 경사로 길이에 속하는 칸을 탐색한다.
 * 			(2-1) 이미 경사로가 설치된 칸이라면, 설치 불가능
 * 			(2-2) 현재 좌표의 값보다 1 작은 값이 아니라면, 설치 불가능
 * 		(3) 경사로 설치가 가능하다면 속한 칸들을 방문 처리 한다.
 * 	4) 이전 칸의 높이 < 현재 칸의 높이 => 현재칸을 기준으로 왼쪽에 경사로를 놓을 수 있는지 확인한다.
 * 		- 3)번과 동일한 과정을 통해 확인
 * 
 * 5. 세로 방향에 건설 가능한 활주로 개수를 구한다.
 * 	- 4번과 동일한 과정을 통해 계산
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	// 방향 : 상하좌우
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	static int testCase;			// 테스트케이스 개수
	static int mapSize, runwayLen;	// 지도 크기, 경사로 길이
	static int[][] map;				// 지도 지형 정보
	static boolean[] isSelected;	// 경사로 선택 여부
	static boolean check;			// 해당 라인의 활주로 건설 여부
	
	public static boolean isAvailable(int row, int col, int dir) {

		// 현재 좌표를 기준으로 경사로를 설치했을 때, 경사로 끝의 좌표
		int endRow = row + DELTA_ROW[dir]*runwayLen;
		int endCol = col + DELTA_COL[dir]*runwayLen;
		
		// (1) 경사로가 지도의 범위를 벗어나는 경우, 설치 불가능
		if (endRow < 0 || endCol < 0 || endRow >= mapSize || endCol >= mapSize)
			return false;
		
		// (2) 현재 좌표를 기준으로 입력 방향으로 경사로 길이에 속하는 칸을 탐색한다.
		for (int len = 1; len <= runwayLen; len++) {
			int nextRow = row + DELTA_ROW[dir]*len;
			int nextCol = col + DELTA_COL[dir]*len;
			
			// (2-1) 이미 경사로가 설치된 칸이라면, 설치 불가능
			if ((dir < 2 && isSelected[nextRow]) || (dir >= 2 && isSelected[nextCol]))
				return false;
				
			// (2-2) 현재 좌표의 값보다 1 작은 값이 아니라면, 설치 불가능
			if (map[nextRow][nextCol] != map[row][col] - 1)
				return false;
		}
		
		// (3) 경사로 설치가 가능하다면 속한 칸들을 방문 처리 한다.
		for (int nextRow = row + DELTA_ROW[dir]; nextRow <= endRow; nextRow++) {
			for (int nextCol = col + DELTA_COL[dir]; nextCol <= endCol; nextCol++) {
				// 상하 -> 행 기준
				if (dir < 2)
					isSelected[nextRow] = true;
				
				// 좌우 -> 열 기준
				else
					isSelected[nextCol] = true;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 지도의 한 변의 길이와 경사로의 길이가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			mapSize = Integer.parseInt(st.nextToken());
			runwayLen = Integer.parseInt(st.nextToken());
			
			// 3. 지도의 지형 정보가 입력된다.
			map = new int[mapSize][mapSize];
			for (int row = 0; row < mapSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < mapSize; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 4. 가로 방향에 건설 가능한 활주로 개수를 구한다.
			int rowCnt = 0;
			for (int row = 0; row < mapSize; row++) {
				isSelected = new boolean[mapSize];	// 경사로 설치로 선택된 칸 저장
				check = true;	// 해당 라인에 활주로 건설 가능 여부
				
				for (int col = 1; col < mapSize; col++) {
					// 1) 이전 칸의 높이 == 현재 칸의 높이 => 패스
					if (map[row][col-1] == map[row][col]) continue;
					
					// 2) 이전 칸의 높이와 현재 칸의 높이 차가 2 이상인 경우 => 해당 라인에 활주로 건설 불가
					else if (Math.abs(map[row][col-1] - map[row][col]) >= 2) {
						check = false;
						break;
					}
					
					// 3) 이전 칸의 높이 > 현재 칸의 높이 => 이전 칸을 기준으로 오른쪽에 경사로를 놓을 수 있는지 확인한다.
					else if (map[row][col-1] > map[row][col]) {
						if (!isAvailable(row, col-1, RIGHT)) {
							check = false;
							break;
						}
					}
					
					// 4) 이전 칸의 높이 < 현재 칸의 높이 => 현재칸을 기준으로 왼쪽에 경사로를 놓을 수 있는지 확인한다.
					else if (map[row][col-1] < map[row][col]) {
						if (!isAvailable(row, col, LEFT)) {
							check = false;
							break;
						}
					}
				}
				
				if (check) {
					rowCnt++;
				}
			}
			
			// 5. 세로 방향에 건설 가능한 활주로 개수를 구한다.
			int colCnt = 0;
			for (int col = 0; col < mapSize; col++) {
				isSelected = new boolean[mapSize];
				check = true;
				
				for (int row = 1; row < mapSize; row++) {
					// 1) 이전 칸의 높이 == 현재 칸의 높이 => 패스
					if (map[row-1][col] == map[row][col]) continue;
					
					// 2) 이전 칸의 높이와 현재 칸의 높이 차가 2 이상인 경우 => 해당 라인에 활주로 건설 불가
					else if (Math.abs(map[row-1][col] - map[row][col]) >= 2) {
						check = false;
						break;
					}
					
					// 3) 이전 칸의 높이 > 현재 칸의 높이 => 이전 칸을 기준으로 오른쪽에 경사로를 놓을 수 있는지 확인한다.
					else if (map[row-1][col] > map[row][col]) {
						if (!isAvailable(row-1, col, DOWN)) {
							check = false;
							break;
						}
					}
					
					// 4) 이전 칸의 높이 < 현재 칸의 높이 => 현재칸을 기준으로 왼쪽에 경사로를 놓을 수 있는지 확인한다.
					else if (map[row-1][col] < map[row][col]) {
						if (!isAvailable(row, col, UP)) {
							check = false;
							break;
						}
					}
				}
				
				if (check) {
					colCnt++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(rowCnt + colCnt).append("\n");
		}
		System.out.println(sb);
	}
}