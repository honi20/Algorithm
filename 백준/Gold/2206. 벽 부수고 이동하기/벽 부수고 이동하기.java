import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Point implements Comparable<Point> {
		int row, col;
		int dist;
		boolean breakWall;
		
		public Point(int row, int col, int dist, boolean breakWall) {
			this.row = row;
			this.col = col;
			this.dist = dist;
			this.breakWall = breakWall;
		}
		
		@Override
		public int compareTo(Point p) {
			return this.dist - p.dist;
		}
	}
	
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static final int EMPTY = 0;
	static final int WALL = 1;

	static int rowSize, colSize;
	static int[][] map;
	static int[][][] dijk;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 맵의 세로, 가로 크기가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 2. 맵의 정보를 입력받는다.
		map = new int[rowSize+1][colSize+1];
		dijk = new int[rowSize+1][colSize+1][2];
		for (int row = 1; row <= rowSize; row++) {
			String str = br.readLine().trim();
			
			for (int col = 1; col <= colSize; col++) {
				map[row][col] = str.charAt(col-1) - '0';
				dijk[row][col][0] = dijk[row][col][1] = Integer.MAX_VALUE;
			}
		}
		
		// 3. 최단 경로를 구한다.
		solve();
		
		int result = Math.min(dijk[rowSize][colSize][0], dijk[rowSize][colSize][1]);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	private static void solve() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.offer(new Point(1, 1, 1, false));
		dijk[1][1][0] = 1;
		
		while (!queue.isEmpty()) {
			Point point = queue.poll();
			int curRow = point.row;
			int curCol = point.col;
			int curDist = point.dist;
			boolean breakWall = point.breakWall;
			
			if (curRow == rowSize && curCol == colSize) {
				break;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nextRow = curRow + DELTA_ROW[dir];
				int nextCol = curCol + DELTA_COL[dir];
				
				if (nextRow <= 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize)
					continue;
				
				if (map[nextRow][nextCol] == EMPTY) {
					if (!breakWall && curDist + 1 < dijk[nextRow][nextCol][0]) {
						dijk[nextRow][nextCol][0] = curDist + 1;
						queue.offer(new Point(nextRow, nextCol, curDist+1, breakWall));
					}
					else if (breakWall && curDist + 1 < dijk[nextRow][nextCol][1]) {
						dijk[nextRow][nextCol][1] = curDist + 1;
						queue.offer(new Point(nextRow, nextCol, curDist+1, breakWall));
					}
				}
				
				else if (map[nextRow][nextCol] == WALL) {
					if (!breakWall && curDist + 1 < dijk[nextRow][nextCol][1]) {
						dijk[nextRow][nextCol][1] = curDist + 1;
						queue.offer(new Point(nextRow, nextCol, curDist+1, !breakWall));
					}
				}
			}
			
		}
	}
}
