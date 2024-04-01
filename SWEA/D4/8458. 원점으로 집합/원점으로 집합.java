/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 격자점의 개수가 입력된다.
 * 3. 각 격자점의 좌표가 입력된다.
 * 4. 남은 거리 중 가장 큰 값을 기준으로 몇 번의 이동을 해야 0 이하의 값이 나오는지 파악한다.
 * 5. 모든 격자점의 남은 거리에서 4번에서 구한 이동 횟수의 누적 합을 뺀다.
 * 	- 뺀 결과는 모두 0 이하이다.
 * 	- 뺀 결과의 절댓값을 2로 나눈 나머지에 음수 값으로 저장한다.
 * 	- 2번의 이동으로 제자리에 올 수 있기 때문에 모든 값들을 0 또는 -1 중에서만 저장되도록 한다.
 * 6. 결과를 출력한다.
 * 	6-1. 모든 격자점의 남은 거리 값이 동일하지 않다면, 모든 점을 원점으로 이동할 수 없다.
 * 	6-2. 모든 격자점의 남은 거리 값이 0이라면, 모든 점이 원점이다.
 * 	6-3. 모든 격자점의 남은 거리 값이 -1이라면,
 * 		1) 지금까지의 이동 횟수가 짝수인 경우, +1한 이동 횟수에 모든 접이 원점으로 이동한다.
 * 		2) 지금까지의 이동 횟수가 홀수인 경우, +2한 이동 횟수에 모든 접이 원점으로 이동한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int testCase;	// 테스트케이스 개수
	static int pointCnt;	// 격자점 개수
	static int[] distance;	// 각 격자점의 남은 거리 저장
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 격자점의 개수가 입력된다.
			pointCnt = Integer.parseInt(br.readLine().trim());
			
			// 3. 각 격자점의 좌표가 입력된다.
			distance = new int[pointCnt];
			for (int idx = 0; idx < pointCnt; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				distance[idx] = Math.abs(row) + Math.abs(col);
			}
			Arrays.sort(distance);
			
			// 4. 남은 거리 중 가장 큰 값을 기준으로 몇 번의 이동을 해야 0 이하의 값이 나오는지 파악한다.
			int cnt = 0;
			int sum = 0;
			int maxValue = distance[pointCnt - 1];
			while (maxValue > 0) {
				maxValue -= (++cnt);
				sum += cnt;
			}
			
			boolean isAllZero = true;	// 남은 거리가 모두 0
			boolean isAllMinus1 = true;	// 남은 거리가 모두 -1
			
			// 5. 모든 격자점의 남은 거리에서 4번에서 구한 이동 횟수의 누적 합을 뺀다.
			for (int idx = 0; idx < pointCnt; idx++) {
				// 뺀 결과는 모두 0 이하이다.
				// 뺀 결과의 절댓값을 2로 나눈 나머지에 음수 값으로 저장한다.
				// 2번의 이동으로 제자리에 올 수 있기 때문에 모든 값들을 0 또는 -1 중에서만 저장되도록 한다.
				distance[idx] = (-1)*(Math.abs(distance[idx] - sum) % 2);
				if (distance[idx] == 0)
					isAllMinus1 = false;
				else if (distance[idx] == -1)
					isAllZero = false;
			}
			
			// 6. 결과를 출력한다.
			int result = -1;
			// 6-1. 모든 격자점의 남은 거리 값이 동일하지 않다면, 모든 점을 원점으로 이동할 수 없다.
			if (!isAllZero && !isAllMinus1)
				result = -1;
			// 6-2. 모든 격자점의 남은 거리 값이 0이라면, 모든 점이 원점이다.
			else if (isAllZero)
				result = cnt;
			// 6-3. 모든 격자점의 남은 거리 값이 -1이라면,
			else if (isAllMinus1) {
				// 1) 지금까지의 이동 횟수가 짝수인 경우, +1한 이동 횟수에 모든 접이 원점으로 이동한다.
				if (cnt % 2 == 0)
					result = cnt + 1;
				// 2) 지금까지의 이동 횟수가 홀수인 경우, +2한 이동 횟수에 모든 접이 원점으로 이동한다.
				else
					result = cnt + 2;
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}