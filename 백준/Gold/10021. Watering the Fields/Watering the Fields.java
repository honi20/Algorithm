import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Vertex {
		int x, y;
		
		public Vertex(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 간선
	static class Edge implements Comparable<Edge> {
		int vertex1, vertex2;
		int weight;
		
		public Edge(int vertex1, int vertex2, int weight) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int vertexCnt, minimumWeight;
	static List<Vertex> vertex;
	static List<Edge> edgeList;
	static int[] parentList;
	static int[] rankList;
	
	public static boolean union(int element1, int element2) {
		int e1Root = find(element1);
		int e2Root = find(element2);
		
		// 이미 같은 집합
		if (e1Root == e2Root)
			return false;
		
		// e1 아래에 e2 합치기
		if (rankList[e1Root] > rankList[e2Root]) {
			parentList[e2Root] = e1Root;
			return true;
		}
		
		// e2 아래에 e1 합치기
		parentList[e1Root] = e2Root;
		
		// 두 집합의 랭크가 같다면 랭크 크기 1 증가
		if (rankList[e1Root] == rankList[e2Root])
			++rankList[e2Root];
		
		return true;
	}
	
	public static int find(int element) {
		// 자신이 조상 노드라면
		if (parentList[element] == element)
			return element;
		
		return parentList[element] = find(parentList[element]);
	}
	
	public static void make() {
		parentList = new int[vertexCnt + 1];
		rankList = new int[vertexCnt + 1];
		
		for (int idx = 0; idx < vertexCnt; idx++) {
			parentList[idx] = idx;
			rankList[idx] = 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 정점 개수와 최소 간선 가중치 값이 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		vertexCnt = Integer.parseInt(st.nextToken());
		minimumWeight = Integer.parseInt(st.nextToken());
		
		// 2. 정점의 좌표를 입력받는다.
		vertex = new ArrayList<>();
		for (int idx = 0; idx < vertexCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			vertex.add(new Vertex(x, y));
		}
		
		// 3. 각 정점의 거리 값을 계산하여 간선 정보를 구한다.
		edgeList = new ArrayList<>();
		for (int vertex1 = 0; vertex1 < vertex.size()-1; vertex1++) {
			for (int vertex2 = vertex1+1; vertex2 < vertex.size(); vertex2++) {
				int dist = getDist(vertex1, vertex2);
				
				if (dist >= minimumWeight)
					edgeList.add(new Edge(vertex1, vertex2, dist));
			}
		}
		
		// 4. 간선 가중치를 오름차순 정렬
		Collections.sort(edgeList);
		
		// 5. 집합 초기화
		make();
		
		// 6. kruskal 알고리즘을 통해 최소 스패닝 트리의 가중치를 구한다.
		long mstWeight = 0;
		int mstCnt = 0;
		for (Edge edge : edgeList) {
			// 6-1. 해당 간선이 사이클을 만들어내는 경우, 패스
			if (!union(edge.vertex1, edge.vertex2))
				continue;
			
			// 6-2. 사이클을 만들지 않으면, 가중치 합 증가
			mstWeight += (long) edge.weight;
			
			// 6-3. (정점-1)개의 간선을 모두 선택했다면, 종료
			if (++mstCnt == vertexCnt - 1)
				break;
		}
		
		if (mstCnt == vertexCnt - 1)
			System.out.println(mstWeight);
		else
			System.out.println(-1);
	}

	private static int getDist(int vertex1, int vertex2) {
		return (vertex.get(vertex1).x - vertex.get(vertex2).x)*(vertex.get(vertex1).x - vertex.get(vertex2).x)
				+ (vertex.get(vertex1).y - vertex.get(vertex2).y)*(vertex.get(vertex1).y - vertex.get(vertex2).y);
	}
}
