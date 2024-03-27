/**
 * 1. 테스트 케이스 개수를 입력한다.
 * 2. 달팽이 크기를 입력한다.
 * 3. 배열의 값을 채운다. (1 ~ size*size)
 * 	3-1. 현재 좌표에 숫자를 작성한다.
 * 	3-2. 현재의 방향으로 이동할 칸을 구한다.
 * 	3-3. 만약 이동할 칸이 범위 밖이면, 방향을 전환한다.
 * 	3-4. 만약 이동할 칸의 값이 0이 아니면, 방향을 전환한다.
 * 	3-5. 현재 좌표를 이동할 칸의 좌표로 저장한다.
 * 	(방향 전환 순서 : 우 -> 하 -> 좌 -> 상)
 * 4. 결과 저장
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int[] DELTA_R = {0, 1, 0, -1};
	static final int[] DELTA_C = {1, 0, -1, 0};
	
	static int snailSize;	// 달팽이 크기
	static int[][] snail;	// 달팽이 정보
	
	static int currentRow, currentCol;	// 현재 좌표
	static int nextRow, nextCol;	// 이동할 좌표
	static int currentDir;		// 현재 방향
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력한다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 달팽이 크기를 입력한다.
			snailSize = Integer.parseInt(br.readLine().trim());
			snail = new int[snailSize][snailSize];
			
			// init
			currentRow = 0; currentCol = 0;
			currentDir = 0;
			
			// 3. 배열의 값을 채운다. (1 ~ size*size)
			for (int num = 1; num <= snailSize * snailSize; num++) {
				// 3-1. 현재 좌표에 숫자를 작성한다.
				snail[currentRow][currentCol] = num;
				
				// 3-2. 현재의 방향으로 이동할 칸을 구한다.
				nextRow = currentRow + DELTA_R[currentDir];
				nextCol = currentCol + DELTA_C[currentDir];
				
				// 3-3. 만약 이동할 칸이 범위 밖이면, 방향을 전환한다.
				if (nextRow < 0 || nextCol < 0 || nextRow >= snailSize || nextCol >= snailSize) {
					currentDir = (currentDir + 1) % 4;
					nextRow = currentRow + DELTA_R[currentDir];
					nextCol = currentCol + DELTA_C[currentDir];
				}
				
				// 3-4. 만약 이동할 칸의 값이 0이 아니면, 방향을 전환한다.
				else if (snail[nextRow][nextCol] != 0) {
					currentDir = (currentDir + 1) % 4;
					nextRow = currentRow + DELTA_R[currentDir];
					nextCol = currentCol + DELTA_C[currentDir];
				}
				
				// 3-5. 현재 좌표를 이동할 칸의 좌표로 저장한다.
				currentRow = nextRow;
				currentCol = nextCol;
			}
			
			// 4. 결과 저장
			sb.append("#").append(tc).append("\n");
			for (int row = 0; row < snailSize; row++) {
				for (int col = 0; col < snailSize; col++) {
					sb.append(snail[row][col]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}