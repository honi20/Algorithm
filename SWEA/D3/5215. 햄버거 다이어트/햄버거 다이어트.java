/**
 * 1. 테스트 케이스 개수가 입력된다.
 * 2. 재료의 수와 제한 칼로리가 입력된다.
 * 3. 각 재료 별 맛 점수와 칼로리가 입력된다.
 * 4. 재료의 조합 탐색
 * 	4-1. 종료조건 1. 선택을 모두 한 경우, 재료의 칼로리 및 점수 계산 후 종료 
 * 	4-2. 종료조건 2. 모든 재료를 탐색한 경우, 종료
 * 	4-3. 현재 재료를 햄버거의 재료로 선택한 경우
 * 	4-4. 현재 재료를 햄버거의 재료로 선택하지 않는 경우
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int SCORE_IDX = 0;
	static final int CALORY_IDX = 1;
	
	static int ingredientCount;		// 재료 개수
	static int limitCalory;			// 제한 칼로리
	static int[][] ingredients;		// 재료 맛 점수
	static int[] calory;			// 재료 칼로리
	static int maximumScore;		// 최대 맛 점수
	static int[] selectList;		// 선택한 재료 저장
	
	// 선택한 재료들의 칼로리 또는 점수의 합 반환
	public static int getTotalSum(int typeIdx) {
		int totalSum = 0;
		for (int idx = 0; idx < selectList.length; idx++) {
			totalSum += ingredients[selectList[idx]][typeIdx];
		}
		return totalSum;
	}
	
	// 조합
	public static void combination(int selectIdx, int elementIdx, int selectSize) {
		// 4-1. 종료조건 1. 선택을 모두 한 경우, 재료의 칼로리 및 점수 계산 후 종료 
		if (selectIdx == selectSize) {
			int totalCalory = getTotalSum(CALORY_IDX);
			
			if (totalCalory <= limitCalory) {
				int totalScore = getTotalSum(SCORE_IDX);
				maximumScore = Math.max(maximumScore, totalScore);
			}
			return;
		}
		
		// 4-2. 종료조건 2. 모든 재료를 탐색한 경우, 종료
		if (elementIdx == ingredientCount)
			return;
		
		// 4-3. 현재 재료를 햄버거의 재료로 선택한 경우
		selectList[selectIdx] = elementIdx;
		combination(selectIdx + 1, elementIdx + 1, selectSize);
		
		// 4-4. 현재 재료를 햄버거의 재료로 선택하지 않는 경우
		combination(selectIdx, elementIdx + 1, selectSize);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트 케이스 개수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			maximumScore = 0;
			
			// 2. 재료의 수와 제한 칼로리가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			ingredientCount = Integer.parseInt(st.nextToken());
			limitCalory = Integer.parseInt(st.nextToken());
			
			// 배열 크기 지정
			ingredients = new int[ingredientCount][2];
			
			// 3. 각 재료 별 맛 점수와 칼로리가 입력된다.
			for (int idx = 0; idx < ingredientCount; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				ingredients[idx][SCORE_IDX] = Integer.parseInt(st.nextToken());
				ingredients[idx][CALORY_IDX] = Integer.parseInt(st.nextToken());
			}
			
			// 4. 재료의 조합 탐색
			for (int selectSize = 1; selectSize <= ingredientCount; selectSize++) {
				selectList = new int[selectSize];
				combination(0, 0, selectSize);
			}
			
			// 출력
			sb.append("#").append(tc).append(" ").append(maximumScore).append("\n");
		}
		System.out.println(sb);
	}
}
