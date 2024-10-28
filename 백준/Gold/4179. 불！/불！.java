import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Point {
		int row, col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public void updateRow(int row) {
			this.row = row;
		}
		
		public void updateCol(int col) {
			this.col = col;
		}
	}
	
	static class Node implements Comparable<Node> {
		Point point;
		int time;
		
		public Node(Point point, int time) {
			this.point = point;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.time - n.time;
		}
	}
	
	static final int INF = 123456789;
	
	static final char WALL = '#';
	static final char EMPTY = '.';
	static final char JIHUN = 'J';
	static final char FIRE = 'F';
	
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static int rowSize, colSize;
	static char[][] maze;
	static Point curPos;
	static int[][] fires;
	static int[][] dp;
	static int result;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		maze = new char[rowSize][colSize];
		fires = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			Arrays.fill(fires[row], Integer.MAX_VALUE);
		}
		
		for (int row = 0; row < rowSize; row++) {
			maze[row] = br.readLine().trim().toCharArray();
			
			for (int col = 0; col < colSize; col++) {
				if (maze[row][col] == JIHUN) {
					curPos = new Point(row, col);
				}
			}
		}
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (maze[row][col] == FIRE) {
					spreadFire(row, col);
				}
			}
		}

		solve();
		
		System.out.println(result == -1 ? "IMPOSSIBLE" : result);
	}
	
	private static void spreadFire(int row, int col) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(new Point(row, col), 0));
		fires[row][col] = 0;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int curRow = node.point.row;
			int curCol = node.point.col;
			int curTime = node.time;
			
			for (int dir = 0; dir < 4; dir++) {
				int nextRow = curRow + DELTA_ROW[dir];
				int nextCol = curCol + DELTA_COL[dir];
				
				if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
					continue;
				}
				
				if (maze[nextRow][nextCol] == WALL) {
					continue;
				}
				
				if (fires[nextRow][nextCol] > curTime + 1) {
					fires[nextRow][nextCol] = curTime + 1;
					queue.offer(new Node(new Point(nextRow, nextCol), fires[nextRow][nextCol]));
				}
			}
		}
		
	}
	
	private static void solve() {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		result = -1;
		dp = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				dp[row][col] = Integer.MAX_VALUE;
			}
		}
		
		queue.offer(new Node(curPos, 0));
		dp[curPos.row][curPos.col] = 0;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int curRow = node.point.row;
			int curCol = node.point.col;
			int curTime = node.time;
			
			if (curRow == 0 || curRow == rowSize-1 || curCol == 0 || curCol == colSize-1) {
				result = curTime + 1;
				break;
			}
			
			// 상하좌우로 탐색
			for (int dir = 0; dir < 4; dir++) {
				int nextRow = curRow + DELTA_ROW[dir];
				int nextCol = curCol + DELTA_COL[dir];
				
				if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
					continue;
				}
				
				if (maze[nextRow][nextCol] == WALL) {
					continue;
				}
	
				if (fires[nextRow][nextCol] > curTime + 1 && dp[nextRow][nextCol] > curTime + 1) {
					dp[nextRow][nextCol] = curTime + 1;
					queue.offer(new Node(new Point(nextRow, nextCol), dp[nextRow][nextCol]));
				}
			}
		}
		
		return;
	}
}