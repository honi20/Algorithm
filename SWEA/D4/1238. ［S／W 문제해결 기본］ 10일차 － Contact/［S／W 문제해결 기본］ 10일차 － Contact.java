/**
 * 1. 데이터 길이와 시작점이 입력된다.
 * 2. 연락 관계 데이터가 입력된다. (from to from to ...)
 * 3. 시작 당번부터 연락를 시작한다.
 * 	3-1. 시작 사람을 큐에 넣는다.(방문 처리)
 * 	3-2. 빈 큐가 될 때까지 탐색을 진행한다.
 * 	3-3. 큐의 맨 앞 값을 꺼낸다.
 * 	3-4. 결괏값 갱신 (순서가 가장 큰 순 -> 번호가 가장 큰 순)
 * 	3-5. 해당 번호 사람이 연락 가능한 다른 사람에게 연락을 취한다.
 * 		3-5-1. 이미 선택된 사람이면 패스
 * 		3-5-2. 다음 사람의 번호와 순서를 큐에 넣는다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Node {
		int to;
		Node next;
		
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	static class Person {
		int num;	// 번호
		int order;	// 전화 받은 순서
		
		public Person(int num, int order) {
			this.num = num;
			this.order = order;
		}
	}
	
	static final int TESTCASE = 10;
	static final int PEOPLE_MAX = 100;
	
	static int dataSize;	// 데이터 크기
	static int startPerson;	// 시작 사람 번호
	static Node[] calling;	// 연락 관계
	static boolean[] isVisited;		// 연락 완료 여부
	static int maxOrder, maxNum;	// 결과 사람의 순서와 번호
	
	public static void callToOther(int startNum) {
		Queue<Person> queue = new ArrayDeque<>();
		
		// 3-1. 시작 사람을 큐에 넣는다.(방문 처리)
		queue.offer(new Person(startNum, 0));
		isVisited[startNum] = true;
		
		// 3-2. 빈 큐가 될 때까지 탐색을 진행한다.
		while (!queue.isEmpty()) {
			// 3-3. 큐의 맨 앞 값을 꺼낸다.
			Person person = queue.poll();
			int currentNum = person.num;
			int currentOrder = person.order;
			
			// 3-4. 결괏값 갱신 (순서가 가장 큰 순 -> 번호가 가장 큰 순)
			if (maxOrder < currentOrder) {
				maxOrder = currentOrder;
				maxNum = currentNum;
			}
			else if (maxOrder == currentOrder)
				maxNum = Math.max(maxNum, currentNum);
			
			// 3-5. 해당 번호 사람이 연락 가능한 다른 사람에게 연락을 취한다.
			for (Node other = calling[currentNum]; other != null; other = other.next) {
				// 3-5-1. 이미 선택된 사람이면 패스
				if (isVisited[other.to])
					continue;
				
				// 3-5-2. 다음 사람의 번호와 순서를 큐에 넣는다.
				isVisited[other.to] = true;
				queue.offer(new Person(other.to, currentOrder + 1));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int tc = 1; tc <= TESTCASE; tc++) {
			// init
			maxOrder = maxNum = -1;
			calling = new Node[PEOPLE_MAX + 1];
			isVisited = new boolean[PEOPLE_MAX + 1];
			
			// 1. 데이터 길이와 시작점이 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			dataSize = Integer.parseInt(st.nextToken());
			startPerson = Integer.parseInt(st.nextToken());
			
			// 2. 연락 관계 데이터가 입력된다. (from to from to ...)
			String[] data = br.readLine().trim().split(" ");
			for (int idx = 0; idx < dataSize; idx += 2) {
				int from = Integer.parseInt(data[idx]);
				int to = Integer.parseInt(data[idx + 1]);
				
				calling[from] = new Node(to, calling[from]);
			}
			
			// 3. 시작 당번부터 연락를 시작한다.
			callToOther(startPerson);
			
			sb.append("#").append(tc).append(" ").append(maxNum).append("\n");
		}
		System.out.println(sb);
	}
}
