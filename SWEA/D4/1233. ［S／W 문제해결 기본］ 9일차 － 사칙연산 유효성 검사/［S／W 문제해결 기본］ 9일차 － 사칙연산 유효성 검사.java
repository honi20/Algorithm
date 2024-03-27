/**
 * 1. 각 케이스의 트리가 갖는 정점의 총 수가 입력된다.
 * 2. 트리가 완전 이진 트리이기 때문에, 트리가 갖는 정점의 총 수를 통해 리프 노드들의 번호들을 구한다.
 * 	2-1. 종료 조건 : 현재 깊이까지의 포화 이진 트리의 노드 총 개수가 입력된 트리 개수보다 크거나 같은 경우
 * 		- 리프 노드의 개수를 구한다. (현재 트리의 노드 총 개수 - 전 레벨의 포화 이진 트리의 노드 총 개수)
 * 	2-2. 다음 깊이를 탐색한다.
 * 	2-3. 리프 노드의 개수가 홀수인 경우, 적절하지 않은 식
 * 3. 각 노드의 값, 왼쪽 자식의 노드 번호, 오른쪽 자식의 노드 번호가 입력된다.
 * 4. 왼쪽, 오른쪽 자식 노드를 갖는 노드인 경우
 * 	4-1. 리프 노드가 아닌 노드의 값이 숫자인 경우, 적절하지 않은 식
 * 5. 자식 노드를 갖지 않는 노드인 경우
 * 	5-1. 리프 노드의 값이 연산자인 경우, 적절하지 않은 식
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int TESTCASE = 10;
	
	static int nodeSize;		// 노드의 총 개수
	static int leafNodeCount;	// 리프 노드의 개수
	
	// 파라미터의 깊이를 갖는 포화 이진 트리가 가질 수 있는 총 노드의 개수
	public static int getTotalNodeOfPerfectBT(int depth) {
		return (int) (Math.pow(2, depth+1) - 1);
	}
	
	// 완전 이진 트리의 리프 노드 탐색
	public static void getLeafNodeCount(int depth) {
		// 2-1. 종료 조건 : 현재 깊이까지의 포화 이진 트리의 노드 총 개수가 입력된 트리 개수보다 크거나 같은 경우
		int totalNode = getTotalNodeOfPerfectBT(depth);
		
		if (totalNode >= nodeSize) {
			// 리프 노드의 개수를 구한다. (현재 트리의 노드 총 개수 - 전 레벨의 포화 이진 트리의 노드 총 개수)
			leafNodeCount = nodeSize - getTotalNodeOfPerfectBT(depth - 1);
			return;
		}
		
		// 2-2. 다음 깊이를 탐색한다.
		getLeafNodeCount(depth + 1);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int tc = 1; tc <= TESTCASE; tc++) {
			boolean isSuitable = true;		// 식의 적절성 여부 저장
			
			sb.append("#").append(tc).append(" ");
			
			// 1. 각 케이스의 트리가 갖는 정점의 총 수가 입력된다.
			nodeSize = Integer.parseInt(br.readLine().trim());
			
			// 2. 트리가 완전 이진 트리이기 때문에, 트리가 갖는 정점의 총 수를 통해 리프 노드들의 번호들을 구한다.
			getLeafNodeCount(0);
			
			// 2-3. 리프 노드의 개수가 홀수인 경우, 적절하지 않은 식
			if (leafNodeCount % 2 != 0)
				isSuitable = false;
			
			for (int node = 1; node <= nodeSize; node++) {
				// 3. 각 노드의 값, 왼쪽 자식의 노드 번호, 오른쪽 자식의 노드 번호가 입력된다.
				st = new StringTokenizer(br.readLine().trim());
				
				st.nextToken();		// 현재 노드의 번호
				
				// 4. 왼쪽, 오른쪽 자식 노드를 갖는 노드인 경우
				if (st.countTokens() == 3) {
					try {
						// 노드의 값이 숫자인 경우
						int nodeValue = Integer.parseInt(st.nextToken());
						
						// 4-1. 리프 노드가 아닌 노드의 값이 숫자인 경우, 적절하지 않은 식
						isSuitable = false;
						
					} catch (NumberFormatException e) {	
						// 노드의 값이 연산자인 경우
					}
				}
				
				// 5. 자식 노드를 갖지 않는 노드인 경우
				else if (st.countTokens() == 1) {
					// 노드의 값
					try {
						// 노드의 값이 숫자인 경우
						int nodeValue = Integer.parseInt(st.nextToken());
					} catch (NumberFormatException e) {	
						// 5-1. 리프 노드의 값이 연산자인 경우, 적절하지 않은 식
						isSuitable = false;
					}
				}
			}
			
			if (isSuitable)
				sb.append(1);
			else
				sb.append(0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}