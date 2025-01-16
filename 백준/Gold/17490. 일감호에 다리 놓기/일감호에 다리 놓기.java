import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int buildingCnt;
	static int sectionCnt;
	static long stoneCnt;
	
	static int[] stones;
	static boolean[] isConnected;	// idx -> idx+1 길목 공사
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		buildingCnt = Integer.parseInt(st.nextToken());
		sectionCnt = Integer.parseInt(st.nextToken());
		stoneCnt = Long.parseLong(st.nextToken());
		
		stones = new int[buildingCnt+1];
		isConnected = new boolean[buildingCnt+1];
		Arrays.fill(isConnected, true);
		
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= buildingCnt; idx++) {
			stones[idx] = Integer.parseInt(st.nextToken());
		}
		
		for (int idx = 0; idx < sectionCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int building1 = Integer.parseInt(st.nextToken());
			int building2 = Integer.parseInt(st.nextToken());
			
			if (Math.min(building1, building2) == 1 && Math.max(building1, building2) == buildingCnt) {
				isConnected[Math.max(building1, building2)] = false;
			}
			else {
				isConnected[Math.min(building1, building2)] = false;
			}
		}
		
		// 모두 연결되어 있다면 패스
		if (sectionCnt <= 1) {
			System.out.println("YES");
			return;
		}
		
		int minStone = stones[1];
		List<Integer> result = new ArrayList<>();
		
		for (int idx = 2; idx <= buildingCnt; idx++) {
			// 이전 것과 연결되어 있는 경우
			if (isConnected[idx-1]) {
				minStone = Math.min(minStone, stones[idx]);
			}
			// 이전 것과 연결되어 있지 않는 경우
			else {
				result.add(minStone);
				minStone = stones[idx];
			}
		}
		
		// 마지막 돌이 첫 번째 돌과 연결되어 있는 경우
		if (isConnected[buildingCnt]) {
			if (result.size() > 0) {
				int firstVal = result.get(0);
				result.remove(0);
				result.add(Math.min(minStone, firstVal));
			}
		}
		// 마지막 돌이 첫 번째 돌과 연결되어 있지 않는 경우
		else {
			if (result.size() > 0) {
				result.add(minStone);
			}
		}
		
		long sum = 0;
		for (int idx = 0; idx < result.size(); idx++) {
			sum += result.get(idx);
			
			if (sum > stoneCnt) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}
}