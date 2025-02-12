import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int SELECT = 0;
	static final int NOT_SELECT = 1;
	
	static int nodeCnt;
	static List<Integer>[] edges;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		nodeCnt = Integer.parseInt(br.readLine().trim());
		edges = new ArrayList[nodeCnt+1];
		
		for (int idx = 1; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			if (edges[node1] == null) {
				edges[node1] = new ArrayList<>();
			}
			if (edges[node2] == null) {
				edges[node2] = new ArrayList<>();
			}
			
			edges[node1].add(node2);
			edges[node2].add(node1);
		}
		
		// 어느 노드를 루트로 하든 트리 형태
		dp = new int[nodeCnt+1][2];
		solve(1, 0);
		
		System.out.println(Math.min(dp[1][SELECT], dp[1][NOT_SELECT]));
	}
	
	private static void solve(int node, int parent) {
		List<Integer> list = edges[node];
		if (list == null) {
			return;
		}
		
		dp[node][SELECT] = 1;
		dp[node][NOT_SELECT] = 0;
		
		// 자식 노드를 탐색한다.
		for (int idx = 0; idx < list.size(); idx++) {
			int nextNode = list.get(idx);
			
			// 부모 노드인 경우, 패스
			if (nextNode == parent)
				continue;
			
			solve(nextNode, node);
			
			// 현재 노드를 선택한다면, 자식 노드의 선택 또는 선택안함 중 최소값 선택
			dp[node][SELECT] += Math.min(dp[nextNode][SELECT], dp[nextNode][NOT_SELECT]);
			
			// 현재 노드를 선택하지 않는다면, 자식 노드 무조건 선택
			dp[node][NOT_SELECT] += dp[nextNode][SELECT];
		}
	}
}
