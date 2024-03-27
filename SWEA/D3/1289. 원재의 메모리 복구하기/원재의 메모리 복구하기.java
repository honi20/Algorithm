import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// 1. 테스트 케이스 개수 입력 받기
		int testCase = Integer.parseInt(br.readLine().trim());
		String nowMemory;		// 원래 메모리 값
		int compareBit;			// 원래 메모리 값과 비교할 초기 비트
		int result;				// 수정 횟수
		
		for (int tc=0; tc<testCase; tc++ ) {
			compareBit = 0;
			result = 0;
			
			// 2. 메모리의 원래 값 입력 받기
			nowMemory = br.readLine().trim();
			
			// 3. 반복문을 통해 원래 메모리의 각 비트 값 확인
			for (int idx=0; idx<nowMemory.length(); idx++) {
				int nowBit = nowMemory.charAt(idx) - '0';
				
				// 4. 만약 초기 메모리의 비트와 원래 메모리의 비트가 같지 않은 경우
				if (nowBit != compareBit) {
					// 4-1. 수정 횟수 증가
					result++; 	
					// 4-2. 초기 메모리 비트 변경 - 0이면 1로, 1이면 0으로 변경
					compareBit = Math.abs(compareBit - 1);
				}
			}
			// 5. 수정 횟수 출력
			sb.setLength(0);
			sb.append("#").append(tc+1).append(" ").append(result);
			System.out.println(sb);
		}
	}
}
