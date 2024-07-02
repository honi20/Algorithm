/**
 * 1. 선분 개수를 입력받는다.
 * 2. 선분의 구간을 입력받는다.
 * 	2-1. 해당 구간의 시작점, 끝점을 리스트에 저장한다.
 * 3. 최대로 많이 겹치는 선분들의 개수를 구한다.
 * 	3-1. 리스트를 좌표 기준으로 오름차순 정렬한다.
 * 	3-2. 리스트를 반복하며 해당 좌표가 시작점이면 +1, 끝점이면 -1한다.
 * 	3-3. 겹치는 선분의 최대 개수를 구한다.
 */
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int START = 1;
	static final int END = -1;
	
	static class Point implements Comparable<Point> {
		int value;
		int plus;
		
		public Point(int value, int plus) {
			this.value = value;
			this.plus = plus;
		}
		
		@Override
		public int compareTo(Point p) {
			if (this.value == p.value) {
				return this.plus - p.plus;
			}
			return this.value - p.value;
		}
	}
	
	static int lineCnt;
	static List<Point> point;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 선분 개수를 입력받는다.
		lineCnt = Integer.parseInt(br.readLine().trim());
		
		// 2. 선분의 구간을 입력받는다.
		point = new ArrayList<>();
		for (int idx = 0; idx < lineCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			// 2-1. 해당 구간의 시작점, 끝점을 리스트에 저장한다.
			point.add(new Point(start, START));
			point.add(new Point(end, END));
		}
		
		// 3. 최대로 많이 겹치는 선분들의 개수를 구한다.
		// 3-1. 리스트를 좌표 기준으로 오름차순 정렬한다.
		Collections.sort(point);
		
		// 3-2. 리스트를 반복하며 해당 좌표가 시작점이면 +1, 끝점이면 -1한다.
		int result = -1;
		int cnt = 0;
		for (int idx = 0; idx < point.size(); idx++) {
			cnt += point.get(idx).plus;
			result = Math.max(result, cnt);
		}
		
		// 3-3. 겹치는 선분의 최대 개수를 구한다.
		sb.append(result);
		System.out.println(sb);
	}
}