/**
 * 1. 테스트 케이스의 길이를 입력한다.
 * 2. 테스트 케이스를 입력한다.
 * 3. 유효성 여부를 체크한다.
 * 	3-1. 왼쪽 괄호인 경우, 스택에 push
 * 	3-2. 오른쪽 괄호인 경우, 
 * 		3-3. 스택이 비어있다면, 유효하지 않음
 * 		3-4. 스택 top의 왼쪽 괄호와 짝이 맞다면, pop
 * 		3-5. 스택 top의 왼쪽 괄호와 짝이 안맞다면, 유효하지 않음
 * 	3-6. 문자열을 모두 돌아보고 스택이 비어있지 않는 경우, 유효하지 않음 + 스택 초기화
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int TESTCASE = 10;
	static final List<Integer> LEFT_PARENTHESIS = new ArrayList<Integer>(Arrays.asList(40, 91, 123, 60));	// 왼쪽 괄호
	static final List<Integer> RIGHT_PARENTHESIS = new ArrayList<Integer>(Arrays.asList(41, 93, 125, 62));	// 오른쪽 괄호
	
	static int length;			// 문자열 길이
	static String parenthesisStr;	// 괄호들의 문자열
	static Stack<Integer> stack;	// 스택
	static int validation;			// 유효성 여부
	
	static int currentParen, topParen;		// 문자열의 현재 괄호, 스택 맨 위의 괄호
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		stack = new Stack<>();
		
		for (int tc = 1; tc <= TESTCASE; tc++) {
			validation = -1;
			
			// 1. 문자열의 길이를 입력한다.
			length = Integer.parseInt(br.readLine().trim());
			
			// 2. 괄호들의 문자열을 입력한다.
			parenthesisStr = br.readLine().trim();
			
			// 3. 유효성 여부를 체크한다.
			for (int idx = 0; idx < length; idx++) {
				currentParen = parenthesisStr.charAt(idx);
				
				// 3-1. 왼쪽 괄호인 경우, 스택에 push
				if (LEFT_PARENTHESIS.contains(currentParen)) {
					stack.push(currentParen);
				}
				
				// 3-2. 오른쪽 괄호인 경우, 
				else if (RIGHT_PARENTHESIS.contains(currentParen)) {
					// 3-3. 스택이 비어있다면, 유효하지 않음
					if (stack.isEmpty()) {
						validation = 0;
						break;
					}
					
					else {
						topParen = stack.peek();
						// 3-4. 스택 top의 왼쪽 괄호와 짝이 맞다면, pop
						if (LEFT_PARENTHESIS.indexOf(topParen) == RIGHT_PARENTHESIS.indexOf(currentParen)) {
							stack.pop();
						}
						// 3-5. 스택 top의 왼쪽 괄호와 짝이 안맞다면, 유효하지 않음
						else {
							validation = 0;
							break;
						}
					}
				}
			}
			
			// 3-6. 문자열을 모두 돌아보고 스택이 비어있지 않는 경우, 유효하지 않음 + 스택 초기화
			if (!stack.isEmpty()) {
				validation = 0;
				stack.clear();
			}
			
			// 유효한 경우
			if (validation == -1)
				validation = 1;
			
			sb.append("#").append(tc).append(" ").append(validation).append("\n");
		}
		System.out.println(sb);
	}
}
