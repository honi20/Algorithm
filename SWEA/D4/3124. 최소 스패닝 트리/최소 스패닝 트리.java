/**
 * 1. 테스트케이스 수가 입력된다.
 * 2. 정점과 간선의 개수가 입력된다.
 * 3. 각 간선 정보가 입력된다.
 * 4. 간선 가중치를 오름차순으로 정렬
 * 5. 집합 초기화
 * 6. kruskal 알고리즘을 통해 최소 스패닝 트리의 가중치를 구한다.
 * 	6-1. 해당 간선이 사이클을 만들어내는 경우, 패스
 * 	6-2. 사이클을 만들지 않으면, 가중치 합 증가
 * 	6-3. (정점-1)개의 간선을 모두 선택했다면, 종료
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 간선
	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int vertexCnt, edgeCnt;	// 정점, 간선 개수
	static Edge[] edgeList;		// 간선 정보
	static int[] parentList;	// 부모 정점 정보
	static int[] rankList;		// 랭크 정보
	
	public static boolean union(int element1, int element2) {
		int e1Root = find(element1);
		int e2Root = find(element2);
		
		// 이미 같은 집합이라면
		if (e1Root == e2Root)
			return false;
		
		// e1 아래에 e2 합치기
		if (rankList[e1Root] > rankList[e2Root]) {
			parentList[e2Root] = e1Root;
			return true;
		}
		
		// e2 아래에 e1 합치기
		parentList[e1Root] = e2Root;
		
		// 두 집합의  랭크 같다면 랭크 크기 1 증가
		if (rankList[e1Root] == rankList[e2Root])
			rankList[e2Root]++;
		
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
		
		for (int idx = 0; idx < vertexCnt + 1; idx++) {
			parentList[idx] = idx;
			rankList[idx] = 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트케이스 수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 2. 정점과 간선의 개수가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			vertexCnt = Integer.parseInt(st.nextToken());
			edgeCnt = Integer.parseInt(st.nextToken());
			
			// 3. 각 간선 정보가 입력된다.
			edgeList = new Edge[edgeCnt];
			for (int idx = 0; idx < edgeCnt; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList[idx] = new Edge(from, to, weight);
			}
			
			// 4. 간선 가중치를 오름차순으로 정렬
			Arrays.sort(edgeList);
			
			// 5. 집합 초기화
			make();
			
			// 6. kruskal 알고리즘을 통해 최소 스패닝 트리의 가중치를 구한다.
			long mstWeight = 0;
			int mstCnt = 0;
			for (Edge edge : edgeList) {
				// 6-1. 해당 간선이 사이클을 만들어내는 경우, 패스
				if (!union(edge.from, edge.to))
					continue;
				
				// 6-2. 사이클을 만들지 않으면, 가중치 합 증가
				mstWeight += (long) edge.weight;
				
				// 6-3. (정점-1)개의 간선을 모두 선택했다면, 종료
				if (++mstCnt == vertexCnt - 1)
					break;
			}
			sb.append(mstWeight).append("\n");
		}
		System.out.println(sb);
	}
}