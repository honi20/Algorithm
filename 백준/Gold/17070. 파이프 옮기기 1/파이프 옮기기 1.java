/**
 * 1. 집의 크기가 입력된다.
 * 2. 집의 상태가 입력된다.
 * 3. 파이프를 끝까지 이동시키는 방법의 개수를 구한다.
 * 	3-1. 초기화 - 처음 파이프의 위치는 (1,1)와 (1,2) => (1,2)의 가로 개수에 1 저장
 * 	3-2. 현재 칸을 기준으로 파이프를 놓았을 때의 다음 칸에 경우의 수를 증가한다.
 * 		3-2-1. 파이프를 놓고자 하는 칸이 범위 벗어나면 패스
 * 		3-2-2. 파이프를 놓고자 하는 칸이 벽이면 패스
 * 		3-2-3. 대각선 파이프를 놓고자 하는 경우
 * 			- 아래, 오른쪽, 대각선의 칸 모두 빈 칸이어야 함
 * 			- 가로, 세로 파이프 -> 대각선 파이프 가능
 * 		3-2-4. 가로 또는 세로 파이프를 놓고자 하는 경우
 * 			- 가로 -> 가로 / 세로 -> 세로 가능
 * 		3-2-5. 대각선 -> 가로&세로&대각선 가능
 * 4. 마지막 칸에 저장된 모든 경우의 수를 더하여 출력한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 방향 : 오른쪽, 아래, 오른쪽 아래 대각선
	static final int DELTA_CNT = 3;
	static final int[] DELTA_ROW = {0,1,1};
	static final int[] DELTA_COL = {1,0,1};
	
	static final int HORIZON = 0;	// 가로
	static final int VERTICAL = 1;	// 세로
	static final int DIAGONAL = 2;	// 대각선
	
	static final int EMPTY = 0;		// 빈 칸
	static final int WALL = 1;		// 벽
	
	static int homeSize;	// 집 크기
	static int[][] home;	// 집 정보
	static int[][][] dp;	// 현재 칸까지 올 수 있는 파이프 경로 경우의 수 (현재 칸에 도착한 파이프 방향에 따라)
	
	public static void getMovePipeCnt() {
		dp = new int[homeSize + 1][homeSize + 1][3];
		
		// 3-1. 초기화
		// 처음 파이프의 위치는 (1,1)와 (1,2) => (1,2)의 가로 개수에 1 저장
		dp[1][2][HORIZON] = 1;
		
		// 3-2. 현재 칸을 기준으로 파이프를 놓았을 때의 다음 칸에 경우의 수를 증가한다.
		for (int row = 1; row <= homeSize; row++) {
			for (int col = 1; col <= homeSize; col++) {
				// 처음 파이프 고정
				if (row == 1 && col < 2)
					continue;
				
				for (int dir = 0; dir < DELTA_CNT; dir++) {
					int nextRow = row + DELTA_ROW[dir];
					int nextCol = col + DELTA_COL[dir];
					
					// 3-2-1. 파이프를 놓고자 하는 칸이 범위 벗어나면 패스
					if (nextRow <= 0 || nextCol <= 0 || nextRow > homeSize || nextCol > homeSize)
						continue;
					
					// 3-2-2. 파이프를 놓고자 하는 칸이 벽이면 패스
					if (home[nextRow][nextCol] == WALL)
						continue;
					
					// 3-2-3. 대각선 파이프를 놓고자 하는 경우
					if (dir == DIAGONAL) {
						// 아래, 오른쪽, 대각선의 칸 모두 빈 칸이어야 함
						if (home[nextRow-1][nextCol] == WALL || home[nextRow][nextCol-1] == WALL)
							continue;
						
						// 가로, 세로 파이프 -> 대각선 파이프 가능
						dp[nextRow][nextCol][DIAGONAL] += dp[row][col][HORIZON];
						dp[nextRow][nextCol][DIAGONAL] += dp[row][col][VERTICAL];
					}
					
					// 3-2-4. 가로 또는 세로 파이프를 놓고자 하는 경우
					else {
						// 가로 -> 가로 / 세로 -> 세로 가능
						dp[nextRow][nextCol][dir] += dp[row][col][dir];
					}
					
					// 3-2-5. 대각선 -> 가로&세로&대각선 가능
					dp[nextRow][nextCol][dir] += dp[row][col][DIAGONAL];
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 집의 크기가 입력된다.
		homeSize = Integer.parseInt(br.readLine().trim());
		
		// 2. 집의 상태가 입력된다.
		home = new int[homeSize + 1][homeSize + 1];
		for (int row = 1; row <= homeSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 1; col <= homeSize; col++) {
				home[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 3. 파이프를 끝까지 이동시키는 방법의 개수를 구한다.
		getMovePipeCnt();
		
		// 4. 마지막 칸에 저장된 모든 경우의 수를 더하여 출력한다.
		int result = 0;
		for (int dir = 0; dir < DELTA_CNT; dir++)
			result += dp[homeSize][homeSize][dir];
		
		System.out.println(result);
	}
}