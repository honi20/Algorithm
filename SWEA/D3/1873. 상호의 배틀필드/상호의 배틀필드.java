/**
 * 1. 테스트 케이스의 수가 입력된다.
 * 2. 게임 맵의 높이, 너비가 입력된다.
 * 3. 맵의 초기 상태가 압력된다.
 * 4. 사용자 입력 개수가 입력된다.
 * 5. 사용자 입력이 문자열로 입력된다.
 * 
 * 6. 사용자 입력 순서대로 게임을 실행한다.
 * 7. 입력이 S인 경우, 현재 전차 방향으로 포탄을 발사한다.
 * 	7-1. 게임 맵 범위를 벗어나는 경우 => 종료
 * 	7-2. 벽돌로 만들어진 벽과 부딪힌 경우, 해당 칸을 평지로 => 종료
 * 	7-3. 강철로 만들어진 벽과 부딪힌 경우 => 종료
 * 
 * 8. 그 외의 입력인 경우, 입력에 맞는 방향으로 전환 후 1칸 이동한 칸이 평지라면 이동한다.
 * 	8-1. 입력에 맞게 맞는 방향으로 전환한다.
 * 	8-2. 게임 맵 밖이라면 이동하지 않음
 * 	8-3. 평지가 아니라면 이동하지 않음
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	// 방향 : 상하좌우
	static final int[] DELTA_ROW = { -1, 1, 0, 0 };
	static final int[] DELTA_COL = { 0, 0, -1, 1 };
	static final int DELTA_UP = 0;
	static final int DELTA_DOWN = 1;
	static final int DELTA_LEFT = 2;
	static final int DELTA_RIGHT = 3;

	// 구성 요소
	static final char UP = '^';
	static final char DOWN = 'v';
	static final char LEFT = '<';
	static final char RIGHT = '>';
	static final char IS_PLAIN = '.';
	static final char IS_WALL_BY_BRICK = '*';
	static final char IS_WALL_BY_STEEL = '#';

	static int rowSize, colSize; // 게임 맵의 세로, 가로 크기
	static char[][] gameMap; // 게임 맵 상태
	static int userInputCnt; // 사용자 입력 개수
	static String userInput; // 사용자 입력

	static int direction; // 전차 방향
	static int tankRow, tankCol; // 전차 좌표

	public static void fireShell() {
		int dist = 1;
		while (true) {
			int currentRow = tankRow + dist * DELTA_ROW[direction];
			int currentCol = tankCol + dist * DELTA_COL[direction];

			// 7-1. 게임 맵 범위를 벗어나는 경우 => 종료
			if (currentRow < 0 || currentCol < 0 || currentRow >= rowSize || currentCol >= colSize)
				break;

			// 7-2. 벽돌로 만들어진 벽과 부딪힌 경우, 해당 칸을 평지로 => 종료
			if (gameMap[currentRow][currentCol] == IS_WALL_BY_BRICK) {
				gameMap[currentRow][currentCol] = IS_PLAIN;
				break;
			}

			// 7-3. 강철로 만들어진 벽과 부딪힌 경우 => 종료
			if (gameMap[currentRow][currentCol] == IS_WALL_BY_STEEL)
				break;

			dist++;
		}
	}

	public static void changeDirAndMove(char input) {
		char directionChar = 0;
		
		// 8-1. 입력에 맞게 맞는 방향으로 전환한다.
		switch (input) {
		case 'U':
			direction = DELTA_UP; directionChar = UP;
			break;
		case 'D':
			direction = DELTA_DOWN; directionChar = DOWN;
			break;
		case 'L':
			direction = DELTA_LEFT; directionChar = LEFT;
			break;
		case 'R':
			direction = DELTA_RIGHT; directionChar = RIGHT;
			break;
		}
		gameMap[tankRow][tankCol] = directionChar;
	
		// 이동할 칸의 좌표
		int nextRow = tankRow + DELTA_ROW[direction];
		int nextCol = tankCol + DELTA_COL[direction];

		// 8-2. 게임 맵 밖이라면 이동하지 않음
		if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize)
			return;

		// 8-3. 평지가 아니라면 이동하지 않음
		if (gameMap[nextRow][nextCol] != IS_PLAIN)
			return;

		// 전차 이동
		gameMap[tankRow][tankCol] = IS_PLAIN;
		tankRow = nextRow; tankCol = nextCol;
		gameMap[tankRow][tankCol] = directionChar;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트 케이스의 수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 게임 맵의 높이, 너비가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			gameMap = new char[rowSize][colSize];

			// 3. 맵의 초기 상태가 압력된다.
			for (int row = 0; row < rowSize; row++) {
				String str = br.readLine().trim();
				for (int col = 0; col < colSize; col++) {
					gameMap[row][col] = str.charAt(col);

					// 입력이 전차라면, 방향 및 위치 저장
					switch (gameMap[row][col]) {
					case UP:
						direction = DELTA_UP;
						tankRow = row;
						tankCol = col;
						break;
					case DOWN:
						direction = DELTA_DOWN;
						tankRow = row;
						tankCol = col;
						break;
					case LEFT:
						direction = DELTA_LEFT;
						tankRow = row;
						tankCol = col;
						break;
					case RIGHT:
						direction = DELTA_RIGHT;
						tankRow = row;
						tankCol = col;
						break;
					}
				}
			}
			
			// 4. 사용자 입력 개수가 입력된다.
			userInputCnt = Integer.parseInt(br.readLine().trim());

			// 5. 사용자 입력이 문자열로 입력된다.
			userInput = br.readLine().trim();

			// 6. 사용자 입력 순서대로 게임을 실행한다.
			for (int idx = 0; idx < userInputCnt; idx++) {
				char input = userInput.charAt(idx);

				switch (input) {
				// 7. 입력이 S인 경우, 현재 전차 방향으로 포탄을 발사한다.
				case 'S':
					fireShell();
					break;

				// 8. 그 외의 입력인 경우, 입력에 맞는 방향으로 전환 후 1칸 이동한 칸이 평지라면 이동한다.
				case 'U':
				case 'D':
				case 'L':
				case 'R':
					changeDirAndMove(input);
					break;
				}
			}

			sb.append("#").append(tc).append(" ");
			for (int row = 0; row < rowSize; row++) {
				for (char value : gameMap[row])
					sb.append(value);
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}