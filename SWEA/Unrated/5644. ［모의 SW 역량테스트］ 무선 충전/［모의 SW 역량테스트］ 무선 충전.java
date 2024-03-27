/**
 * 1. 테스트케이스의 개수가 입력된다.
 * 2. 총 이용 시간과 BC의 개수가 입력된다.
 * 3. 두 사용자의 이동 정보가 입력된다.
 * 4. BC의 정보가 입력된다. (좌표, 충전 범위, 처리량)
 * 
 * 5. 0초부터 이동 정보를 바탕으로 두 사용자를 움직인다.
 *  5-1. 현재 칸에서 연결할 수 있는 bc들을 각각 구한다.
 *  5-2. 두 사용자의 bc 리스트에서 각각 하나를 선택했을 때의 충전 양을 계산한다.
 *  	1) 1개의 BC만 존재하거나 2개의 BC가 같을 경우, 해당 BC의 성능 양만큼 반환
 *  	2) 다를 경우, 두 BC의 성능 합을 반환
 *  5-3. 모든 경우의 쌍 중 최대 충전 양을 선택하여 결과 충전 양에 합한다.
 *  5-4. 현재 시간이 총 이용 시간일 때 => 종료
 *  5-5. 아니라면 입력된 이동 방향으로 1칸 이동
 */
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class BC {
		int x, y; // 좌표
		int coverage, performance; // 충전 범위, 성능

		public BC(int x, int y, int coverage, int performance) {
			this.x = x;
			this.y = y;
			this.coverage = coverage;
			this.performance = performance;
		}

		// 사용자가 충전 범위 안에 들었는지 반환
		public boolean isInCoverage(int x, int y) {
			if (Math.abs(this.x - x) + Math.abs(this.y - y) <= this.coverage)
				return true;
			return false;
		}

		@Override
		public String toString() {
			return "BC [x=" + x + ", y=" + y + ", coverage=" + coverage + ", performance=" + performance + "]";
		}
	}

	// 방향 : 그대로/상/우/하/좌
	static final int[] DELTA_Y = { 0, -1, 0, 1, 0 };
	static final int[] DELTA_X = { 0, 0, 1, 0, -1 };

	static final int MAP_SIZE = 10; // 격자 크기
	static final int USER_CNT = 2; 	// 사용자 수
	static final int USER_A = 0; 	// 사용자 A 인덱스
	static final int USER_B = 1; 	// 사용자 B 인덱스

	static int totalTime; 			// 총 이용 시간
	static int bcCnt; 				// bc 개수
	static int[][] userPoint; 		// 사용자 좌표 (x, y)
	static int[][] userMoveInfo; 	// 사용자 이동 정보
	static BC[] batteryCharger; 	// bc 정보
	static int totalCharge; 		// 모든 사용자의 총 충전한 양

	public static int getCharge(BC chargerA, BC chargerB) {
		// 1) 1개의 BC만 존재하거나 2개의 BC가 같을 경우, 해당 BC의 성능 양만큼 반환
		if (chargerB == null || chargerA == chargerB)
			return chargerA.performance;
		
		// 2) 다를 경우, 두 BC의 성능 합을 반환
		return chargerA.performance + chargerB.performance;
	}
	
	public static void moveUsers(int time) {
		// 5-1. 현재 칸에서 연결할 수 있는 bc들을 각각 구한다.
		List<BC> userACharger = new ArrayList<>();
		List<BC> userBCharger = new ArrayList<>();

		for (int user = 0; user < USER_CNT; user++) {
			// 현재 사용자 좌표
			int currentRow = userPoint[user][0];
			int currentCol = userPoint[user][1];

			for (BC bc : batteryCharger) {
				// 사용자가 충전 가능한 범위 안에 존재하는 경우
				if (bc.isInCoverage(currentRow, currentCol)) {
					if (user == USER_A)
						userACharger.add(bc);
					else
						userBCharger.add(bc);
				}
			}
		}

		// 5-2. 두 사용자의 bc 리스트에서 각각 하나를 선택했을 때의 충전 양을 계산한다.
		// 사용자 B만 충전 가능한 BC가 있는 경우
		int maxCharge = 0;
		if (userACharger.isEmpty()) {
			for (BC chargerB : userBCharger) {
				maxCharge = Math.max(maxCharge, getCharge(chargerB, null));
			}
		}
		// 사용자 A만 충전 가능한 BC가 있는 경우
		else if (userBCharger.isEmpty()) {
			for (BC chargerA : userACharger) {
				maxCharge = Math.max(maxCharge, getCharge(chargerA, null));
			}
		}
		else {			
			for (BC chargerA : userACharger) {
				for (BC chargerB : userBCharger) {
					maxCharge = Math.max(maxCharge, getCharge(chargerA, chargerB));
				}
			}
		}

		// 5-3. 모든 경우의 쌍 중 최대 충전 양을 선택하여 결가 충전 양에 합한다.
		totalCharge += maxCharge;

		// 5-4. 현재 시간이 총 이용 시간일 때 => 종료
		if (time == totalTime)
			return;

		// 5-5. 아니라면 입력된 이동 방향으로 1칸 이동
		for (int user = 0; user < USER_CNT; user++) {
			int currentDir = userMoveInfo[user][time];
			userPoint[user][0] += DELTA_X[currentDir];
			userPoint[user][1] += DELTA_Y[currentDir];
		}
		moveUsers(time + 1);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		userPoint = new int[USER_CNT][2];

		// 1. 테스트케이스의 개수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; tc++) {
			// init
			userPoint[USER_A][0] = 1;
			userPoint[USER_A][1] = 1;
			userPoint[USER_B][0] = MAP_SIZE;
			userPoint[USER_B][1] = MAP_SIZE;
			totalCharge = 0;

			// 2. 총 이용 시간과 BC의 개수가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			totalTime = Integer.parseInt(st.nextToken());
			bcCnt = Integer.parseInt(st.nextToken());

			userMoveInfo = new int[USER_CNT][totalTime];
			batteryCharger = new BC[bcCnt];

			// 3. 두 사용자의 이동 정보가 입력된다.
			for (int user = 0; user < USER_CNT; user++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int time = 0; time < totalTime; time++)
					userMoveInfo[user][time] = Integer.parseInt(st.nextToken());
			}

			// 4. BC의 정보가 입력된다. (좌표, 충전 범위, 처리량)
			for (int idx = 0; idx < bcCnt; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				batteryCharger[idx] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// 5. 0초부터 이동 정보를 바탕으로 두 사용자를 움직인다.
			moveUsers(0);
			
			sb.append("#").append(tc).append(" ").append(totalCharge).append("\n");
		}
		System.out.println(sb);
	}
}