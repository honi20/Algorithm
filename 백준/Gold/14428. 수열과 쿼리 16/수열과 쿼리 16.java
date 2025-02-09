import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Node {
		int index;
		int value;
		
		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
	
	static int arrSize; 
	static int[] arr;
	static int queryCnt;
	static Node[] tree;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		arrSize = Integer.parseInt(br.readLine().trim());
		
		arr = new int[arrSize+1];
		arr[0] = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= arrSize; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}
		
		queryCnt = Integer.parseInt(br.readLine().trim());
		
		int height = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
		tree = new Node[(1 << (height+1))];
		init(1, 1, arrSize);
		
		while (queryCnt-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			int type = Integer.parseInt(st.nextToken());
			int val1 = Integer.parseInt(st.nextToken());
			int val2 = Integer.parseInt(st.nextToken());
			
			if (type == 1) {
				arr[val1] = val2;
				update(1, 1, arrSize, new Node(val1, val2));
			}
			else if (type == 2) {
				Node ans = query(1, 1, arrSize, val1, val2);
				sb.append(ans.index).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static Node query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return null;
		}
		
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end) / 2;
		return getMin(query(node*2, start, mid, left, right),
				query(node*2+1, mid + 1, end, left, right));
				
	}
	
	private static void update(int node, int start, int end, Node newNode) {
		if (newNode.index < start || end < newNode.index) {
			return;
		}
		
		if (tree[node].index == newNode.index) {
			tree[node] = newNode;
		}
		else {
			tree[node] = getMin(tree[node], newNode);
		}
		
		if (start != end) {
			int mid = (start+end) / 2;
			update(node*2, start, mid, newNode);
			update(node*2+1, mid + 1, end, newNode);
			tree[node] = getMin(tree[node*2], tree[node*2+1]);
		}
	}
	
	private static Node init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = new Node(start, arr[start]);
		}
		
		int mid = (start+end) / 2;
		return tree[node] = getMin(init(node*2, start, mid), init(node*2+1, mid + 1, end));
	}
	
	private static Node getMin(Node node1, Node node2) {
		// 값이 없는 경우
		if (node1 == null) {
			return node2;
		}
		else if (node2 == null) {
			return node1;
		}
		
		// 서로 다른 노드의 값 비교
		if (node1.value == node2.value && node1.index < node2.index) {
			return node1;
		}
		else if (node1.value < node2.value) {
			return node1;
		}
		
		return node2;
	}
}