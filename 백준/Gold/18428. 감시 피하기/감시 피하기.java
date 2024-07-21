/**
 * 1. 복도 크기가 입력된다.
 * 2. 복도 정보가 입력된다.
 */
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
	}
	
	static final int EMPTY = 'X';
	static final int STUDENT = 'S';
	static final int TEACHER = 'T';
	static final int OBSTACLE = 'O';
	
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static int hallwaySize;
	static int[][] hallway;
	static List<Point> teacher;
	static boolean result;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 복도 크기가 입력된다.
		hallwaySize = Integer.parseInt(br.readLine().trim());
		
		// 2. 복도 정보가 입력된다.
		hallway = new int[hallwaySize][hallwaySize];
		teacher = new ArrayList<>();
		
		for (int row = 0; row < hallwaySize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for (int col = 0; col < hallwaySize; col++) {
				hallway[row][col] = st.nextToken().charAt(0);
				
				if (hallway[row][col] == TEACHER) {
					teacher.add(new Point(row, col));
				}
			}
		}
		
		// 3. 3개의 장애물을 놓는다.
		result = false;
		for (int row = 0; row < hallwaySize; row++) {
			for (int col = 0; col < hallwaySize; col++) {
				// 만약 빈 칸이 아니라면 패스
				if (hallway[row][col] != EMPTY)
					continue;
				
				if (result)
					break;
				
				hallway[row][col] = OBSTACLE;
				placeObstacle(row, col, 1);
				hallway[row][col] = EMPTY;
			}
		}
		
		System.out.println(result ? "YES" : "NO");
	}
	
	public static void placeObstacle(int curRow, int curCol, int cnt) {
		// 만약 3개의 장애물을 모두 놓은 경우
		if (cnt == 3) {
			
			if (isAllSurvived()) {
				result = true;
//				printArray();
			}
			return;
		}
		
		if (result) return;
		
		
		for (int row = curRow; row < hallwaySize; row++) {
			for (int col = 0; col < hallwaySize; col++) {
				if (row == curRow && col <= curCol)
					continue;
				
				if (hallway[row][col] != EMPTY)
					continue;
				
				hallway[row][col] = OBSTACLE;
				placeObstacle(row, col, cnt + 1);
				hallway[row][col] = EMPTY;
			}
		}
	}
	
	public static boolean isAllSurvived() {
		for (int idx = 0; idx < teacher.size(); idx++) {
			if (meetStudent(teacher.get(idx))) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean meetStudent(Point point) {
		int curRow = point.row;
		int curCol = point.col;
		
		for (int dir = 0; dir < 4; dir++) {
			
			int idx = 1;
			while (true) {
				int nextRow = curRow + idx * DELTA_ROW[dir];
				int nextCol = curCol + idx * DELTA_COL[dir];
				
				if (nextRow < 0 || nextCol < 0 || nextRow >= hallwaySize || nextCol >= hallwaySize)
					break;
				
				if (hallway[nextRow][nextCol] == OBSTACLE)
					break;
				
				if (hallway[nextRow][nextCol] == STUDENT)
					return true;
				
				++idx;
			}
		}
		return false;
	}
	
	public static void printArray() {
		for (int row = 0; row < hallwaySize; row++) {
			for (int col = 0; col < hallwaySize; col++) {
				System.out.print((char)hallway[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
