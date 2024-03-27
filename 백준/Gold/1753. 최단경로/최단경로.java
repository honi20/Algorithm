/**
 * 1. 정점의 개수와 간선의 개수가 입력된다.
 * 2. 시작 정점의 번호가 입력된다.
 * 3. 각 간선 정보가 입력된다.
 * 	- 두 정점 번호, 가중치
 * 
 * 4. 시작점으로부터 각 정점으로 가는 최단 경로의 경로 값을 구한다.
 * 	4-1. 큐를 통해 반복하여 탐색
 * 	4-2. 큐에서 나온 정점이 이미 방문한 정점이라면, 패스
 * 	4-3. 큐에서 나온 정점이 선택되지 않은 정점이면, 방문 처리
 * 	4-4. 모든 정점을 다 방문했다면 종료
 * 	4-5. 다음으로 이동할 인접 정점들을 탐색한다.
 * 		- 아직 방문하지 않았고 현재까지의 경로에서 이동한 경로값이 원래 저장된 경로값보다 작다면,
 * 		=> 경로값 갱신 후 큐에 넣기
 * 5. 시작점으로부터 각 정점까지의 최단 경로 값을 출력한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 인접 리스트의 노드
	static class Node {
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + ", next=" + next + "]";
		}
	}
	
	// 큐에 넣을 정점 정보
	static class Vertex implements Comparable<Vertex> {
		int num;		// 정점 번호
		int distance;	// 시작점에서 해당 정점까지의 경로
		
		public Vertex(int num, int distance) {
			super();
			this.num = num;
			this.distance = distance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.distance - o.distance;
		}
	}
	
	static int vertexCnt, edgeCnt;	// 정점, 간선 개수
	static int startVertex;			// 시작 정점
	static Node[] adjList;			// 인접 리스트
	
	static boolean[] isVisited;		// 방문 여부
	static int[] minDistance;		// 출발점으로부터 해당 정점까지의 최단 경로값
	
	
	public static void getMinPath() {
		// 초기화
		minDistance = new int[vertexCnt + 1];
		isVisited = new boolean[vertexCnt + 1];
		
		// 우선순위 큐 - 경로가 작은 순으로
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		int visitedCnt = 0;		// 방문한 정점 개수
		
		// 시작 정점 설정
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		minDistance[startVertex] = 0;
		queue.offer(new Vertex(startVertex, minDistance[startVertex]));
		
		// 4-1. 큐를 통해 반복하여 탐색
		while (!queue.isEmpty()) {
			Vertex vertex = queue.poll();
			int currentNum = vertex.num;
			int currentDistance = vertex.distance;
			
			// 4-2. 큐에서 나온 정점이 이미 방문한 정점이라면, 패스
			if (isVisited[currentNum])
				continue;
			
			// 4-3. 큐에서 나온 정점이 선택되지 않은 정점이면, 방문 처리
			isVisited[currentNum] = true;
			
			// 4-4. 모든 정점을 다 방문했다면 종료
			if (visitedCnt++ == vertexCnt)
				break;
			
			// 4-5. 다음으로 이동할 인접 정점들을 탐색한다.
			for (Node to = adjList[currentNum]; to != null; to = to.next) {
				// 아직 방문하지 않았고 현재까지의 경로에서 이동한 경로값이 원래 저장된 경로값보다 작다면,
				if (!isVisited[to.vertex] && ((currentDistance + to.weight) < minDistance[to.vertex])) {
					// 경로값 갱신 후 큐에 넣기
					minDistance[to.vertex] = currentDistance + to.weight;
					queue.offer(new Vertex(to.vertex, minDistance[to.vertex]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 정점의 개수와 간선의 개수가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		vertexCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		
		// 2. 시작 정점의 번호가 입력된다.
		startVertex = Integer.parseInt(br.readLine().trim());
		
		// 3. 각 간선 정보가 입력된다.
		adjList = new Node[vertexCnt + 1];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 인접 리스트 저장
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		// 4. 시작점으로부터 각 정점으로 가는 최단 경로의 경로 값을 구한다.
		getMinPath();
		
		// 5. 시작점으로부터 각 정점까지의 최단 경로 값을 출력한다.
		for (int idx = 1; idx <= vertexCnt; idx++)
			sb.append((minDistance[idx] == Integer.MAX_VALUE) ? "INF" : minDistance[idx]).append("\n");
		System.out.println(sb);
	}
}