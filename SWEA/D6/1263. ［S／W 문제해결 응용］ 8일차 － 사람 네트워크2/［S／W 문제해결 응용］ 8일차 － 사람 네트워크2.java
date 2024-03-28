/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 사람의 수와 사람 네트워크의 인접 행렬이 입력된다.
 * 	2-1. 서로 다른 사람 쌍이 직접 연결이 되어있지 않다면, 무한대 값으로 초기화
 * 3. 사람 그래프에서 각 사람 쌍 간의 최단 거리를 구한다.
 * 	3-1. 1번 사람부터 마지막 사람까지 해당 사람을 경유로하는 각 사람 쌍을 탐색한다.
 * 	3-2. 이전 경유에서 저장된 사람 쌍의 거리를 기준으로, 두 사람이 현재 경유지를 경유하는 경우와 안하는 경우 중 최단 거리로 갱신한다.
 * 4. 다른 사람들과의 최단 거리의 합이 최소인 사람의 값을 출력한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int NO_DIRECT_CONNECT = 0;	// 직접 연결 안됨
	static final int INF = 1000;	// 최대 사용자 수 1,000 => 사람 쌍 간의 최대 거리 = 모든 사람을 거치는 경우 = 999
	
	static int testCase;	// 테스트 케이스 개수
	static int peopleCnt;	// 사람 수
	static int[][] people;	// 사람 인접 행렬 (people[i][j] : 사람 i에서 사람 j까지의 최단 거리(가까운 정도))
	static int minResult;	// 최소 결과값
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			minResult = Integer.MAX_VALUE;
			
			// 2. 사람의 수와 사람 네트워크의 인접 행렬이 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			peopleCnt = Integer.parseInt(st.nextToken());
			people = new int[peopleCnt + 1][peopleCnt + 1];
			
			for (int row = 1; row <= peopleCnt; row++) {
				for (int col = 1; col <= peopleCnt; col++) {
					people[row][col] = Integer.parseInt(st.nextToken());
					
					// 2-1. 서로 다른 사람 쌍이 직접 연결이 되어있지 않다면, 무한대 값으로 초기화
					if (row != col && people[row][col] == NO_DIRECT_CONNECT)
						people[row][col] = INF;
				}
			}
			
			// 3. 사람 그래프에서 각 사람 쌍 간의 최단 거리를 구한다.
			// 3-1. 1번 사람부터 마지막 사람까지 해당 사람을 경유로하는 각 사람 쌍을 탐색한다.
			for (int mid = 1; mid <= peopleCnt; mid++) {
				for (int start = 1; start <= peopleCnt; start++) {
					// 경유가 발생하지 않는 경우(시작 사람 = 경유 사람), 해당 경우 패스
					if (start == mid)
						continue;
					
					for (int end = 1; end <= peopleCnt; end++) {
						// 3-2. 이전 경유에서 저장된 사람 쌍의 거리를 기준으로, 두 사람이 현재 경유지를 경유하는 경우와 안하는 경우 중 최단 거리로 갱신한다.
						people[start][end] = Math.min(people[start][end], people[start][mid] + people[mid][end]);
					}
				}
			}
			
			// 4. 다른 사람들과의 최단 거리의 합이 최소인 사람의 값을 출력한다.
			for (int current = 1; current <= peopleCnt; current++) {
				int sum = 0;
				for (int other = 1; other <= peopleCnt; other++) {
					sum += people[current][other];
				}
				minResult = Math.min(minResult, sum);
			}
			
			sb.append("#").append(tc).append(" ").append(minResult).append("\n");
		}
		System.out.println(sb);
	}
}