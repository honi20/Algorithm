/*
 * 1. 동굴의 크기가 입력된다.
 * 	- 0이 입력되면 테스트 종료
 * 2. 동굴의 각 칸에 있는 도둑 루피의 크기가 입력된다.
 * 3. 링크가 동굴을 지나면서 잃게 되는 최소 금액을 구한다.
 * 	3-1. 출발지부터 시작하여 각 칸 별 최소 손실 값을 구한다.
 * 	3-2. 목적지에 도착했으면, 반복문 break
 * 	3-3. 현재 칸이 이미 방문한 칸이면 패스
 * 	3-4. 인접 칸 탐색
 * 		3-4-1. 이동칸이 범위를 벗어나면 패스
 * 		3-4-2. 이동칸을 이미 방문했다면 패스
 * 		3-4-3. 이동칸에 저장된 최소 손실값보다 현재 칸에서 이동했을 때의 손실 값이 더 적은 경우, 갱신 후 큐에 저장
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 우선순위 큐에 넣을 값 (좌표, 손실값)
	static class Node implements Comparable<Node> {
		int row, col;
		int loss;
		
		public Node(int row, int col, int loss) {
			this.row = row;
			this.col = col;
			this.loss = loss;
		}

		@Override
		public int compareTo(Node o) {
			return this.loss - o.loss;
		}
	}
	
	// 이동 방향 (상/하/좌/우)
	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};
	
	static int testCase;	// 테스트케이스 번호
	static int caveSize;	// 동굴 크기
	static int[][] cave;	// 동굴 루피 크기 정보
	static int[][] minLoss;	// 해당 칸까지 이동했을 때의 최소 손실 값
	static boolean[][] isVisited;	// 칸 방문 여부
	
	// 초기화
	public static void init() {
		minLoss = new int[caveSize][caveSize];
		for (int row = 0; row < caveSize; row++) {
			for (int col = 0; col < caveSize; col++) {
				minLoss[row][col] = Integer.MAX_VALUE;
			}
		}
		minLoss[0][0] = cave[0][0];
		isVisited = new boolean[caveSize][caveSize];
	}
	
	public static void goToDestination() {
		init();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, minLoss[0][0]));
		
		// 3-1. 출발지부터 시작하여 각 칸 별 최소 손실 값을 구한다.
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int currentRow = node.row;
			int currentCol = node.col;
			int currentLoss = node.loss;
			
			// 3-2. 목적지에 도착했으면, 반복문 break
			if (currentRow == caveSize - 1 && currentCol == caveSize - 1) {
				break;
			}
			
			// 3-3. 현재 칸이 이미 방문한 칸이면 패스
			if (isVisited[currentRow][currentCol])
				continue;
			isVisited[currentRow][currentCol] = true;
			
			// 3-4. 인접 칸 탐색
			for (int dir = 0; dir < DELTA_CNT; dir++) {
				int nextRow = currentRow + DELTA_ROW[dir];
				int nextCol = currentCol + DELTA_COL[dir];
				
				// 3-4-1. 이동칸이 범위를 벗어나면 패스
				if (nextRow < 0 || nextCol < 0 || nextRow >= caveSize || nextCol >= caveSize)
					continue;
				
				// 3-4-2. 이동칸을 이미 방문했다면 패스
				if (isVisited[nextRow][nextCol])
					continue;
				
				// 3-4-3. 이동칸에 저장된 최소 손실값보다 현재 칸에서 이동했을 때의 손실 값이 더 적은 경우, 갱신 후 큐에 저장
				if (currentLoss + cave[nextRow][nextCol] < minLoss[nextRow][nextCol]) {
					minLoss[nextRow][nextCol] = currentLoss + cave[nextRow][nextCol];
					pq.offer(new Node(nextRow, nextCol, minLoss[nextRow][nextCol]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		testCase = 0;
		while (true) {			
			// 1. 동굴의 크기가 입력된다.
			caveSize = Integer.parseInt(br.readLine().trim());
			
			// 0이 입력되면 테스트 종료
			if (caveSize == 0)
				break;
			
			// 2. 동굴의 각 칸에 있는 도둑 루피의 크기가 입력된다.
			cave = new int[caveSize][caveSize];
			for (int row = 0; row < caveSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < caveSize; col++) {
					cave[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 3. 링크가 동굴을 지나면서 잃게 되는 최소 금액을 구한다.
			goToDestination();
			
			sb.append("Problem ").append(++testCase).append(": ");
			sb.append(minLoss[caveSize-1][caveSize-1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
