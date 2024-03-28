/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 섬의 개수가 입력된다.
 * 3. 각 섬들의 x좌표들, y좌표들이 입력된다.
 * 4. 환경 부담 세율 실수가 입력된다.
 * 
 * 5. 트리의 각 원소 집합을 생성한다.
 * 6. 각 섬 간의 간선 리스트를 생성하고, 환경 부담금(가중치) 값이 오름차순이 되도록 정렬한다.
 * 	- 환경 부담금 = 환경 부담 세율 * (해저 터널의 길이 ^ 2)
 * 7. kruskal 알고리즘을 통해 총 환경 부담금(가중치)을 최소로 하는 최소 신장 트리를 구한다.
 * 	7-1. 사이클을 만드는 간선의 경우, 패스
 * 	7-2. 사이클을 만들지 않는다면, 해당 간선을 해저터널로 선택(가중치 추가)
 * 	7-3. 모든 섬을 선택했다면, 종료
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
	static class Edge implements Comparable<Edge>{
		int from, to;
		double weight;
		
		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
	static final int X = 0;
	static final int Y = 1;
	
	static int islandCnt;		// 섬 개수
	static int[][] islandPosition;	// 섬 좌표
	static double taxRate;		// 환경 부담 세율 실수
	
	static Edge[] edgeList;		// 간선 정보
	static int[] parentList;	// 부모 정보
	static int[] rankList;		// 랭크 정보
	
	public static boolean union(int element1, int element2) {
		int e1Root = find(element1);
		int e2Root = find(element2);
		
		if (e1Root == e2Root)
			return false;
		
		// e1의 랭크가 크도록 조정 (e1 아래로 e2 합치기 위해)
		if (rankList[e1Root] < rankList[e2Root]) {
			int tmp = e1Root;
			e1Root = e2Root;
			e2Root = tmp;
		}
		parentList[e2Root] = e1Root;
		
		if (rankList[e1Root] == rankList[e2Root]) 
			rankList[e1Root]++;
		return true;
	}
	
	public static int find(int element) {
		if (parentList[element] == element)
			return element;
		return parentList[element] = find(parentList[element]);
	}
	
	public static void make() {
		parentList = new int[islandCnt];
		rankList = new int[islandCnt];
		
		for (int idx = 0; idx < islandCnt; idx++) {
			parentList[idx] = idx;
			rankList[idx] = 0;
		}
	}
	
	// 환경 부담금(가중치) = 환경 부담 세율 * (해저 터널의 길이 ^ 2)
	public static double getWeight(int idx1, int idx2) {
		// 해저 터널 길이의 제곱 값
		double distancePow = Math.pow(islandPosition[idx1][X] - islandPosition[idx2][X], 2) 
				+ Math.pow(islandPosition[idx1][Y] - islandPosition[idx2][Y], 2);
		
		return taxRate * distancePow;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트케이스 개수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 섬의 개수가 입력된다.
			islandCnt = Integer.parseInt(br.readLine().trim());
			edgeList = new Edge[islandCnt * (islandCnt-1) / 2];
			
			// 3. 각 섬들의 x좌표들, y좌표들이 입력된다.
			islandPosition = new int[islandCnt][2];
			
			// X 좌표
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < islandCnt; idx++)
				islandPosition[idx][X] = Integer.parseInt(st.nextToken());
			// Y 좌표
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < islandCnt; idx++)
				islandPosition[idx][Y] = Integer.parseInt(st.nextToken());
			
			// 4. 환경 부담 세율 실수가 입력된다.
			taxRate = Double.parseDouble(br.readLine().trim());
			
			// 5. 트리의 각 원소 집합을 생성한다.
			make();
			
			// 6. 각 섬 간의 간선 리스트를 생성하고, 환경 부담금(가중치) 값이 오름차순이 되도록 정렬한다.
			int idx = 0;
			for (int element1 = 0; element1 < islandCnt - 1; element1++) {
				for (int element2 = element1 + 1; element2 < islandCnt; element2++) {
					edgeList[idx++] = new Edge(element1, element2, getWeight(element1, element2));
				}
			}
			Arrays.sort(edgeList);
	
			// 7. kruskal 알고리즘을 통해 총 환경 부담금(가중치)을 최소로 하는 최소 신장 트리를 구한다.
			double mstWeight = 0;
			int mstCnt = 0;
			for (Edge edge : edgeList) {
				// 7-1. 사이클을 만드는 간선의 경우, 패스
				if (!union(edge.from, edge.to))
					continue;
				
				// 7-2. 사이클을 만들지 않는다면, 해당 간선을 해저터널로 선택(가중치 추가)
				mstWeight += edge.weight;
				
				// 7-3. 모든 섬을 선택했다면, 종료
				if (++mstCnt == islandCnt - 1)
					break;
			}
			
			sb.append("#").append(tc).append(" ").append(Math.round(mstWeight)).append("\n");
		}
		System.out.println(sb);
	}
}