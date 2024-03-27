/**
 * // 1. 테스트 케이스의 번호 입력받는다.
 * 2. 100 x 100 크기의 배열 값을 입력받는다.
 * 3. 마지막 행에서 배열값이 2인 인덱스를 찾는다. (도착점)
 * 4. 도착 지점에서부터 사다리를 타고 올라가 대응되는 출발점을 찾는다.
 * 	4-1. 기저 조건
 * 		- 현재의 행이 첫 번째 행인 경우, 현재 열 append 후 종료
 * 
 * 	4-2. 현재 방향이 '상'인 경우
 * 		4-2-1. 좌 방향으로 갈 수 있다면, 다음 이동 방향 = 좌
 * 			- 해당 방향으로 갈 수 있는지 확인하는 방법
 * 			1) 해당 방향으로 행/열 이동
 * 			2) 범위 밖의 칸인 경우, false 리턴
 * 			3) 사다리(1)가 아닌 칸인 경우, false 리턴
 * 			4) 그 외의 경우, true 리턴
 * 
 * 		4-2-2. 우 방향으로 갈 수 있다면, 다음 이동 방향 = 우
 * 
 * 	4-3. 현재 방향이 '좌/우'인 경우
 * 		4-3-1. 상 방향으로 갈 수 있다면, 다음 이동 방향 = 상
 * 	4-4. 재귀 호출
 * 		- 다음 방향의 칸으로 재귀 호출
 * 
 * 5. 결과 출력
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int TEST_CASE = 10;
	static final int ARRAY_SIZE = 100;		// 배열의 크기
	static final int[][] DELTA = {{-1, 0}, {0, -1}, {0, 1}};	// 방향 {행, 열} - 상, 좌, 우
	
	static int[][] array;	// 배열 저장 값
	
	public static boolean isSuitable(int currentRow, int currentCol, int moveDir) {
		// 1) 해당 방향으로 행/열 이동
		int nextRow = currentRow + DELTA[moveDir][0];
		int nextCol = currentCol + DELTA[moveDir][1];
		
		// 2) 범위 밖의 칸인 경우, false 리턴
		if (nextRow < 0 || nextCol < 0 || nextRow >= ARRAY_SIZE || nextCol >= ARRAY_SIZE)
			return false;
		
		// 3) 사다리(1)가 아닌 칸인 경우, false 리턴
		if (array[nextRow][nextCol] != 1)
			return false;
		
		// 4) 그 외의 경우, true 리턴
		return true;
	}
	
	// 현재 행, 현재 열, 현재 방향
	public static void findStartColumn(int currentRow, int currentCol, int currentDir) {
		// 4-1. 기저 조건
		// 현재의 행이 첫 번째 행인 경우, 현재 열 append 후 종료
		if (currentRow == 0) {
			sb.append(currentCol);
			return;
		}
		
		int nextDir = currentDir;		// 다음으로 이동할 방향 (default : 현재 방향)
		
		// 4-2. 현재 방향이 '상'인 경우
		if (currentDir == 0) {
			
			// 4-2-1. 좌 방향으로 갈 수 있다면, 다음 이동 방향 = 좌
			if (isSuitable(currentRow, currentCol, 1)) {
				nextDir = 1;
			}
			
			// 4-2-2. 우 방향으로 갈 수 있다면, 다음 이동 방향 = 우
			else if (isSuitable(currentRow, currentCol, 2)) {
				nextDir = 2;
			}

		}
		
		// 4-3. 현재 방향이 '좌/우'인 경우
		else if (currentDir == 1 || currentDir == 2) {
			// 4-3-1. 상 방향으로 갈 수 있다면, 상 방향의 칸으로 재귀 호출
			// 상 방향으로 갈 수 있다면, 다음 이동 방향 = 상
			if (isSuitable(currentRow, currentCol, 0)) {
				nextDir = 0;
			}
		}
		
		// 4-4. 재귀 호출
		// 다음 방향의 칸으로 재귀 호출
		findStartColumn(currentRow + DELTA[nextDir][0], currentCol + DELTA[nextDir][1], nextDir);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int tc = 1; tc <= TEST_CASE; tc++) {
			array = new int[ARRAY_SIZE][ARRAY_SIZE];
			
			sb.setLength(0);
			sb.append("#").append(tc).append(" ");
			
			// 1. 테스트케이스의 번호 입력받는다.
			br.readLine().trim();
			
			// 2. 100 x 100 크기의 배열 값을 입력받는다.
			for (int row = 0; row < ARRAY_SIZE; row++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for (int col = 0; col < ARRAY_SIZE; col++) {
					array[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 3. 마지막 행에서 배열값이 2인 인덱스를 찾는다. (도착점)
			for (int col = 0; col < ARRAY_SIZE; col++) {
				if (array[ARRAY_SIZE-1][col] == 2) {
					// 4. 도착 지점에서부터 사다리를 타고 올라가 대응되는 출발점을 찾는다.
					findStartColumn(ARRAY_SIZE-1, col, 0);
				}
			}
			
			// 5. 결과 출력
			System.out.println(sb);
		}
	}
}