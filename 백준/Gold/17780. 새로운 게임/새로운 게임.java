/**
* 1. 체스판의 크기, 말의 개수가 입력된다.
* 2. 체스판의 정보가 입력된다.
* 3. 말의 정보가 입력된다.
*     - 행, 열, 이동 방향
* 4. 게임이 끝날 때까지 턴을 계속한다.
*     4-1. 턴의 횟수를 1 증가한다. 만약 값이 1000보다 크다면 종료
*     4-2. 1부터 k까지의 말을 이동시킨다.
*         1) 해당 말이 가장 아래에 있는 말이 아니라면 패스
*         2) 이동칸이 범위를 벗어나는 경우
*             - 이동 방향을 반대로 한다.
*             - 갱신된 이동칸이 범위를 벗어나거나 파란색이면, 패스
*             - 갱신된 이동칸의 색상에 따라 말을 해당 방향으로 이동시킨다.
*         3) 이동칸 = 흰색
*             - 이동칸으로 이동 + 말 쌓기
*             - 이동칸의 가장 아래, 위의 말 정보 갱신
*             - 이동칸의 총 말의 개수 갱신
*             - 이전 칸에서 이동된 말의 위치 갱신
*             - 이전 칸의 정보 초기화(말이 존재하지 않는 상태)
*         4) 이동칸 = 빨간색
*             - 현재 칸에 있는 말들의 쌓인 순서를 반대로 바꾼다.
*             - 이동칸으로 이동 + 말 쌓기 (= 흰색칸으로 이동)
*         5) 이동칸 = 파란색
*             - 범위를 벗어나는 경우와 똑같이 진행
*     4-3. 말이 4개 이상 쌓이는 순간 게임 종료
*/
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	// 체스칸 색 정보
	static final int WHITE = 0;
	static final int RED = 1;
	static final int BLUE = 2;

	// 이동 방향
	static final int[] DELTA_ROW = { 0, 0, -1, 1 };
	static final int[] DELTA_COL = { 1, -1, 0, 0 };

	static final int NONE = -1;

	// 말 정보
	static class Player {
		int row, col;	// 좌표
		int dir;		// 방향

		public Player(int row, int col, int dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
	
	// 체스칸 정보
	static class Point {
		int color;			// 색상
		int topPlayer;		// 가장 위 말 번호
		int bottomPlayer;	// 가장 아래 말 번호
		int cnt;			// 말 개수
		
		public Point(int color) {
			this.color = color;
			this.topPlayer = NONE;
			this.bottomPlayer = NONE;
			this.cnt = 0;
		}
	}

	static int chessSize; // 체스판 크기
	static int playerCnt; // 말 개수
	static Point[][] chess; // 체스판 정보
	static Player[] playerList; // 말 정보

	static int result;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 체스판의 크기, 말의 개수가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		chessSize = Integer.parseInt(st.nextToken());
		playerCnt = Integer.parseInt(st.nextToken());

		// 2. 체스판의 정보가 입력된다.
		chess = new Point[chessSize+1][chessSize+1];
		for (int row = 1; row <= chessSize; row++) {
			st = new StringTokenizer(br.readLine().trim());

			for (int col = 1; col <= chessSize; col++) {
				chess[row][col] = new Point(Integer.parseInt(st.nextToken()));
			}
		}

		// 3. 말의 정보가 입력된다.
		playerList = new Player[playerCnt];
		for (int idx = 0; idx < playerCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			// 말 정보 저장 & 체스판에 해당 말 위치시킨다.
			// 해당 칸의 가장 아래,위 말은 현재 말이고, 해당 칸의 초기 말 개수는 1이다.
			playerList[idx] = new Player(row, col, dir-1);
			chess[row][col].topPlayer = chess[row][col].bottomPlayer = idx;
			chess[row][col].cnt = 1;
		}

		// 4. 게임이 끝날 때까지 턴을 계속한다.
		result = 0;
		boolean flag = false;
		while (true) {
			// 4-1. 턴의 횟수를 1 증가한다. 만약 값이 1000보다 크다면 종료
			if (++result > 1000)
				break;
			
			// 4-2. 1부터 k까지의 말을 이동시킨다.
			for (int idx = 0; idx < playerCnt; idx++) {
				Player player = playerList[idx];
				int curRow = player.row;
				int curCol = player.col;
				int curDir = player.dir;

				// 1) 해당 말이 가장 아래에 있는 말이 아니라면 패스
				if (chess[curRow][curCol].bottomPlayer != idx)
					continue;

				int nextRow = curRow + DELTA_ROW[curDir];
				int nextCol = curCol + DELTA_COL[curDir];

				// 2) 이동칸이 범위를 벗어나는 경우
				if (nextRow <= 0 || nextCol <= 0 || nextRow > chessSize || nextCol > chessSize) {
					reverseDirectionAndMove(player, nextRow, nextCol);
				}
				else {					
					switch (chess[nextRow][nextCol].color) {
					// 3) 이동칸 = 흰색
					case WHITE:
						moveAndStack(player, nextRow, nextCol);
						break;
						
					// 4) 이동칸 = 빨간색
					case RED:
						reverseOrderAndMove(player, nextRow, nextCol);
						break;
						
					// 5) 이동칸 = 파란색
					case BLUE:
						reverseDirectionAndMove(player, nextRow, nextCol);
						break;
					}
				}
				
				// 4-3. 말이 4개 이상 쌓이는 순간 게임 종료
				if (chess[player.row][player.col].cnt >= 4) {
					flag = true;
					break;
				}
			}
			
			if (flag)
				break;
		}
		
		System.out.println(result > 1000 ? -1 : result);
	}
	
	// 이동칸이 범위를 벗어나거나 파란색인 경우
	public static void reverseDirectionAndMove(Player player, int nextRow, int nextCol) {
	
		// 이동 방향을 반대로 한다.
		int reverseDir = (player.dir % 2 == 0 ? player.dir+1 : player.dir-1);
		int rNextRow = player.row + DELTA_ROW[reverseDir];
		int rNextCol = player.col + DELTA_COL[reverseDir];
		
		player.dir = reverseDir;
		
		// 갱신된 이동칸이 범위를 벗어나거나 파란색이면, 패스
		if (rNextRow <= 0 || rNextCol <= 0 || rNextRow > chessSize || rNextCol > chessSize) {
			return;
		}
		
		// 갱신된 이동칸의 색상에 따라 말을 해당 방향으로 이동시킨다.
		if (chess[rNextRow][rNextCol].color == WHITE) {
			moveAndStack(player, rNextRow, rNextCol);
		}
		else if (chess[rNextRow][rNextCol].color == RED) {
			reverseOrderAndMove(player, rNextRow, rNextCol);
		}
	}
	
	// 이동칸이 흰색인 경우
	public static void moveAndStack(Player player, int nextRow, int nextCol) {
		int curRow = player.row;
		int curCol = player.col;
		int curBottom = chess[curRow][curCol].bottomPlayer;
		int curTop = chess[curRow][curCol].topPlayer;
		
		// 이동칸의 가장 아래, 위의 말 정보 갱신
		if (chess[nextRow][nextCol].bottomPlayer == NONE) {
			chess[nextRow][nextCol].bottomPlayer = curBottom;
		}
		chess[nextRow][nextCol].topPlayer = curTop;
		
		// 이동칸의 총 말의 개수 갱신
		chess[nextRow][nextCol].cnt += chess[player.row][player.col].cnt;
		
		// 이전 칸에서 이동된 말의 위치 갱신
		playerList[curBottom].row = playerList[curTop].row = nextRow;
		playerList[curBottom].col = playerList[curTop].col = nextCol;
		
		// 이전 칸의 정보 초기화(말이 존재하지 않는 상태)
		initPoint(curRow, curCol);
		 
	}
	
	// 이동칸이 빨간색인 경우
	public static void reverseOrderAndMove(Player player, int nextRow, int nextCol) {
		int curRow = player.row;
		int curCol = player.col;
		
		// 현재 칸에 있는 말들의 쌓인 순서를 반대로 바꾼다.
		int tmp = chess[curRow][curCol].bottomPlayer;
		chess[curRow][curCol].bottomPlayer = chess[curRow][curCol].topPlayer;
		chess[curRow][curCol].topPlayer = tmp;
		
		// 이동칸으로 이동 + 말 쌓기 (= 흰색칸으로 이동)
		moveAndStack(player, nextRow, nextCol);
	}
	
	public static void initPoint(int row, int col) {
		chess[row][col].cnt = 0;
		chess[row][col].bottomPlayer = chess[row][col].topPlayer = NONE;
	}
}