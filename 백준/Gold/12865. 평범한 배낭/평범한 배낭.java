/*
 * Boj 12865. 평범한 배낭
 * 
 * 1. 물건의 수와 준서가 버틸 수 있는 무게가 입력된다.
 * 2. 물건의 최대 가치값을 저장하기 위한 배열을 초기화한다.
 * 	- 2행의 2차원 배열 (이전 물건까지의 가치, 현재 물건까지의 가치 저장)
 * 3. 각 물건의 무게와 가치가 입력된다.
 * 4. 1부터 가능한 무게(W)까지 모두 탐색하면서 현재 물건(i)까지의 최대 가치를 구한다.
 * 	4-1. 해당 무게에 현재 물건이 들어갈 수 없는 경우, 이전 물건의 가치(i-1, w)를 그대로 가져온다.
 * 	4-2. 해당 무게에 현재 물건이 들어올 수 있는 경우, 현재 물건을 넣었을 때의 가치와 넣지 않을 때의 가치 중 최대값을 가져온다.
 * 5. 현재 물건과 이전 물건의 인덱스를 바꾼다(토글)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int preObj = 0;	// 이전 물건의 인덱스
	static int nowObj = 1;	// 현재 물건의 인덱스
	
	static int objCount;		// 물건의 개수
	static int limitWeight;		// 배낭에 넣을 수 있는 최대 무게
	static int[][] maxValue;	// 현재 물건까지에 대해 넣을 수 있는 가치의 최대 저장
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 물건의 수와 준서가 버틸 수 있는 무게가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		objCount = Integer.parseInt(st.nextToken());
		limitWeight = Integer.parseInt(st.nextToken());
		
		// 2. 물건의 최대 가치값을 저장하기 위한 배열을 초기화한다.
		// 2행의 2차원 배열 (이전 물건까지의 가치, 현재 물건까지의 가치 저장)
		maxValue = new int[2][limitWeight + 1];
		
		// 3. 각 물건의 무게와 가치가 입력된다.
		for (int obj = 1; obj <= objCount; obj++) {
			st = new StringTokenizer(br.readLine().trim());
			int nowWeight = Integer.parseInt(st.nextToken());
			int nowValue = Integer.parseInt(st.nextToken());
			
			// 4. 1부터 가능한 무게(W)까지 모두 탐색하면서 현재 물건(i)까지의 최대 가치를 구한다.
			for (int weight = 1; weight <= limitWeight; weight++) {
				// 4-1. 해당 무게에 현재 물건이 들어갈 수 없는 경우, 이전 물건의 가치(i-1, w)를 그대로 가져온다.
				if (nowWeight > weight) {
					maxValue[nowObj][weight] = maxValue[preObj][weight];
				}
				// 4-2. 해당 무게에 현재 물건이 들어올 수 있는 경우, 현재 물건을 넣었을 때의 가치와 넣지 않을 때의 가치 중 최대값을 가져온다.
				else {
					maxValue[nowObj][weight] = Math.max(maxValue[preObj][weight], maxValue[preObj][weight-nowWeight] + nowValue);
				}
			}
			
			// 5. 현재 물건과 이전 물건의 인덱스를 바꾼다(토글)
			if (obj < objCount) {					
				nowObj = Math.abs(nowObj - 1);
				preObj = Math.abs(preObj - 1);
			}
		}
		sb.append(maxValue[nowObj][limitWeight]);
		System.out.println(sb);
	}
}