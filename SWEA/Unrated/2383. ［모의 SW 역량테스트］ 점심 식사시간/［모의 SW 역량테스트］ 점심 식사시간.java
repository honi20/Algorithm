/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 방의 한 변의 길이가 입력된다.
 * 3. 지도 정보가 입력된다.
 * 	- 1 : 사람, 2 이상 : 계단 입구(계단의 길이)
 * 
 * 4. 1번 계단을 선택한 사람의 부분 집합 선택
 * 	4-1. 종료조건 : 모든 사람들을 탐색한 경우, 모든 사람의 이동 시간 계산(5번) 후 최소값 갱신
 * 	4-2. 현재 원소를 1번 계단의 사람으로 선택하는 경우
 * 	4-3. 현재 원소를 1번 계단의 사람으로 선택하지 않는 경우(2번 계단 이용)
 * 5. 모든 사람이 각자 선택한 계단을 통해 내려가 이동 완료하는 시간 계산
 * 	5-1. 각 계단 별 선택된 사람들을 모두 이동시키는데 걸리는 시간을 계산한다.
 * 		1) 선택된 사람들의 각 계단까지의 거리 계산
 * 		2) 계단까지의 거리 리스트 오름차순 정렬
 * 		3) 시간을 늘려가며 사람들을 이동시킨다.
 * 			3-1) 앞에서 최대 3번째까지 사람의 이동 칸을 1씩 감소한다.
 * 			3-2) 모두 이동을 완료하면 종료
 * 			3-3) 현재 시간과 계단에 도착하는 시간이 같은 사람들을 리스트에 넣는다.
 * 	5-2. 둘 중 큰 값이 현재 부분 집합에 대한 이동 시간이 된다. (최솟값과 비교 후 갱신)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 좌표
	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	// 계단 정보
	static class Stairs {
		Point point;
		int length;		// 계단 길이
		
		public Stairs(Point point, int length) {
			this.point = point;
			this.length = length;
		}
	}
	
	// 배열 값 정보
	static final int PERSON = 1;
	static final int MIN_STAIRS = 2;
	
	static int arraySize;			// 방 길이
	static int[][] array;			// 방 정보
	static List<Point> peopleList;	// 사람 좌표
	static int stairsIdx;			// 계단 인덱스
	static Stairs[] stairsList;		// 계단 좌표, 길이
	static boolean[] isSelected;	// 1번 계단 선택 여부
	static int minTime;				// 최소 이동 시간
	
	// 두 좌표 사이의 거리
	public static int getDistance(Point point1, Point point2) {
		return Math.abs(point1.row - point2.row) + Math.abs(point1.col - point2.col);
	}
	
	// 각 계단에서 선택된 사람들을 모두 이동시키는데 걸리는 시간 계산
	public static int getTimeOfStairs(int current) {
		List<Integer> distToStairs = new ArrayList<>();	// 계단 이동을 시작하게 되는 시점(거리+1)
		List<Integer> stairs = new ArrayList<>();		// 계단을 이동 중인 사람이 이동을 시작한 시간
		
		// 1) 선택된 사람들의 각 계단까지의 거리 계산
		for (int idx = 0; idx < peopleList.size(); idx++) {
			// 1번 계단은 선택 처리 된 사람들만, 2번 계단은 선택 처리 되지 않은 사람들만
			if ((current == 1 && isSelected[idx]) || (current == 2 && !isSelected[idx])) {
				distToStairs.add(getDistance(stairsList[current].point, peopleList.get(idx)) + 1);
			}
		}
		
		// 해당 계단에 선택된 사람이 없는 경우, 종료
		if (distToStairs.size() == 0)
			return 0;
		
		// 2) 계단까지의 거리 리스트 오름차순 정렬
		Collections.sort(distToStairs);

		// 3) 시간을 늘려가며 사람들을 이동시킨다.
		int time = distToStairs.get(0);
		int done = 0;
		while (true) {
			// 3-1) 앞에서 최대 3번째까지 사람의 이동 칸을 1씩 감소한다.
			int moveIdx = (stairs.size() > 2) ? 3 : stairs.size();
			if (moveIdx > 0) {
				for (int idx = moveIdx-1; idx >= 0; idx--) {
					// 이동칸이 0이 되면, 이동 완료 처리
					if (stairs.get(idx) - 1 == 0) {
						stairs.remove(idx);
						done++;
					}
					else {
						stairs.set(idx, stairs.get(idx)-1);
					}
				}
			}

			// 3-2) 모두 이동을 완료하면 종료
			if (done == distToStairs.size()) {
				break;
			}

			// 3-3) 현재 시간과 계단에 도착하는 시간이 같은 사람들을 리스트에 넣는다.
			for (int idx = 0; idx < distToStairs.size(); idx++) {
				if (distToStairs.get(idx) == time) {
					stairs.add(stairsList[current].length);
				}
				else if (distToStairs.get(idx) > time) {
					idx--;
					break;
				}
			}
			time++;

		}
		return time--;
	}
	
	// 1번 계단을 선택하는 사람의 부분 집합
	public static void selectFirstStairs(int elementIdx) {
		
		// 4-1. 종료조건 : 모든 사람들을 탐색한 경우, 모든 사람의 이동 시간 계산 후 최소값 갱신
		if (elementIdx == peopleList.size()) {
			// 5. 모든 사람이 각자 선택한 계단을 통해 내려가 이동 완료하는 시간 계산
			// 5-1. 각 계단 별 선택된 사람들을 모두 이동시키는데 걸리는 시간을 계산한다.
			int timeOfStairs1 = getTimeOfStairs(1);
			int timeOfStairs2 = getTimeOfStairs(2);
			
			// 5-2. 둘 중 큰 값이 현재 부분 집합에 대한 이동 시간이 된다. (최솟값과 비교 후 갱신)
			int totalTime = Math.max(timeOfStairs1, timeOfStairs2);
			
			// 최솟값 갱신
			minTime = Math.min(minTime, totalTime);
			return;
		}
		
		// 4-2. 현재 원소를 1번 계단의 사람으로 선택하는 경우
		isSelected[elementIdx] = true;
		selectFirstStairs(elementIdx + 1);
		
		// 4-3. 현재 원소를 1번 계단의 사람으로 선택하지 않는 경우(2번 계단 이용)
		isSelected[elementIdx] = false;
		selectFirstStairs(elementIdx + 1);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		peopleList = new ArrayList<>();
		stairsList = new Stairs[3];
		
		// 1. 테스트케이스 개수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			minTime = Integer.MAX_VALUE;
			stairsIdx = 0;
			peopleList.clear();
			
			// 2. 방의 한 변의 길이가 입력된다.
			arraySize = Integer.parseInt(br.readLine().trim());
			array = new int[arraySize][arraySize];
			
			// 3. 지도 정보가 입력된다.
			for (int row = 0; row < arraySize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for (int col = 0; col < arraySize; col++) {
					array[row][col] = Integer.parseInt(st.nextToken());
					
					// 사람인 경우, 사람 리스트에 저장
					if (array[row][col] == PERSON) {
						peopleList.add(new Point(row, col));
					}
					// 계단 입구인 경우, 계단 리스트에 저장
					else if (array[row][col] >= MIN_STAIRS) {
						stairsList[++stairsIdx] = new Stairs(new Point(row, col), array[row][col]);
					}
				}
			}
			
			// 4. 1번 계단을 선택한 사람의 부분 집합 선택
			isSelected = new boolean[peopleList.size()];
			selectFirstStairs(0);
			
			sb.append(minTime).append("\n");
		}
		System.out.println(sb);
	}
}