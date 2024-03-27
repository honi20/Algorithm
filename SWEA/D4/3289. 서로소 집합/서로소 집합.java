/**
 * 1. 테스트 케이스 수가 입력된다.
 * 2. 집합의 개수와 연산의 개수가 입력된다.
 * 3. 모든 원소의 부모 배열과 랭크 배열을 초기화한다.
 * 4. 각각의 연산 정보가 입력된다.
 * 	4-1. 합집합 연산
 * 	4-2. 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int elementCnt;		// 원소 개수
	static int calculationCnt;	// 연산 개수
	
	static int[] parentList;	// 부모 정보
	static int[] rankList;		// 랭크 정보
	
	public static void union(int element1, int element2) {
		int e1Parent = find(element1);
		int e2Parent = find(element2);
		
		// 두 원소가 같은 집합에 속한 경우
		if (e1Parent == e2Parent)
			return;
		
		// e1의 집합 랭크가 e2 집합 랭크보다 큰 경우, e1 아래에 e2 합침
		if (rankList[e1Parent] > rankList[e2Parent]) {
			parentList[e2Parent] = e1Parent;
			return;
		}
		
		// e1의 집합 랭크가 e2 집합 랭크보다 작거나 같은 경우, e2 아래에 e1 합침
		parentList[e1Parent] = e2Parent;
		
		// 두 집합의 랭크가 같은 경우, 합쳐진 랭크 크기 1 증가
		if (rankList[e1Parent] == rankList[e2Parent])
			rankList[e2Parent]++;
	}
	
	public static int find(int element) {
		// 나 자신이 루트 노드인 경우
		if (parentList[element] == element)
			return element;
		
		// 경로 압축
		return parentList[element] = find(parentList[element]);
	}
	
	public static void makeSet() {
		parentList = new int[elementCnt + 1];
		rankList = new int[elementCnt + 1];
		
		for (int idx = 0; idx < elementCnt + 1; idx++) {
			parentList[idx] = idx;
			rankList[idx] = 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 2. 집합의 개수와 연산의 개수가 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			elementCnt = Integer.parseInt(st.nextToken());
			calculationCnt = Integer.parseInt(st.nextToken());
			
			// 3. 모든 원소의 부모 배열과 랭크 배열을 초기화한다.
			makeSet();
			
			// 4. 각각의 연산 정보가 입력된다.
			while (calculationCnt-- > 0) {				
				st = new StringTokenizer(br.readLine().trim());
				int num = Integer.parseInt(st.nextToken());
				int element1 = Integer.parseInt(st.nextToken());
				int element2 = Integer.parseInt(st.nextToken());
				
				// 4-1. 합집합 연산
				if (num == 0) {
					// 두 집합 합치기
					union(element1, element2);
				}
				// 4-2. 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산
				else if (num == 1) {
					// 같은 집합에 속해있는 경우
					if (find(element1) == find(element2))
						sb.append(1);
					// 다른 집합에 속해있는 경우
					else
						sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
