import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Node implements Comparable<Node> {
		int position;
		int cnt;
		
		public Node(int position, int cnt) {
			this.position = position;
			this.cnt = cnt;
		}
		
		public int compareTo(Node n) {
			return this.cnt - n.cnt;
		}
	}
	
	static final int MAX = 100001;
	
	static int start;
	static int end;
	static int[] dp;
	static PriorityQueue<Node> queue;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dp = new int[MAX];
		Arrays.fill(dp, MAX);
		
		queue = new PriorityQueue<>();
		queue.offer(new Node(start, 0));
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int curPos = cur.position;
			int curCnt = cur.cnt;
	
			if (curPos == end) {
				System.out.println(curCnt);
				break;
			}
			
			if (curPos > 0 && dp[curPos-1] > curCnt + 1) {
				dp[curPos-1] = curCnt + 1;
				queue.offer(new Node(curPos-1, curCnt+1));
			}
			
			if (curPos < MAX-1 && dp[curPos+1] > curCnt + 1) {
				dp[curPos+1] = curCnt + 1;
				queue.offer(new Node(curPos+1, curCnt+1));
			}
			
			if (curPos > 0 && curPos*2 < MAX && dp[curPos*2] > curCnt) {
				dp[curPos*2] = curCnt;
				queue.offer(new Node(curPos*2, curCnt));
			}
		}
	}
}