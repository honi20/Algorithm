/**
 * 1. 집의 수가 입력된다.
 * 2. 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 입력된다.
 * 3. 모든 집을 칠하는 비용의 최솟값을 출력한다.
 * 	3-1. 첫 번째 집부터 마지막 집까지 반복한다.
 * 	3-2. 이전 집의 색과 다른 색으로 칠할 때의 최소 비용을 저장한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int RGB = 3;	// 색 개수
	
	static int houseCnt;	// 집 수
	static int[][] weight;	// 각 집 별 색 칠하는 비용
	static int[][] minWeight;	// 첫 번째 집부터 각 집까지 색을 칠하는 최소 비용
	
	public static void init() {
		for (int idx = 1; idx <= houseCnt; idx++)
			Arrays.fill(minWeight[idx], Integer.MAX_VALUE);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 집의 수가 입력된다.
		houseCnt = Integer.parseInt(br.readLine().trim());
		weight = new int[houseCnt + 1][3];
		minWeight = new int[houseCnt + 1][3];
		
		// 2. 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 입력된다.
		for (int idx = 1; idx <= houseCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int color = 0; color < RGB; color++) 
				weight[idx][color] = Integer.parseInt(st.nextToken());
		}
		
		// 3. 모든 집을 칠하는 비용의 최솟값을 출력한다.
		init();
		// 3-1. 첫 번째 집부터 마지막 집까지 반복한다.
		for (int idx = 1; idx <= houseCnt; idx++) {
			// 3-2. 이전 집의 색과 다른 색으로 칠할 때의 최소 비용을 저장한다.
			for (int nowColor = 0; nowColor < RGB; nowColor++) {
				for (int preColor = 0; preColor < RGB; preColor++) {
					// 같은 색은 패스
					if (nowColor == preColor)
						continue;
					
					// 현재 집의 색과 이전 집의 색이 다를 때, 색칠 비용의 최솟값 갱신
					minWeight[idx][nowColor] = 
							Math.min(minWeight[idx][nowColor], minWeight[idx-1][preColor] + weight[idx][nowColor]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int color = 0; color < RGB; color++)
			result = Math.min(result, minWeight[houseCnt][color]);
		
		System.out.println(result);
	}
}