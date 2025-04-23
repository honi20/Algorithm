import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Road implements Comparable<Road> {
		int start;
		int end;
		int len;
		
		public Road(int start, int end, int len) {
			this.start = start;
			this.end = end;
			this.len = len;
		}
		
		@Override
		public int compareTo(Road r) {
			if (this.start == r.start) {
				if (this.end == r.end) {
					return this.len - r.len;
				}
				
				return this.end - r.end;
			}
			
			return this.start - r.start;
		}
	}
	
	static int N, D;
	static List<Road> roads;
	static int[] minLen;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		roads = new ArrayList<>();
		for (int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			roads.add(new Road(start, end, len));
		}
		
		Collections.sort(roads);
		
		minLen = new int[D + 1];
		Arrays.fill(minLen, Integer.MAX_VALUE);
		
		solve(0, 0);
		
		System.out.println(minLen[D]);
	}
	
	private static void solve(int curPos, int curLen) {
		if (curPos == D) {
			minLen[curPos] = Math.min(minLen[curPos], curLen);
			return;
		}
		
		if (curLen > minLen[curPos]) {
			return;
		}
		
		// 다음 지름길의 시작까지 가서 지름길 타기
		for (Road road : roads) {
			if (curPos > road.start) {
				continue;
			}
			
			if (D < road.end) {
				continue;
			}
			
			if (minLen[road.end] > curLen + (road.start - curPos) + road.len) {
				minLen[road.end] = curLen + (road.start - curPos) + road.len;
				solve(road.end, minLen[road.end]);
			}
		}
		
		// 목적지로 바로 이동
		solve(D, curLen + (D - curPos));
	}
}