/**
 * 1. 본문 문자열이 입력된다.
 * 2. 패턴 문자열이 입력된다.
 * 3. 패턴 문자열에 대해 부분 일치 테이블 배열 pi을 만든다.
 * 	- pi[k] : 패턴의 k 인덱스까지의 부분 문자열에 대하여 접두사와 접미사가 일치하는 최대 길이
 * 	- 패턴 문자열과 같은 접두사 패턴 문자열이 존재한다고 가정하면서 최대 길이를 구한다.
 * 	
 * 	3-1. 패턴 문자열의 인덱스 1부터 pi 값을 구해간다. (인덱스 0은 접미사와 접두사가 자기 자신이기 때문에 pi값을 0으로 고정)
 * 	3-2. 패턴의 현재 문자와 접두사 패턴의 현재 문자가 같은 경우 => 접두사 인덱스에 +1한 값을 pi에 저장한다.(접두사 길이만큼 일치한다는 의미) -> 패턴, 접두사 패턴 인덱스 증가
 * 	3-3. 패턴의 현재 문자와 접두사 패턴의 현재 문자가 다를 경우
 * 		1) 접두사 인덱스가 0인 경우 => 패턴의 인덱스 증가 (현재 인덱스까지의 문자열에서 접두사와 접미사의 일치하는 부분이 없음을 의미)
		2) 접두사 인덱스가 0이 아닌 경우 => 접두사의 인덱스를 이전 접두사 인덱스의 pi 값으로 갱신
			(이전 접두사 문자까지는 접미사와 일치하기 때문에 해당 길이만큼의 문자들은 비교 생략)
 * 	
 * 4. 본문 문자열에서 패턴 문자열이 어디에 몇 번 등장하는지 탐색한다.
 * 	4-1. 본문 문자열을 탐색하며 패턴 문자열이 존재하는지 확인한다.
 * 	4-2. 본문 현재 문자와 패턴 현재 문자가 같은 경우 => 본문 인덱스와 패턴 인덱스 각각 1 증가
 * 		1) 만약 패턴의 전체 문자열을 구한 경우, 패턴이 시작되는 인덱스 저장 & 패턴 인덱스를 현재 인덱스의 pi 값으로 갱신
 * 		2) 아니라면, 패턴 인덱스 1 증가
 * 	4-3. 본문 현재 문자와 패턴 현재 문자가 다른 경우
 * 		1) 패턴 인덱스가 0인 경우 => 본문의 인덱스 증가
 * 		2) 패턴 인덱스가 0이 아닌 경우 => 패턴의 인덱스를 이전 인덱스의 pi 값으로 갱신
 * 5. 패턴 등장 횟수와 위치 출력
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static String origin;
	static String pattern;
	static int[] pi;
	static List<Integer> results;
	
	// 패턴 문자열과 같은 접두사 패턴 문자열이 존재한다고 가정하면서 최대 길이를 구한다.
	public static void getPiArray() {
		pi = new int[pattern.length()];
		
		// 3-1. 패턴 문자열의 인덱스 1부터 pi 값을 구해간다. (인덱스 0은 접미사와 접두사가 자기 자신이기 때문에 pi값을 0으로 고정)
		int idx = 1, prefixIdx = 0;
		while (idx < pattern.length()) {
			
			// 3-2. 패턴의 현재 문자와 접두사 패턴의 현재 문자가 같은 경우 => 접두사 인덱스에 +1한 값을 pi에 저장한다.(접두사 길이만큼 일치한다는 의미) -> 패턴, 접두사 패턴 인덱스 증가
			if (pattern.charAt(idx) == pattern.charAt(prefixIdx)) {
				pi[idx] = ++prefixIdx;
				idx++;
			}
			
			// 3-3. 패턴의 현재 문자와 접두사 패턴의 현재 문자가 다를 경우
			else {
				// 1) 접두사 인덱스가 0인 경우 => 패턴의 인덱스 증가 (현재 인덱스까지의 문자열에서 접두사와 접미사의 일치하는 부분이 없음을 의미)
				if (prefixIdx == 0)
					idx++;
				
				// 2) 접두사 인덱스가 0이 아닌 경우 => 접두사의 인덱스를 이전 접두사 인덱스의 pi 값으로 갱신
				// (이전 접두사 문자까지는 접미사와 일치하기 때문에 해당 길이만큼의 문자들은 비교 생략)
				else {
					prefixIdx = pi[prefixIdx - 1];
				}
			}
		}
	}
	
	public static void getPatternInOrigin() {
		// 4-1. 본문 문자열을 탐색하며 패턴 문자열이 존재하는지 확인한다.
		int originIdx = 0, patternIdx = 0;
		
		while (originIdx < origin.length()) {
			// 4-2. 본문 현재 문자와 패턴 현재 문자가 같은 경우 => 본문 인덱스와 패턴 인덱스 각각 1 증가
			if (origin.charAt(originIdx) == pattern.charAt(patternIdx)) {
				// 1) 만약 패턴의 전체 문자열을 구한 경우, 패턴이 시작되는 인덱스 저장 & 패턴 인덱스를 현재 인덱스의 pi 값으로 갱신
				if (patternIdx == pattern.length() - 1) {
					results.add(originIdx - pattern.length() + 1);
					patternIdx = pi[patternIdx];
				}
				
				// 2) 아니라면, 패턴 인덱스 1 증가
				else
					patternIdx++;
				
				originIdx++;
			}
			
			// 4-3. 본문 현재 문자와 패턴 현재 문자가 다른 경우
			else {
				// 1) 패턴 인덱스가 0인 경우 => 본문의 인덱스 증가
				if (patternIdx == 0)
					originIdx++;
				
				// 2) 패턴 인덱스가 0이 아닌 경우 => 패턴의 인덱스를 이전 인덱스의 pi 값으로 갱신
				else {
					patternIdx = pi[patternIdx - 1];
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		results = new ArrayList<>();
		
		// 앞 뒤의 공백을 제거하면 안되기 때문에 trim 하지 말기!!!!
		// 1. 본문 문자열이 입력된다.
		origin = br.readLine();
		
		// 2. 패턴 문자열이 입력된다.
		pattern = br.readLine();

		// 3. 패턴 문자열에 대해 부분 일치 테이블 배열 pi을 만든다.
		getPiArray();

		// 4. 본문 문자열에 패턴이 등장하는 위치와 횟수를 구한다.
		getPatternInOrigin();
		
		// 5. 패턴 등장 횟수와 위치 출력
		sb.append(results.size()).append("\n");
		for (int result : results) {
			// 문제의 문자들은 인덱스 1부터 시작하기 때문에 1 증가하여 출력
			sb.append(result + 1).append(" ");
		}
		System.out.println(sb);
	}
}