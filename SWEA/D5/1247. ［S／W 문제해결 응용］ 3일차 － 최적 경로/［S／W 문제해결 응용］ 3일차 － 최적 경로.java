/**
 * 1. 테스트케이스 수가 입력된다.
 * 2. 고객의 수가 입력된다.
 * 3. 회사의 좌표, 집의 좌표, 각 고객의 좌표가 입력된다.
 * 4. 고객의 순열을 탐색한다.
 * 	4-1. 종료조건 : 선택을 모두 한 경우, 해당 순서로의 전체 이동 거리 계산 및 결괏값 갱신 후 종료
 * 		1) 회사에서 첫 번째 고객으로 가는 이동 경로 계산
 * 		2) 첫번째 고객부터 마지막 고객까지 각각의 이동 경로 계산
 * 		3) 마지막 고객에서 집으로 가는 이동 경로 계산
 * 	4-2. 다음 원소를 이미 선택한 경우, 패스
 * 	4-3. 전처리 로직 : 이동할 칸을 선택 리스트에 추가 & 선택 처리
 * 	4-4. 재귀 호출
 * 	4-5. 후처리 로직 : 선택 처리 취소
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int customerCnt;			// 고객 수
	static Point company, home;		// 회사, 집 좌표
	static Point[] customers;		// 고객들 좌표
	static Point[] selectList;		// 고객 순열 좌표값 저장
	static boolean[] isSelected;	// 순열 선택 여부 저장
	static int minDist;				// 최소 이동 거리 (결과)
	
	// 두 좌표 사이의 거리 계산
	public static int calculateDist(Point from, Point to) {
		return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
	}
	
	// 순열 순서로 이동했을 때의 전체 이동 거리 반환
	public static int getTotalDist() {
		int totalDist = 0;
		// 1) 회사에서 첫 번째 고객으로 가는 이동 경로 계산
		totalDist += calculateDist(company, selectList[0]);
		
		// 2) 첫번째 고객부터 마지막 고객까지 각각의 이동 경로 계산
		for (int customer = 0; customer < customerCnt-1; customer++) {
			totalDist += calculateDist(selectList[customer], selectList[customer + 1]);
		}
		
		// 3) 마지막 고객에서 집으로 가는 이동 경로 계산
		totalDist += calculateDist(selectList[customerCnt - 1], home);
		
		return totalDist;
	}
	
	public static void permutation(int selectIdx) {
		// 4-1. 종료조건 : 선택을 모두 한 경우, 해당 순서로의 전체 이동 거리 계산 및 결괏값 갱신 후 종료
		if (selectIdx == customerCnt) {
			minDist = Math.min(minDist, getTotalDist());
			return;
		}
		
		for (int idx = 0; idx < customerCnt; idx++) {
			// 4-2. 다음 원소를 이미 선택한 경우, 패스
			if (isSelected[idx])
				continue;
			
			// 4-3. 전처리 로직 : 이동할 칸을 선택 리스트에 추가 & 선택 처리
			selectList[selectIdx] = customers[idx];
			isSelected[idx] = true;
			
			// 4-4. 재귀 호출
			permutation(selectIdx + 1);
			
			// 4-5. 후처리 로직 : 선택 처리 취소
			selectList[selectIdx] = null;
			isSelected[idx] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트케이스 수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			minDist = Integer.MAX_VALUE;
			sb.append("#").append(tc).append(" ");
			
			// 2. 고객의 수가 입력된다.
			customerCnt = Integer.parseInt(br.readLine().trim());
			customers = new Point[customerCnt];
			selectList = new Point[customerCnt];
			isSelected = new boolean[customerCnt];
			
			// 3. 회사의 좌표, 집의 좌표, 각 고객의 좌표가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			for (int idx = 0; idx < customerCnt; idx++) {
				customers[idx] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// 4. 고객의 순열을 탐색한다.
			permutation(0);
			
			sb.append(minDist).append("\n");
		}
		System.out.println(sb);
	}
}