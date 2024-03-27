/**
 * 1. 테스트 케이스의 개수가 입력된다.
 * 2. 과자의 개수와 무게 제한값이 입력된다.
 * 3. 각 과자의 무게가 입력된다.
 * 4. 과자 2개를 고르는 조합을 구한다.
 * 	종료 조건 1. 2개를 모두 선택한 경우, 선택한 과자 무게 계산 후 종료
 * 	종료 조건 2. 과자 원소를 모두 탐색한 경우, 종료
 * 	5-1. 현재 과자를 선택한 경우
 * 	5-2. 현재 과자를 선택하지 않는 경우
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int SELECT_COUNT = 2;
	
	static int testCase;		// 테스트케이스 개수
	static int snackCount;		// 과자 개수
	static int limitWeight;		// 제한 무게
	static int[] snackWeight;	// 과자 무게
	static int[] selectList;	// 선택한 과자 저장
	static int maximumWeight;	// 조건을 만족하는 최대 무게(결과)
	
	// 선택한 과자 무게 계산
	public static int calculateWeight() {
		int totalWeight = 0;
		for (int idx = 0; idx < SELECT_COUNT; idx++) {
			totalWeight += snackWeight[selectList[idx]];
		}
		return totalWeight;
	}
	
	public static void combination(int selectIdx, int elementIdx) {
		// 종료 조건 1. 2개를 모두 선택한 경우, 선택한 과자 무게 계산 후 종료
		if (selectIdx == SELECT_COUNT) {
			int currentWeight = calculateWeight();
			
			// 제한 무게를 초과하지 않는 경우, 결괏값 갱신
			if (currentWeight <= limitWeight) {
				maximumWeight = Math.max(maximumWeight, currentWeight);
			}
			return;
		}
		
		// 종료 조건 2. 과자 원소를 모두 탐색한 경우, 종료
		if (elementIdx == snackCount)
			return;
		
		// 5-1. 현재 과자를 선택한 경우
		selectList[selectIdx] = elementIdx;
		combination(selectIdx + 1, elementIdx + 1);
		
		// 5-2. 현재 과자를 선택하지 않는 경우
		combination(selectIdx, elementIdx + 1);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		selectList = new int[SELECT_COUNT];
		
		// 1. 테스트 케이스의 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			maximumWeight = -1;
			
			// 2. 과자의 개수와 무게 제한값이 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			snackCount = Integer.parseInt(st.nextToken());
			limitWeight = Integer.parseInt(st.nextToken());
			snackWeight = new int[snackCount];
			
			// 3. 각 과자의 무게가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < snackCount; idx++) {
				snackWeight[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 4. 과자 2개를 고르는 조합을 구한다.
			combination(0, 0);
			
			sb.append("#").append(tc).append(" ").append(maximumWeight).append("\n");
		}
		System.out.println(sb);
	}

}