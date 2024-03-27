/**
 * 1. 덤프 횟수를 입력받는다.
 * 2. 각 상자의 높이를 입력받는다.
 * 3. 덤프 횟수만큼 상자 덤프 수행
 * 	3-1. 상자 높이 배열 정렬
 * 	3-2. 기저 조건
 * 		1) 덤프 횟수만큼의 덤프를 진행한 경우, 종료
 * 		2) 평탄화가 완료된 경우, 종료 (최고점과 최저점의 차이가 1)
 * 	3-3. 전 처리 조건
 * 		- 첫 인덱스 배열 값에는 +1, 마지막 인덱스 배열 값에는 -1
 * 	3-4. 재귀 호출
 * 		- 덤프 횟수 1회 감소한 후 함수 호출 
 * 4. 최고점과 최저점의 높이 차 출력 (배열의 첫 번째 값과 마지막 값의 차)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int TESTCASE = 10;
	static final int LENGTH = 100;	// 가로 길이 항상 100
	static int dumpCount;			// 덤프 횟수
	static int[] boxHeightArray = new int[100];	// 상자의 높이 배열
	
	public static void dumpBox(int dumpCount) {
		// 3-1. 상자 높이 배열 정렬
		Arrays.sort(boxHeightArray);
		
		// 3-2. 기저 조건
		// 1) 덤프 횟수만큼의 덤프를 진행한 경우, 종료
		if (dumpCount == 0)
			return;
		
		// 2) 평탄화가 완료된 경우, 종료 (최고점과 최저점의 차이가 1)
		if (boxHeightArray[LENGTH-1] - boxHeightArray[0] <= 1) {
			return;
		}
		
		// 3-3. 전 처리 조건
		// 첫 인덱스 배열 값에는 +1, 마지막 인덱스 배열 값에는 -1
		boxHeightArray[0]++;
		boxHeightArray[LENGTH-1]--;
		
		// 3-4. 재귀 호출
		// 덤프 횟수 1회 감소한 후 함수 호출 
		dumpBox(dumpCount-1);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int tc = 0; tc < TESTCASE; tc++) {
			// 1. 덤프 횟수를 입력받는다.
			dumpCount = Integer.parseInt(br.readLine().trim());
			
			// 2. 각 상자의 높이를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < LENGTH; idx++) {
				boxHeightArray[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 3. 덤프 횟수만큼 상자 덤프 수행
			dumpBox(dumpCount);
			
			// 4. 최고점과 최저점의 높이 차 출력 (배열의 첫 번째 값과 마지막 값의 차)
			sb.setLength(0);
			sb.append("#").append(tc+1).append(" ").append(boxHeightArray[LENGTH-1] - boxHeightArray[0]);
			System.out.println(sb);
		}
	}
}
