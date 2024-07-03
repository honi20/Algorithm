/**
 * 1. 동영상 개수와 질문의 개수가 입력된다.
 * 2. 동영상 쌍의 USADO가 입력된다. (2개의 동영상 번호, USADO)
 * 3. 플로이드 와샬을 통해 각 동영상 쌍의 USADO 값을 구한다.
 * 	3-1. 큐를 통해 두 노드 간의 usado 값을 구한다.
 * 	3-2. 현재 노드가 도착 노드가 아닌 경우, 연결된 노드 탐색
 * 4. 질문이 입력된다. (K값, 동영상 번호)
 * 	4-1. 해당 비디오와 연결된 비디오 중 base값 이상인 비디오 개수를 구한다.
 */
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int INF = 1234567890;
	
	static class Link {
		int video;
		int value;
		
		public Link(int video, int value) {
			this.video = video;
			this.value = value;
		}
	}
	
	static class Node {
		int video;
		int minDist;
		
		public Node(int video, int minDist) {
			this.video = video;
			this.minDist = minDist;
		}
	}
	
	static int videoCnt;
	static int questionCnt;
	static int[][] usado;
	static List<Link>[] list;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 동영상 개수와 질문의 개수가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		videoCnt = Integer.parseInt(st.nextToken());
		questionCnt = Integer.parseInt(st.nextToken());
		
		// 2. 동영상 쌍의 USADO가 입력된다. (2개의 동영상 번호, USADO)
		init();
		for (int idx = 0; idx < videoCnt-1; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			usado[node1][node2] = usado[node2][node1] = value;
			list[node1].add(new Link(node2, value));
			list[node2].add(new Link(node1, value));
		}
		
		// 3. 각 동영상 쌍의 USADO 값을 구한다.
		for (int idx = 1; idx <= videoCnt; idx++) {
			getUsado(idx);
		}
		
		// 4. 질문이 입력된다. (K값, 동영상 번호)
		for (int idx = 0; idx < questionCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int base = Integer.parseInt(st.nextToken());
			int videoNum = Integer.parseInt(st.nextToken());
			
			// 4-1. 해당 비디오와 연결된 비디오 중 base값 이상인 비디오 개수를 구한다.
			int cnt = findConnectVideoCnt(base, videoNum);
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}

	public static void init() {
		usado = new int[videoCnt+1][videoCnt+1];
		list = new ArrayList[videoCnt+1];
		
		for (int idx = 0; idx <= videoCnt; idx++) {
			Arrays.fill(usado[idx], INF);
			usado[idx][idx] = 0;
			list[idx] = new ArrayList<Link>();
		}
	}
	
	public static void getUsado(int start) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[videoCnt+1];
		Arrays.fill(isVisited, false);
		
		// 3-1. 큐를 통해 두 노드 간의 usado 값을 구한다.
		isVisited[start] = true;
		queue.add(new Node(start, INF));
		
		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int curVideo = curNode.video;
			int curValue = curNode.minDist;
			
			usado[start][curVideo] = usado[curVideo][start] = Math.min(usado[start][curVideo], curValue);
			
			// 3-2. 현재 노드가 도착 노드가 아닌 경우, 연결된 노드 탐색
			for (int idx = 0; idx < list[curVideo].size(); idx++) {
				Link link = list[curVideo].get(idx);
				int linkVideo = link.video;
				int linkValue = link.value;
				
				if (isVisited[linkVideo])
					continue;
				
				isVisited[linkVideo] = true;
				queue.add(new Node(linkVideo, Math.min(curValue, linkValue)));
			}
		}
	}
	
	public static int findConnectVideoCnt(int base, int videoNum) {
		int result = 0;
		for (int idx = 1; idx <= videoCnt; idx++) {
			if (videoNum == idx) continue;
			
			if (usado[videoNum][idx] >= base) {
				++result;
			}
		}
		return result;
	}
	
	public static void printDist() {
		for (int idx = 1; idx <= videoCnt; idx++) {
			for (int idx2 = 1; idx2 <= videoCnt; idx2++) {
				System.out.println(idx + " " + idx2 + "=> " + usado[idx][idx2]);
			}
		}
	}
}
