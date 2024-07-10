/**
 * 1. 테스트케이스가 입력된다.
 * 2. 미로의 세로, 가로 길이, 장애물 개수, 초기 힘, 출발지 좌표, 목적지 좌표가 입력된다.
 * 3. 장애물의 좌표와 높이 정보가 입력된다.
 * 4. 목적지까지 도착할 수 있는지 여부를 확인한다.
 * 	1) 목적지 좌표라면, true 반환하며 종료
 * 	2) 만약 남은 힘이 0 이하라면, 이동 불가
 * 	3) 상하좌우의 인접칸 중 이동 가능한 칸은 큐에 넣는다.
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
	
	static class Node {
		int row, col;
		int force;
		
		public Node(int row, int col, int force) {
			this.row = row;
			this.col = col;
			this.force = force;
		}
	}
	
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static int testCase;
	static int rowSize, colSize;	// 미로의 세로, 가로
	static int hurdleCnt, initForce;	// 장애물 개수, 초기 힘
	static Point start;		// 출발지 좌표
	static Point dest;		// 목적지 좌표
	static int[][] maze;	// 미로
	static boolean[][] isVisited;
	
	static boolean result;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 0; tc < testCase; tc++) {
			// 2. 미로의 세로, 가로 길이, 장애물 개수, 초기 힘, 출발지 좌표, 목적지 좌표가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			
			hurdleCnt = Integer.parseInt(st.nextToken());
			initForce = Integer.parseInt(st.nextToken());
			
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			start = new Point(startRow, startCol);
			
			int destRow = Integer.parseInt(st.nextToken());
			int destCol = Integer.parseInt(st.nextToken());
			dest = new Point(destRow, destCol);
			
			// 3. 장애물의 좌표와 높이 정보가 입력된다.
			init();
			for (int idx = 0; idx < hurdleCnt; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int height = Integer.parseInt(st.nextToken());
				
				maze[row][col] = height;
			}
			
			// 4. 목적지까지 도착할 수 있는지 여부를 확인한다.
			result = checkArrivalAtDestination();
			
			sb.append(result ? "잘했어!!" : "인성 문제있어??").append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean checkArrivalAtDestination() {
		// 4-1. 큐를 생성하고, 출발지 좌표를 큐에 넣는다.
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(start.row, start.col, initForce));
		isVisited[start.row][start.col] = true;
		
		// 4-2. 목적지에 도착하거나, 빈 큐가 될 때까지 반복한다.
		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int curRow = curNode.row;
			int curCol = curNode.col;
			int curForce = curNode.force;
			
			// 1) 목적지 좌표라면, true 반환하며 종료
			if (curRow == dest.row && curCol == dest.col) {
				return true;
			}
			
			// 2) 만약 남은 힘이 0 이하라면, 이동 불가
			if (curForce <= 0)
				continue;
			
			// 3) 상하좌우의 인접칸 중 이동 가능한 칸은 큐에 넣는다.
			for (int dir = 0; dir < 4; dir++) {
				int nextRow = curRow + DELTA_ROW[dir];
				int nextCol = curCol + DELTA_COL[dir];
				
				// 범위 벗어나면 패스
				if (nextRow <= 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize)
					continue;
				
				// 이미 방문했으면 패스
				if (isVisited[nextRow][nextCol])
					continue;
				
				// 현재 칸의 높이 > 이동 칸의 높이 --> 이동
				if (maze[curRow][curCol] > maze[nextRow][nextCol]) {
					isVisited[nextRow][nextCol] = true;
					queue.offer(new Node(nextRow, nextCol, curForce-1));
				}
				
				// (현재 칸의 높이 <= 이동 칸의 높이 --> (이동 칸의 높이 - 현재 칸의 높이)만큼의 점프힘 필요
				else {
					int diff = maze[nextRow][nextCol] - maze[curRow][curCol];
					
					if (diff <= curForce) {
						isVisited[nextRow][nextCol] = true;
						queue.offer(new Node(nextRow, nextCol, curForce-1));
					}
				}
			}
			
		}
		
		return false;
	}
	
	public static void init() {
		maze = new int[rowSize+1][colSize+1];
		isVisited = new boolean[rowSize+1][colSize+1];
		for (int row = 0; row <= rowSize; row++) {
			Arrays.fill(maze[row], 0);
			Arrays.fill(isVisited[row], false);
		}
	}
}