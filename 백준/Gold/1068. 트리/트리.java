import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int nodeCnt;
	static int root;
	static int[] parent;
	static int removeNum;
	static boolean[] isVisited;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		nodeCnt = Integer.parseInt(br.readLine().trim());
		
		parent = new int[nodeCnt];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < nodeCnt; idx++) {
			parent[idx] = Integer.parseInt(st.nextToken());
			
			if (parent[idx] == -1)
				root = idx;
		}
		
		removeNum = Integer.parseInt(br.readLine().trim());
		
		remove(removeNum);
		
		isVisited = new boolean[nodeCnt];
		Arrays.fill(isVisited, false);
		dfs(root);
		
		System.out.println(result);
	}
	
	private static void remove(int node) {
		parent[node] = Integer.MAX_VALUE;
		
		for (int idx = 0; idx < nodeCnt; idx++) {
			if (parent[idx] == node) {
				remove(idx);
			}
		}
	}
	
	private static void dfs(int node) {
		boolean flag = true;
		isVisited[node] = true;
		
		if (parent[node] != Integer.MAX_VALUE) {
			for (int idx = 0; idx < nodeCnt; idx++) {
				if (parent[idx] == node && !isVisited[idx]) {
					dfs(idx);
					flag = false;
				}
			}
			
			if (flag) {
				++result;
			}
		}
	}
}
