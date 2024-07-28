/**
 * 1. 행성 개수와 발사되는 행성의 위치가 입력된다.
 * 2. 각 행성 간 이동 시간이 입력된다.
 * 3. 각 행성 간 이동 시간의 최소를 구한다.
 * 4. 모든 행성을 탐사하는 최소 시간을 구한다.
 */
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int planetCnt;
	static int startPlanet;
	static int[][] travel;
	static boolean[] isVisited;
	static int result;
	
	public static void getMinTime(int planet, int cnt, int total) {
		if (cnt == planetCnt) {
			result = Math.min(result, total);
			return;
		}
		
		for (int idx = 0; idx < planetCnt; idx++) {
			isVisited[planet] = true;
			
			if (!isVisited[idx] && planet != idx) {
				getMinTime(idx, cnt+1, total + travel[planet][idx]);
			}
			
			isVisited[planet] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 행성 개수와 발사되는 행성의 위치가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		planetCnt = Integer.parseInt(st.nextToken());
		startPlanet = Integer.parseInt(st.nextToken());
		
		// 2. 각 행성 간 이동 시간이 입력된다.
		travel = new int[planetCnt][planetCnt];
		for (int from = 0; from < planetCnt; from++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for (int to = 0; to < planetCnt; to++) {
				travel[from][to] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 3. 각 행성 간 이동 시간의 최소를 구한다.
		for (int mid = 0; mid < planetCnt; mid++) {
			for (int from = 0; from < planetCnt; from++) {
				for (int to = 0; to < planetCnt; to++) {
					travel[from][to] = Math.min(travel[from][to], travel[from][mid] + travel[mid][to]);
				}
			}
		}
		
		// 4. 모든 행성을 탐사하는 최소 시간을 구한다.
		isVisited = new boolean[planetCnt];
		result = Integer.MAX_VALUE;
		getMinTime(startPlanet, 1, 0);
		
		System.out.println(result);
	}
}