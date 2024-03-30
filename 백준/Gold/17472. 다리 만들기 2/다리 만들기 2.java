/**
 * 1. 배열의 세로, 가로 크기가 입력된다.
 * 2. 배열 정보가 입력된다.
 * 3. 섬끼리 그룹을 만든다.
 * 	3-1. 현재 칸이 바다가 아니고 방문하지 않은 칸이라면, 연결된 칸(섬) 탐색
 * 		3-1-1. 현재 칸에 방문처리 & 섬(그룹) 번호 저장
 * 		3-1-2. 상하좌우 인접칸 탐색
 * 		3-1-3. 인접칸이 범위를 벗어나면, 패스
 * 		3-1-4. 인접칸이 바다라면, 패스
 * 		3-1-5. 인접칸이 이미 방문한 칸이라면, 패스
 * 
 * 4. 놓을 수 있는 다리의 후보를 탐색한다.
 * 	4-1. 가로, 세로 각각에 놓을 수 있는 다리 후보를 찾는다.
 * 	4-2. 이전 칸이 섬이고 현재 칸이 바다라면, 다리 건설의 가능성 존재
 * 	4-3. 바다칸일 때까지 길이를 늘려가며 건설 가능한 다리의 길이를 구한다.
 * 	4-4. 만약 가능한 다리 길이가 1이라면, 건설 불가능
 * 	4-5. 만약 다리의 끝이 격자의 끝이라면, 건설 불가능
 * 	4-6. 만약 다리의 양 끝 섬이 서로 같은 섬이라면, 건설 불가능
 * 	4-7. 후보에 저장
 * 	
 * 5. 길이가 짧은 다리부터 건설한다.
 * 	5-1. 길이를 기준으로 오름차순 정렬
 * 	5-2. 다리 후보 리스트를 반복하며 다리를 건설한다.
 * 		5-2-1. 섬들이 이미 모두 연결되어 있다면, 반복 종료
 * 		5-2-2. 만약 두 섬이 이미 연결된 상태라면, 패스
 * 		5-2-3. 다리를 건설하고, 두 섬을 연결시킨다.
 * 			- 섬2의 그룹 번호를 가진 섬들을 모두 섬1의 그룹 번호로 바꾼다.
 * 	5-3. 다리 리스트를 모두 탐색했지만 섬들이 모두 연결되어 있지 않다면, -1 저장
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 놓을 수 있는 다리 객체
	static class Bridge implements Comparable<Bridge> {
		int island1, island2;	// 다리로 연결되는 섬 번호
		int length;		// 다리 길이
		
		public Bridge(int island1, int island2, int length) {
			super();
			this.island1 = island1;
			this.island2 = island2;
			this.length = length;
		}

		@Override
		public String toString() {
			return "Bridge [island1=" + island1 + ", island2=" + island2 + ", length=" + length + "]";
		}

		@Override
		public int compareTo(Bridge o) {
			return this.length - o.length;
		}
		
	}
	
	static final int SEA = 0;
	
	// 상하좌우
	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static int rowSize, colSize;	// 세로, 가로 크기
	static int[][] map;				// 격자 정보
	
	static int groupIdx;			// 섬(그룹) 개수
	static int[] group;				// 연결된 섬 그룹의 번호
	static boolean[][] isVisited;	// 방문 여부
	
	static List<Bridge> bridges;	// 놓을 수 있는 다리 리스트
	static int totalLength = 0;		// 결과값
	
	public static void dfs(int row, int col, int groupIdx) {
		// 3-1-1. 현재 칸에 방문처리 & 섬(그룹) 번호 저장
		isVisited[row][col] = true;
		map[row][col] = groupIdx;
		
		// 3-1-2. 상하좌우 인접칸 탐색
		for (int dir = 0; dir < DELTA_CNT; dir++) {
			int nextRow = row + DELTA_ROW[dir];
			int nextCol = col + DELTA_COL[dir];
			
			// 3-1-3. 인접칸이 범위를 벗어나면, 패스
			if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize)
				continue;
			
			// 3-1-4. 인접칸이 바다라면, 패스
			if (map[nextRow][nextCol] == SEA)
				continue;
			
			// 3-1-5. 인접칸이 이미 방문한 칸이라면, 패스
			if (isVisited[nextRow][nextCol])
				continue;
			
			dfs(nextRow, nextCol, groupIdx);
		}
		return;
	}
	
	// 섬끼리 그룹 번호를 지정하는 함수
	public static void makeGroup() {
		groupIdx = 0;
		isVisited = new boolean[rowSize][colSize];
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				// 3-1. 현재 칸이 바다가 아니고 방문하지 않은 칸이라면, 연결된 칸(섬) 탐색
				if (map[row][col] != SEA && !isVisited[row][col]) {
					dfs(row, col, ++groupIdx);
				}
			}
		}
		
		group = new int[groupIdx + 1];
		for (int idx = 0; idx < groupIdx + 1; idx++)
			group[idx] = idx;
	}
	
	// 놓을 수 있는 다리 후보를 찾는 함수
	public static void findBridges() {
		// 4-1. 가로, 세로 각각에 놓을 수 있는 다리 후보를 찾는다.
		// 가로
		for (int row = 0; row < rowSize; row++) {
			for (int col = 1; col < colSize; col++) {
				// 4-2. 이전 칸이 섬이고 현재 칸이 바다라면, 다리 건설의 가능성 존재
				if (map[row][col-1] != SEA && map[row][col] == SEA) {
					int currentCol = col;
					int length = 0;
					
					// 4-3. 바다칸일 때까지 길이를 늘려가며 건설 가능한 다리의 길이를 구한다.
					while (currentCol < colSize && map[row][currentCol] == SEA) {
						++length; ++currentCol;
					}
					
					// 4-4. 만약 가능한 다리 길이가 1이라면, 건설 불가능
					if (length == 1)
						continue;
					
					// 4-5. 만약 다리의 끝이 격자의 끝이라면, 건설 불가능
					if (currentCol == colSize)
						continue;
					
					// 4-6. 만약 다리의 양 끝 섬이 서로 같은 섬이라면, 건설 불가능
					if (map[row][col-1] == map[row][col+length])
						continue;
					
					// 4-7. 후보에 저장
					bridges.add(new Bridge(map[row][col-1], map[row][col+length], length));
					col = currentCol;
				}
			}
		}
		
		// 세로
		for (int col = 0; col < colSize; col++) {
			for (int row = 1; row < rowSize; row++) {
				// 4-2. 이전 칸이 섬이고 현재 칸이 바다라면, 다리 건설의 가능성 존재
				if (map[row-1][col] != SEA && map[row][col] == SEA) {
					
					int currentRow = row;
					int length = 0;
					
					// 4-3. 바다칸일 때까지 길이를 늘려가며 건설 가능한 다리의 길이를 구한다.
					while (currentRow < rowSize && map[currentRow][col] == SEA) {
						++length; ++currentRow;
					}
					// 4-4. 만약 가능한 다리 길이가 1이라면, 건설 불가능
					if (length == 1)
						continue;

					// 4-5. 만약 다리의 끝이 격자의 끝이라면, 건설 불가능
					if (currentRow == rowSize)
						continue;

					// 4-6. 만약 다리의 양 끝 섬이 서로 같은 섬이라면, 건설 불가능
					if (map[row-1][col] == map[row+length][col])
						continue;

					// 4-7. 후보에 저장
					bridges.add(new Bridge(map[row-1][col], map[row+length][col], length));
					row = currentRow;
				}
			}
		}
	}
	
	// 섬들이 모두 연결되어 있는지 확인 (섬들의 그룹 번호가 모두 같은지 확인)
	public static boolean isAllConnected() {
		for (int idx = 1; idx <= groupIdx; idx++) {
			if (group[idx] != group[1])
				return false;
		}
		return true;
	}
	
	// 섬을 연결해주는 함수. from 그룹의 섬 번호를 모두 to 그룹 번호로 바꿔준다.
	public static void connectIslands(int from, int to) {
		for (int idx = 1; idx <= groupIdx; idx++) {
			if (group[idx] == from)
				group[idx] = to;
		}
	}
	
	public static void buildBridges() {
		// 5-1. 길이를 기준으로 오름차순 정렬
		Collections.sort(bridges);
		
		// 5-2. 다리 후보 리스트를 반복하며 다리를 건설한다.
		for (Bridge bridge : bridges) {
			// 5-2-1. 섬들이 이미 모두 연결되어 있다면, 반복 종료
			if (isAllConnected())
				break;
			
			int island1 = bridge.island1;
			int island2 = bridge.island2;
			int length = bridge.length;
			
			// 5-2-2. 만약 두 섬이 이미 연결된 상태라면, 패스
			if (group[island1] == group[island2])
				continue;
			
			// 5-2-3. 다리를 건설하고, 두 섬을 연결시킨다.
			totalLength += length;
			connectIslands(group[island2], group[island1]);
		}
		
		// 5-3. 다리 리스트를 모두 탐색했지만 섬들이 모두 연결되어 있지 않다면, -1 저장
		if (!isAllConnected())
			totalLength = -1;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		bridges = new ArrayList<>();

		// 1. 배열의 세로, 가로 크기가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 2. 배열 정보가 입력된다.
		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 3. 섬끼리 그룹을 만든다.
		makeGroup();
		
		// 4. 놓을 수 있는 다리의 후보를 탐색한다.
		findBridges();
		
		// 5. 길이가 짧은 다리부터 건설한다.
		buildBridges();
		
		System.out.println(totalLength);
	}
}
