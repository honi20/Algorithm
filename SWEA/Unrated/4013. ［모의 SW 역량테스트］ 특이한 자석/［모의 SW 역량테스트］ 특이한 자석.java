/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 자석 회전 횟수가 입력된다.
 * 3. 각 자석 별 자성정보가 입력된다.
 * 4. 입력된 자석 회전 정보(자석 번호, 회전 방향)대로 회전을 진행한다.
 * 	4-1. 기준 자석이거나 탐색 방향이 왼쪽이라면, 현재 자석을 기준으로 왼쪽 자석을 탐색한다.
 * 		- 현재 자석이 시작 자석이 아니고, 왼쪽 자석의 RIGHT 값과 현재 자석의 LEFT 값이 다른 경우 => 계속해서 탐색&회전 진행
 * 	4-2. 기준 자석이거나 탐색 방향이 오른쪽이라면, 현재 자석을 기준으로 오른쪽 자석을 탐색한다.
 * 		- 현재 자석이 마지막 자석이 아니고, 오른쪽 자석의 LEFT 값과 현재 자석의 RIGHT 값이 다른 경우 => 계속해서 탐색&회전 진행
 * 	4-3. 현재 자석을 해당 방향으로 회전시킨다.
 * 		1) 시계방향으로 회전 -> 마지막 값을 맨 앞 인덱스에 저장하고, 나머지 배열 값들은 인덱스 한 칸씩 뒤로 이동한다.
 * 		2) 반시계방향으로 회전 -> 첫번째 값을 맨 뒤 인덱스에 저장하고, 나머지 배열 값들은 인덱스 한 칸씩 앞으로 이동한다.
 * 5. 점수를 계산한다.
 * 	- top의 값이 S극이라면, + 2^(idx-1)점
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 입력 정보
	static final int S = 1;
	static final int CLOCKWISE = 1;
	static final int NOT_CLOCKWISE = -1;
	
	// 방향 정보
	static final int MAGNET_CNT = 4;
	static final int DIR_CNT = 8;
	static final int TOP = 0;
	static final int RIGHT = 2;
	static final int LEFT = 6;
	
	static int testCase;	// 테스트케이스 개수
	static int rotationCnt;	// 회전 횟수
	static int[][] magnets;	// 자석 자성 정보
	
	public static void rotate(int currentNum, int direction) {
		// 1) 시계방향으로 회전 -> 마지막 값을 맨 앞 인덱스에 저장하고, 나머지 배열 값들은 인덱스 한 칸씩 뒤로 이동한다.
		if (direction == CLOCKWISE) {
			int lastValue = magnets[currentNum][DIR_CNT-1];
			for (int dir = DIR_CNT-2; dir >= 0; dir--) {
				magnets[currentNum][dir+1] = magnets[currentNum][dir];
			}
			magnets[currentNum][TOP] = lastValue;
		}
		
		// 2) 반시계방향으로 회전 -> 첫번째 값을 맨 뒤 인덱스에 저장하고, 나머지 배열 값들은 인덱스 한 칸씩 앞으로 이동한다.
		else if (direction == NOT_CLOCKWISE) {
			int firstValue = magnets[currentNum][0];
			for (int dir = 1; dir < DIR_CNT; dir++) {
				magnets[currentNum][dir-1] = magnets[currentNum][dir];
			}
			magnets[currentNum][DIR_CNT-1] = firstValue;
		}
	}
	
	public static void search(int currentNum, int rotateDir, String searchDir) {
		// 4-1. 기준 자석이거나 탐색 방향이 왼쪽이라면, 현재 자석을 기준으로 왼쪽 자석을 탐색한다.
		if (searchDir.equals("BASE") || searchDir.equals("L")) {
			// 현재 자석이 시작 자석이 아니고, 왼쪽 자석의 RIGHT 값과 현재 자석의 LEFT 값이 다른 경우 => 계속해서 탐색&회전 진행
			if (currentNum > 1 && magnets[currentNum][LEFT] != magnets[currentNum-1][RIGHT]) {
				// 지금 방향이 시계방향 -> 다음 자석은 반시계방향
				if (rotateDir == CLOCKWISE)
					search(currentNum-1, NOT_CLOCKWISE, "L");
				// 지금 방향이 반시계방향 -> 다음 자석은 시계방향
				else
					search(currentNum-1, CLOCKWISE, "L");
			}
		}
		
		// 4-2. 기준 자석이거나 탐색 방향이 오른쪽이라면, 현재 자석을 기준으로 오른쪽 자석을 탐색한다.
		if (searchDir.equals("BASE") || searchDir.equals("R")) {
			// 현재 자석이 마지막 자석이 아니고, 오른쪽 자석의 LEFT 값과 현재 자석의 RIGHT 값이 다른 경우 => 계속해서 탐색&회전 진행
			if (currentNum < MAGNET_CNT && magnets[currentNum][RIGHT] != magnets[currentNum+1][LEFT]) {
				if (rotateDir == CLOCKWISE)
					search(currentNum+1, NOT_CLOCKWISE, "R");
				else
					search(currentNum+1, CLOCKWISE, "R");
			}
		}
		
		// 4-3. 현재 자석을 해당 방향으로 회전시킨다.
		rotate(currentNum, rotateDir);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 자석 회전 횟수가 입력된다.
			rotationCnt = Integer.parseInt(br.readLine().trim());
			
			// 3. 각 자석 별 자성정보가 입력된다.
			magnets = new int[MAGNET_CNT+1][DIR_CNT];
			for (int idx = 1; idx <= MAGNET_CNT; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int dir = 0; dir < DIR_CNT; dir++)
					magnets[idx][dir] = Integer.parseInt(st.nextToken());
			}
			
			// 4. 입력된 자석 회전 정보(자석 번호, 회전 방향)대로 회전을 진행한다.
			for (int cnt = 0; cnt < rotationCnt; cnt++) {
				st = new StringTokenizer(br.readLine().trim());
				int currentNum = Integer.parseInt(st.nextToken());
				int rotateDir = Integer.parseInt(st.nextToken());
				search(currentNum, rotateDir, "BASE");
			}
			
			// 5. 점수를 계산한다.
			int score = 0;
			for (int idx = 1; idx <= MAGNET_CNT; idx++) {
				// top의 값이 S극이라면, 2^(idx-1)점
				if (magnets[idx][TOP] == S)
					score += Math.pow(2, idx-1);
			}
			
			sb.append("#").append(tc).append(" ").append(score).append("\n");
		}
		System.out.println(sb);
	}
}