/**
 * 1. 접시 개수, 초밥 가짓수, 연속 접시 수, 쿠폰 번호가 입력된다.
 * 2. 벨트의 회전 방향을 따라 초밥의 종류가 입력된다.
 * 3. 1번 접시부터 연속 접시 수 만큼 먹었을 때 먹을 수 있는 초밥 종류를 구한다.
 * 	3-1. 연속된 접시의 개수가 부족한 경우, endIdx만 증가
 * 		3-1-1. 현재 접시의 초밥 종류가 없는 경우에만 먹을 수 있는 종류 개수 증가
 * 		3-1-2. 해당 종류의 스시 개수, 먹은 접시 개수, endIdx 증가
 * 	3-2. 연속된 접시만큼 탐색한 경우, startIdx & endIdx 증가
 * 		3-2-1. 현재까지 먹은 접시의 초밥 종류와 최댓값과 비교
 * 		3-2-2. startIdx의 초밥 제외
 * 		3-2-3. endIdx 다음의 초밥 추가
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int totalDishCnt;		// 총 접시 개수
	static int sushiCnt;			// 초밥 종류
	static int continuousDishCnt;	// 연속 접시 수
	static int couponNum;			// 쿠폰 번호

	static int[] belt;		// 회전 벨트 정보
	static int[] sushi;		// 먹은 초밥 종류 개수 정보
	static int maxSushi;	// 최대 먹을 수 있는 초밥 종류
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 접시 개수, 초밥 가짓수, 연속 접시 수, 쿠폰 번호가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		totalDishCnt = Integer.parseInt(st.nextToken());
		sushiCnt = Integer.parseInt(st.nextToken());
		continuousDishCnt = Integer.parseInt(st.nextToken());
		couponNum = Integer.parseInt(st.nextToken());

		// 2. 벨트의 회전 방향을 따라 초밥의 종류가 입력된다.
		sushi = new int[sushiCnt + 1];
		belt = new int[totalDishCnt];
		for (int idx = 0; idx < totalDishCnt; idx++) {
			belt[idx] = Integer.parseInt(br.readLine().trim());
		}
		sushi[couponNum] = 1;

		int startIdx = 0;	// 연속 접시의 시작 인덱스
		int endIdx = 0;		// 연속 접시의 끝 인덱스
		int dishCnt = 0;	// 먹은 접시 개수
		int typeCnt = 1;	// 먹은 초밥 종류 개수
		
		// 3. 1번 접시부터 연속 접시 수 만큼 먹었을 때 먹을 수 있는 초밥 종류를 구한다.
		while (startIdx < totalDishCnt) {
			
			// 3-1. 연속된 접시의 개수가 부족한 경우, endIdx만 증가
			if (dishCnt < continuousDishCnt) {
				// 3-1-1. 현재 접시의 초밥 종류가 없는 경우에만 먹을 수 있는 종류 개수 증가
				if (sushi[belt[endIdx]]++ == 0)
					typeCnt++;

				// 3-1-2. 해당 종류의 스시 개수, 먹은 접시 개수, endIdx 증가
				dishCnt++;
				endIdx++;
				
				// 연속된 접시만큼 탐색한 경우, 마지막 인덱스 1 감소 
				if (dishCnt == continuousDishCnt) 
					endIdx--;
			}
			
			// 3-2. 연속된 접시만큼 탐색한 경우, startIdx & endIdx 증가
			else {
				// 3-2-1. 현재까지 먹은 접시의 초밥 종류와 최댓값과 비교
				maxSushi = Math.max(maxSushi, typeCnt);
				
				// 3-2-2. startIdx의 초밥 제외
				// 해당 초밥의 개수를 1 감소시킨 값이 0이라면 초밥 종류의 개수 1 감소
				if (--sushi[belt[startIdx]] == 0)
					typeCnt--;
				startIdx++;
				
				// 3-2-3. endIdx 다음의 초밥 추가
				endIdx = (endIdx + 1) % totalDishCnt;
				if (sushi[belt[endIdx]]++ == 0)
					typeCnt++;
			}
		}
		
		System.out.println(maxSushi);
	}
}