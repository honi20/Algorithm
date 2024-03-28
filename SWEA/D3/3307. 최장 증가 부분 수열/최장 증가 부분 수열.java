/*
 * 1. 테스트케이스 수를 입력받는다.
 * 2. 수열의 길이를 입력받는다.
 * 3. 수열의 값을 입력받는다.
 * 4. 최장 부분 증가 수열의 길이를 구한다.
 * 	1) DP 방법
 * 		1-1) LIS를 저장할 1차원 dp 배열을 만든다. (수열만큼의 크기)
 * 		1-2) 각 인덱스에는 해당 값을 마지막으로 하는 부분 증가 수열의 최대 길이를 저장한다.
 * 			- '현재 값보다 작은 이전 인덱스의 부분 증가 수열 값 + 1' 중 최대값
 * 		1-3) dp에 저장된 값 중 최대값 = 최장 부분 증가 수열의 길이
 * 
 * 	2) 이진 탐색 방법
 * 		2-1) 길이를 인덱스로 하는 1차원 bs 리스트를 만든다.
 * 			- 해당 길이의 부분 증가 수열에서 마지막으로 올 수 있는 최소 수열값 저장
 * 		2-2) 수열을 탐색하면서 bs 리스트 중 현재 수열 값보다 큰 값을 찾고 해당 인덱스에 현재 수열값 저장
 * 			2-2-1) 이진 탐색의 반환값이 양수라면, 이미 현재 값과 같은 값이 존재하므로 패스
 * 			2-2-2) 반환값이 음수라면, 현재 값보다 처음으로 큰 값이 위치하는 인덱스에 현재 값으로 바꾸어 저장
 * 
 * 		[이분탐색]
 * 		- "Collections.binarySearch()" 함수 사용
 * 		- 매개변수 : 탐색할 리스트, 찾을 키 값
 * 		- 찾고자 하는 key 값이 리스트에 존재한다면, 해당 값의 인덱스 반환
 * 		- 찾고자 하는 key 값이 리스트에 존재하지 않는다면, (-(insertion point) - 1) 반환
 * 		- 즉, 반환값이 양수면 리스트에 해당 값 존재 / 음수면 리스트에 해당 값 존재하지 않음
 * 		- insertion point (= (-1)*(반환값+1))
 * 			- 리스트에서 key 값보다 큰 값 중 첫 번째로 오는 인덱스 값
 * 			- 만약 모든 리스트가 key 값보다 작다면, 리스트 사이즈 값
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
	
	static int testCase;	// 테스트케이스 크기
	static int arraySize;	// 수열 크기
	static int[] array;		// 수열의 값
	
	static int[] dp;		// 각 인덱스를 마지막으로 하는 부분 증가 수열의 최대 길이 저장
	static List<Integer> bs;	// 각 인덱스(길이)만큼의 부분 증가 수열에서 마지막에 위치할 수 있는 값의 최소 저장
	static int result;		// 최장 부분 증가 수열
	
	// 방법1. DP
	public static int getLISByDP() {
		int maxLIS = 0;
		
		// 1-1) LIS를 저장할 1차원 dp 배열을 만든다. (수열만큼의 크기)
		dp = new int[arraySize+1];
		
		// 1-2) 각 인덱스에는 해당 값을 마지막으로 하는 부분 증가 수열의 최대 길이를 저장한다.
		for (int idx = 1; idx <= arraySize; idx++) {
			dp[idx] = 1;
			
			// '현재 값보다 작은 이전 인덱스의 부분 증가 수열 값 + 1' 중 최대값
			for (int pre = 1; pre < idx; pre++) {
				if (array[pre] < array[idx]) {
					dp[idx] = Math.max(dp[idx], dp[pre] + 1);
				}
			}
			
			// 1-3) dp에 저장된 값 중 최대값 = 최장 부분 증가 수열의 길이
			maxLIS = Math.max(maxLIS, dp[idx]);
		}
		return maxLIS;
	}
	
	// 방법2. 이진 탐색
	public static int getLISByBinarySearch() {
		int maxLIS = 0;
		
		// 2-1) 길이를 인덱스로 하는 1차원 bs 리스트를 만든다.
		bs = new ArrayList<>();
		bs.add(0);		// 0번째 인덱스 값 저장
		
		// 2-2) 수열을 탐색하면서 bs 리스트 중 현재 수열 값보다 큰 값을 찾고 해당 인덱스에 현재 수열값 저장
		for (int idx = 1; idx <= arraySize; idx++) {
			
			int returnIdx = Collections.binarySearch(bs, array[idx]);
			
			// 2-2-1) 이진 탐색의 반환값이 양수라면, 이미 현재 값과 같은 값이 존재하므로 패스
			if (returnIdx >= 0)
				continue;
			
			// 2-2-2) 반환값이 음수라면, 현재 값보다 처음으로 큰 값이 위치하는 인덱스에 현재 값으로 바꾸어 저장
			// 반환값 = (-(맨 처음으로 큰 값의 인덱스 + 1))
			// => 맨 처음으로 큰 값의 인덱스 = -(반환값 + 1)
			else {
				int insertIdx = (-1)*(returnIdx + 1);
				
				// 만약 맨 처음으로 큰 값의 인덱스가 리스트 크기와 같아면, 리스트의 모든 값이 작은 것이기 때문에 리스트에 추가
				if (insertIdx == bs.size())
					bs.add(array[idx]);
				
				// 아니라면 리스트의 해당 인덱스 값을 현재 수열값으로 바꿈
				else
					bs.set(insertIdx, array[idx]);
				
				maxLIS = Math.max(maxLIS, insertIdx);
			}
		}
		
		return maxLIS;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 2. 수열의 길이를 입력받는다.
			arraySize = Integer.parseInt(br.readLine().trim());
			
			// 3. 수열의 값을 입력받는다.
			array = new int[arraySize+1];
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 1; idx <= arraySize; idx++) {
				array[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 4. 최장 부분 증가 수열의 길이를 구한다.
			// 1) DP 방법
			// result = getLISByDP();
			
			// 2) 이진 탐색 방법
			result = getLISByBinarySearch();
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
