import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static String str1;
	static String str2;
	static Stack<Character> s;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		str1 = br.readLine().trim();
		str2 = br.readLine().trim();
		s = new Stack<>();
		
		for (int idx = 0; idx < str1.length(); idx++) {
			s.push(str1.charAt(idx));
			
			if (s.size() >= str2.length()) {
				boolean flag = true;
				
				for (int k = 0; k < str2.length(); k++) {
					if (s.get(s.size() - str2.length() + k) != (str2.charAt(k))) {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					for (int k = 0; k < str2.length(); k++) {
						s.pop();
					}
				}
			}
		}
		
		for (Character c : s) {
			sb.append(c);
		}
		
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}
}