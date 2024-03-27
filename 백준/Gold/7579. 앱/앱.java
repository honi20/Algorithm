/*
 * 1. 활성화 앱 개수와 필요한 메모리 크기가 입력된다.
 * 2. 활성화 앱의 메모리 크기가 입력된다.
 * 3. 활성화 앱의 비용이 입력된다.
 * 
 * 4. 0부터 최대 비용까지 탐색하면서, 해당 비용 안에 포함될 수 있는 현재 앱까지의 최대 메모리를 구한다.
 * 	4-1. 현재 앱의 비용이 해당 비용보다 크다면, 이전 앱까지의 메모리 그대로 가져옴
 * 	4-2. 현재 앱의 비용이 해당 비용보다 작거나 같다면, 이전 앱까지의 메모리와 현재 앱을 추가한 메모리 중 최대 메모리를 저장한다.
 * 5. 0부터 최대 비용까지 탐색하면서 필요한 메모리를 넘는 최소 비용을 구한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int preIdx = 0;	// 이전 인덱스
	static int nowIdx = 1;	// 현재 인덱스
	
	static int appCnt;		// 앱 개수
	static int requiredMemory;	// 필요한 메모리 크기
	static int[] memories;	// 앱 메모리 정보
	static int[] costs;		// 앱 비용 정보
	static int totalCost;	// 앱 총 비용
	
	static int[][] maxMemory;	// 각 비용 별 담을 수 있는 앱의 최대 메모리
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 활성화 앱 개수와 필요한 메모리 크기가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		appCnt = Integer.parseInt(st.nextToken());
		requiredMemory = Integer.parseInt(st.nextToken());
		
		// 2. 활성화 앱의 메모리 크기가 입력된다.
		memories = new int[appCnt + 1];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= appCnt; idx++) {
			memories[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 3. 활성화 앱의 비용이 입력된다.
		costs = new int[appCnt + 1];
		totalCost = 0;
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= appCnt; idx++) {
			costs[idx] = Integer.parseInt(st.nextToken());
			totalCost += costs[idx];
		}
		
		// 4. 0부터 최대 비용까지 탐색하면서, 해당 비용 안에 포함될 수 있는 현재 앱까지의 최대 메모리를 구한다.
		maxMemory = new int[2][totalCost + 1];
		for (int idx = 1; idx <= appCnt; idx++) {
			for (int cost = 0; cost <= totalCost; cost++) {
				// 4-1. 현재 앱의 비용이 해당 비용보다 크다면, 이전 앱까지의 메모리 그대로 가져옴
				if (costs[idx] > cost) {
					maxMemory[nowIdx][cost] = maxMemory[preIdx][cost];
				}
				// 4-2. 현재 앱의 비용이 해당 비용보다 작거나 같다면, 이전 앱까지의 메모리와 현재 앱을 추가한 메모리 중 최대 메모리를 저장한다.
				else {
					maxMemory[nowIdx][cost] = Math.max(maxMemory[preIdx][cost], maxMemory[preIdx][cost-costs[idx]] + memories[idx]);
				}
			}
			
			if (idx < appCnt) {
				preIdx = Math.abs(preIdx - 1);
				nowIdx = Math.abs(nowIdx - 1);
			}
		}
		
		// 5. 0부터 최대 비용까지 탐색하면서 필요한 메모리를 넘는 최소 비용을 구한다.
		int cost = 0;
		for (cost = 0; cost <= totalCost; cost++) {
			if (maxMemory[nowIdx][cost] >= requiredMemory) {
				break;
			}
		}
		
		System.out.println(cost);
	}
}
