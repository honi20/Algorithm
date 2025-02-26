import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Node {
		Node prev;
		long sum;
		long top;

		Node(Node prev, long sum, long top) {
			this.prev = prev;
			this.sum = sum;
			this.top = top;
		}
	}

	static List<Node> stack;
	static Node[] arr;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int queryCnt = Integer.parseInt(br.readLine().trim());

		stack = new ArrayList<>();
		arr = new Node[queryCnt + 1];

		Node start = new Node(null, 0L, -1);
		stack.add(start);
		arr[0] = start;

		for (int idx = 1; idx <= queryCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			String command = st.nextToken();

			switch (command) {
			case "push":
				push(idx, Long.parseLong(st.nextToken()));
				break;
			case "pop":
				pop(idx);
				break;
			case "restore":
				restore(idx, Integer.parseInt(st.nextToken()));
				break;
			case "print":
				print(idx);
				break;
			}
		}

		System.out.print(sb);
	}

	private static void push(int index, long val) {
		// 명령 전 마지막 노드 뒤에 추가로 연결한다.
		Node lastNode = arr[index - 1];
		Node newNode = new Node(lastNode, lastNode.sum + val, val);
		arr[index] = newNode;
	}

	private static void pop(int index) {
		// 명령 전 마지막 노드가 초기 head 노드인 경우, 패스
		Node lastNode = arr[index - 1];
		if (lastNode.prev == null) {
			return;
		}

		// 마지막 노드가 빠지게 되므로 마지막 노드의 이전 노드를 가리킨다.
		arr[index] = lastNode.prev;
	}

	private static void restore(int index, int step) {
		arr[index] = arr[step];
	}

	private static void print(int index) {
		Node lastNode = arr[index - 1];
		sb.append(lastNode.sum).append("\n");

		arr[index] = arr[index - 1];
	}
}
