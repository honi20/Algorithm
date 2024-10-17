import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int PLUS = 1;
	static final int MINUS = 2;
	static final int SPACE = 3;
	
	static int testCase;
	static int N;
	static List<Character> result;
	static Stack<Character> s;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		result = new ArrayList<>();
		s = new Stack<>();
		testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < testCase; tc++) {
			result.clear();
			N = Integer.parseInt(br.readLine().trim());
			
			result.add('1');
			solve(2);
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void solve(int idx) {
		if (idx == N+1) {
			if (isZero()) {
				printList();
			}
			return;
		}
		
		// 공백
		result.add(' ');
		result.add((char)(idx+'0'));
		solve(idx+1);
		result.remove(result.size()-1);
		result.remove(result.size()-1);
				
		// 덧셈
		result.add('+');
		result.add((char)(idx+'0'));
		solve(idx+1);
		result.remove(result.size()-1);
		result.remove(result.size()-1);
		
		// 뺄셈
		result.add('-');
		result.add((char)(idx+'0'));
		solve(idx+1);
		result.remove(result.size()-1);
		result.remove(result.size()-1);
	}
	
	private static boolean isZero() {
		s.clear();
		int idx = 0;
		
		while (idx < result.size()) {
			char val = result.get(idx);
			
			// 현재 값이 숫자인 경우
			if (val >= '0' && val <= '9') {
				if (s.isEmpty()) {
					s.push(val);
				}
				else {
					// 맨 위 값이 연산자인 경우
					if (s.peek() == '+' || s.peek() == '-') {
						s.push(val);
					}
					else {
						int num = s.pop() - '0';
						int tmp = num*10 + (val-'0');
						s.push((char)(tmp+'0'));
					}
				}
			}
			
			// 현재 값이 연산자인 경우
			else if (val != ' ') {
				// 스택에 2개 이상의 값이 있는 경우 (연산자가 하나 있는 경우)
				if (s.size() > 1) {
					int num1 = s.pop() - '0';
					char oper = s.pop();
					int num2 = s.pop() - '0';
					
					if (oper == '+') {
						s.push((char)((num2+num1) + '0'));
					}
					else if (oper == '-') {
						s.push((char)((num2-num1) + '0'));
					}
				}
				s.push(val);
			}
			
			++idx;
		}
		
		if (s.size() > 1) {
			int num1 = s.pop() - '0';
			char oper = s.pop();
			int num2 = s.pop() - '0';
			
			if (oper == '+') {
				s.push((char)((num2+num1) + '0'));
			}
			else if (oper == '-') {
				s.push((char)((num2-num1) + '0'));
			}
		}
		
		if (s.peek() == '0')
			return true;
		
		return false;
	}
	
	private static void printList() {
		for (int idx = 0; idx < result.size(); idx++) {
			sb.append(result.get(idx));
		}
		sb.append("\n");
	}
}