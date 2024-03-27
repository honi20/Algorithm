/**
 * 1. 테스트 케이스의 개수가 입력된다
 * 2. 하나의 정수(배열 크기)가 입력된다.
 * 3. 각 방들의 숫자가 입력된다.
 * 4. 현재 칸으로부터 이동할 수 있는 방 탐색
 * 	4-1. 현재 칸으로부터 상하좌우의 칸으로 이동가능한지 확인
 * 	4-2. 다음 이동 칸이 범위 밖인 경우, 패쓰
 * 	4-3. 다음 이동 칸이 현재 칸의 숫자 + 1이 아닌 경우, 패쓰
 * 	4-4. 다음 이동 칸으로 재귀 호출한다.
 * 5. 현재 칸에서 이동한 방의 개수 계산 및 결과값 갱신
 * 	5-1. 이동한 방의 개수가 최대 이동 방의 개수와 같은 경우
 * 		- 현재 방의 숫자와 최대 이동 방의 숫자를 비교 --> 현재 방의 숫자가 작다면 갱신
 * 	5-2. 이동한 방의 개수가 최대 이동 방의 개수보다 큰 경우, 결과값 갱신
 */
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 상 우 하 좌
	static final int DIR_COUNT = 4;
	static final int[] DELTA_R = {-1,0,1,0};
	static final int[] DELTA_C = {0,1,0,-1};
	
	static int arraySize;		// 배열 크기
	static int[][] rooms;			// 방들의 숫자 정보
	static boolean[][] isVisited;	// 방 이동 여부 저장
	
	static int maximumMoving;	// 최대 이동한 방의 개수
	static int minimumNum;			// 최대 이동 개수를 갖는 방의 숫자

	public static void getMaximumMoving(int groupCount, int roomNum) {
		// 5-1. 이동한 방의 개수가 최대 이동 방의 개수와 같은 경우
		// 현재 방의 숫자와 최대 이동 방의 숫자를 비교 --> 현재 방의 숫자가 작다면 갱신
		if (groupCount == maximumMoving) {
			if (roomNum < minimumNum) {
				minimumNum = roomNum;
			}
		}
		
		// 5-2. 이동한 방의 개수가 최대 이동 방의 개수보다 큰 경우, 결과값 갱신
		if (groupCount > maximumMoving) {
			maximumMoving = groupCount;
			minimumNum = roomNum;
		}
	}
	
	public static void dfs(int currentRow, int currentCol, int groupCount, int roomNum) {
		getMaximumMoving(groupCount, roomNum);
		
		// 4-1. 현재 칸으로부터 상하좌우의 칸으로 이동가능한지 확인
		for (int dir = 0; dir < DIR_COUNT; dir++) {
			int nextRow = currentRow + DELTA_R[dir];
			int nextCol = currentCol + DELTA_C[dir];
			
			// 4-2. 다음 이동 칸이 범위 밖인 경우, 패쓰
			if (nextRow < 0 || nextCol < 0 || nextRow >= arraySize || nextCol >= arraySize)
				continue;
			
			// 4-3. 다음 이동 칸이 현재 칸의 숫자 + 1이 아닌 경우, 패쓰
			if (rooms[currentRow][currentCol] + 1 != rooms[nextRow][nextCol])
				continue;
			
			// 4-4. 다음 이동 칸으로 재귀 호출한다.
			isVisited[nextRow][nextCol] = true;
			roomNum = Math.min(roomNum, rooms[nextRow][nextCol]);
			dfs(nextRow, nextCol, groupCount + 1, roomNum);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수가 입력된다
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			maximumMoving = 0;
			
			// 2. 하나의 정수(배열 크기)가 입력된다.
			arraySize = Integer.parseInt(br.readLine().trim());
			rooms = new int[arraySize][arraySize];
			isVisited = new boolean[arraySize][arraySize];
			
			// 3. 각 방들의 숫자가 입력된다.
			for (int row = 0; row < arraySize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < arraySize; col++) {
					rooms[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int row = 0; row < arraySize; row++) {
				for (int col = 0; col < arraySize; col++) {
					if (isVisited[row][col])
						continue;
					
					// 4. 현재 칸으로부터 이동할 수 있는 방 탐색
					isVisited[row][col] = true;
					dfs(row, col, 1, rooms[row][col]);
				}
			}
			
			// 출력
			sb.append("#").append(tc).append(" ").append(minimumNum).append(" ").append(maximumMoving).append("\n");
		}
		System.out.println(sb);
	}
}