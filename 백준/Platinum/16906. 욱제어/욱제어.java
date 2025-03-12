import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final String CHILD = "01";

	static class Node {
		Map<Character, Node> child;
		boolean endOfWord;

		public Node() {
			this.child = new HashMap<>();
			this.endOfWord = false;
		}
	}

	static class Input implements Comparable<Input> {
		int index;
		int length;

		public Input(int index, int value) {
			this.index = index;
			this.length = value;
		}

		@Override
		public int compareTo(Input i) {
			return this.length - i.length;
		}
	}

	static int inputCnt;
	static List<Input> inputs;
	static String[] answer;

	static Node root;

	static Node baseNode;
	static int baseLen;
	static String baseStr;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		inputs = new ArrayList<>();
		root = new Node();

		inputCnt = Integer.parseInt(br.readLine().trim());
		answer = new String[inputCnt];

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < inputCnt; idx++) {
			int length = Integer.parseInt(st.nextToken());
			inputs.add(new Input(idx, length));
		}

		// 길이 오름차순 정렬
		Collections.sort(inputs);

		// 단어를 만들 수 있는 노드를 구한다.
		for (Input input : inputs) {
			baseNode = null;
			baseLen = -1;
			baseStr = "";
			solve(root, 0, "", input.length);

			// 만들 수 있는 경우가 없는 경우
			if (baseNode == null) {
				System.out.println(-1);
				return;
			}
			
			// 만들 수 있는 경우, 추가
			else {
				// 기준 노드의 자식 노드가 빈 경우, 0을 남은 길이만큼 추가
				if (baseNode.child.isEmpty()) {
					insert(baseNode, input.length - baseLen);
					answer[input.index] = baseStr + "0".repeat(input.length - baseLen);
				}
				// 기준 노드의 자식 노드가 1개 존재하는 경우, 1을 추가 후 남은 길이만큼 0 추가
				else {
					baseNode.child.putIfAbsent('1', new Node());
					insert(baseNode.child.get('1'), input.length - baseLen - 1);
					answer[input.index] = baseStr + "1" + "0".repeat(input.length - baseLen - 1);
				}
			}
		}
		
		sb.append("1\n");
		for (String ans : answer) {
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);

	}

	private static void solve(Node curNode, int curLen, String curStr, int maxLen) {
		// 현재 길이가 원하는 길이인 경우, 더이상 탐색 안함
		if (curLen == maxLen) {
			return;
		}

		// 기준 문자열이 더 길다면, 업데이트
		if (curLen > baseLen) {
			if (curNode.child.isEmpty() || curNode.child.size() < 2) {
				baseNode = curNode;
				baseLen = curLen;
				baseStr = curStr;
			}
		}

		// 자식 노드로 탐색
		for (int idx = 0; idx < CHILD.length(); idx++) {
			char c = CHILD.charAt(idx);

			// 자식 노드에 해당 문자가 없는 경우, 패스
			if (!curNode.child.containsKey(c)) {
				continue;
			}

			// 해당 자식 노드가 다른 단어의 마지막 문자인 경우, 패스
			if (curNode.child.get(c).endOfWord) {
				continue;
			}

			solve(curNode.child.get(c), curLen + 1, curStr + c, maxLen);
		}
	}

	private static void insert(Node curNode, int len) {
		Node node = curNode;

		while (len-- > 0) {
			node.child.put('0', new Node());

			node = node.child.get('0');
		}

		node.endOfWord = true;
	}
}