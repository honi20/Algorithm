/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 강의 서쪽과 동쪽에 있는 사이트의 개수가 입력된다.
 * 3. 동쪽의 사이트 개수에서 서쪽의 사이트 개수만큼 구할 수 있는 조합의 경우의 수를 구한다.
 * 	3-1. 다리를 지을 사이트 개수보다 다리를 짓지 않을 사이트 개수가 더 적다면, 
 * 		 다리를 짓지 않을 사이트의 조합 개수를 구한다.
 * 	3-2. 0부터 조합 경우의 수를 저장한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int westSite, eastSite;	// 서쪽, 동쪽 사이트 개수
	static int[][] minCnt;
	
	public static int combination(int totalCnt, int selectCnt) {
		minCnt = new int[eastSite + 1][westSite + 1];
		
		// 3-1. 다리를 지을 사이트 개수보다 다리를 짓지 않을 사이트 개수가 더 적다면, 
		// 다리를 짓지 않을 사이트의 조합 개수를 구한다.
		if (selectCnt > (totalCnt / 2))
			selectCnt = totalCnt - selectCnt;
		
		// 3-2. 0부터 조합 경우의 수를 저장한다.
		for (int idx = 0; idx <= totalCnt; idx++) {
			for (int sIdx = 0; sIdx <= Math.min(idx, selectCnt); sIdx++) {
				if (sIdx == 0 || sIdx == idx)
					minCnt[idx][sIdx] = 1;
				else
					minCnt[idx][sIdx] = minCnt[idx-1][sIdx-1] + minCnt[idx-1][sIdx];
			}
		}

		return minCnt[totalCnt][selectCnt];
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 개수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 강의 서쪽과 동쪽에 있는 사이트의 개수가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			westSite = Integer.parseInt(st.nextToken());
			eastSite = Integer.parseInt(st.nextToken());
			
			// 3. 동쪽의 사이트 개수에서 서쪽의 사이트 개수만큼 구할 수 있는 조합의 경우의 수를 구한다.
			sb.append(combination(eastSite, westSite)).append("\n");
		}
		System.out.println(sb);
	}
}
