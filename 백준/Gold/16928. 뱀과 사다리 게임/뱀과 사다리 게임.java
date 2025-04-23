import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Info {
		int from;
		int to;
		
		public Info(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	static final int DEST = 100;
	static final int LADDER = 1;
	static final int SNAKE = 2;
	
	static int N, M;
	static List<Info> ladders;
	static List<Info> snakes;
	static int[] map;
	static int[] minMap;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[DEST + 1];
		
		ladders = new ArrayList<>();
		for (int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			ladders.add(new Info(from, to));
			map[from] = LADDER;
		}
		
		snakes = new ArrayList<>();
		for (int idx = 0; idx < M; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			snakes.add(new Info(from, to));
			map[from] = SNAKE;
		}
		
		minMap = new int[DEST + 1];
		Arrays.fill(minMap, Integer.MAX_VALUE);
		
		minMap[1] = 0;
		solve(1, 0);
		
		System.out.println(minMap[DEST]);
	}
	
	private static void solve(int curPos, int curCnt) {
		if (curPos == DEST) {
			minMap[curPos] = Math.min(minMap[curPos], curCnt);
			return;
		}
		
		if (curCnt > minMap[curPos]) {
			return;
		}
		
		// 다음 칸으로 이동하는 경우
		for (int idx = 1; idx <= 6; idx++) {
			int next = curPos + idx;
			
			if (next > DEST) {
				break;
			}
			
			// 뱀이 있는 칸인 경우
			if (map[next] == SNAKE) {
				if (minMap[next] > curCnt + 1) {
					minMap[next] = curCnt + 1;
					
					for (Info snake : snakes) {
						if (snake.from == next) {
							next = snake.to;
							break;
						}
					}
					
					minMap[next] = curCnt + 1;
					solve(next, minMap[next]);
				}
			}
			
			// 사다리가 있는 칸인 경우
			else if (map[next] == LADDER) {
				if (minMap[next] > curCnt + 1) {
					minMap[next] = curCnt + 1;
					
					for (Info ladder : ladders) {
						if (ladder.from == next) {
							next = ladder.to;
							break;
						}
					}
					
					minMap[next] = curCnt + 1;
					solve(next, minMap[next]);
				}
			}
			
			else {
				if (minMap[next] > curCnt + 1) {
					minMap[next] = curCnt + 1;
					solve(next, minMap[next]);
				}
			}
		}
	}
}