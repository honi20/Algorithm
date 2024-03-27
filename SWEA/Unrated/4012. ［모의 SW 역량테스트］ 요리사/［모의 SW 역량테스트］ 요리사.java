/**
 * 1. 테스트 케이스의 개수가 입력된다.
 * 2. 식재료의 개수가 입력된다.
 * 3. 식재료 끼리의 시너지 값이 입력된다.
 * 4. A 음식을 만들 수 있는 식재료의 조합 탐색(나머지는 자동으로 B 음식)
 * 	4-1. 종료 조건 1. 식재료를 모두 선택한 경우,
 * 					음식 간의 맛의 차이 계산 및 최솟값 갱신
 * 	4-2. 종료 조건 2. 식재료를 모두 탐색한 경우
 * 	4-3. 현재 식재료를 A 음식으로 선택하는 경우
 * 	4-4. 현재 식재료를 A 음식으로 선택하지 않는 경우
 * 5. 음식 간의 맛 시너지의 차이 계산
 * 	5-1. 음식 A의 시너지 합 (조합으로 선택한 재료들의 시너지 합)
 * 	5-2. 나머지 음식(음식 B)의 시너지 합
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int ingredientCount;		// 식재료 개수
	static int[][] synergy;			// 식재료 간 시너지 
	
	static boolean[] isSelected;		// 식재료 개수의 절반의 크기만큼을 조합하여 저장하는 배열
	static int minimumDiff;
	
	// 5. 음식 간의 맛 시너지의 차이 계산
	public static int getFoodDifference() {
		int synergySumA = 0, synergySumB = 0;
		
		// 5-1. 음식 A의 시너지 합 (조합으로 선택한 재료들의 시너지 합)
		for (int ingredient1 = 1; ingredient1 < ingredientCount; ingredient1++) {
			if (isSelected[ingredient1]) {
				for (int ingredient2 = ingredient1-1; ingredient2 >= 0; ingredient2--) {
					if (isSelected[ingredient2]) {
						synergySumA += synergy[ingredient1][ingredient2];
						synergySumA += synergy[ingredient2][ingredient1];
					}
				}
			}
		}
		
		// 5-2. 나머지 음식(음식 B)의 시너지 합
		for (int ingredient1 = 1; ingredient1 < ingredientCount; ingredient1++) {
			if (!isSelected[ingredient1]) {
				for (int ingredient2 = ingredient1-1; ingredient2 >= 0; ingredient2--) {
					if (!isSelected[ingredient2]) {
						synergySumB += synergy[ingredient1][ingredient2];
						synergySumB += synergy[ingredient2][ingredient1];
					}
				}
			}
		}
		return Math.abs(synergySumA - synergySumB);
	}
	
	// 조합 함수
	public static void combination(int selectIdx, int elementIdx) {
		// 4-1. 종료 조건 1. 식재료를 모두 선택한 경우
		if (selectIdx == ingredientCount / 2) {
			// 음식 간의 맛의 차이 계산 및 최솟값 갱신
			int currentDiff = getFoodDifference();
			minimumDiff = Math.min(minimumDiff, currentDiff);
			return;
		}
		
		// 4-2. 종료 조건 2. 식재료를 모두 탐색한 경우
		if (elementIdx == ingredientCount) {
			return;
		}
		
		// 4-3. 현재 식재료를 A 음식으로 선택하는 경우
		isSelected[elementIdx] = true;
		combination(selectIdx + 1, elementIdx + 1);
		
		// 4-4. 현재 식재료를 A 음식으로 선택하지 않는 경우
		isSelected[elementIdx] = false;
		combination(selectIdx, elementIdx + 1);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			minimumDiff = Integer.MAX_VALUE;
			
			// 2. 식재료의 개수가 입력된다.
			ingredientCount = Integer.parseInt(br.readLine().trim());

			// 시너지 저장 배열 크기 지정
			synergy = new int[ingredientCount][ingredientCount];
			isSelected = new boolean[ingredientCount];
			
			// 3. 식재료 끼리의 시너지 값이 입력된다.
			for (int row = 0; row < ingredientCount; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < ingredientCount; col++) {
					synergy[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 4. A 음식을 만들 수 있는 식재료의 조합 탐색(나머지는 자동으로 B 음식)
			combination(0, 0);
			
			sb.append("#").append(tc).append(" ").append(minimumDiff).append("\n");
		}
		System.out.println(sb);
	}
}
