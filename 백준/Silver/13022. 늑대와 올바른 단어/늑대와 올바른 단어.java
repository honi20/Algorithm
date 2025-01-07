import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final char[] LETTER = {'w', 'o', 'l', 'f'};
	
	static String str;
	static int result = 1;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		str = br.readLine().trim();
		
		int baseCnt = 0;
		int curLetter = 0;
		int curCnt = 0;
		for (int idx = 0; idx < str.length(); idx++) {
			char curChar = str.charAt(idx);
			
			// 현재 문자와 같은 경우
			if (curChar == LETTER[curLetter]) {
				++curCnt;
			}
			// 현재 문자와 다른 경우
			else {
				// 올바른 순서인지 확인 (마지막 문자)
				if (curLetter < 3 && LETTER[curLetter+1] != curChar) {
					result = 0;
					break;
				}
				
				if (curLetter == 3 && LETTER[0] != curChar) {
					result = 0;
					break;
				}
				
				// 개수가 정확한지 확인
				if (curLetter > 0 && baseCnt != curCnt) {
					result = 0;
					break;
				}
				
				// w -> o
				if (curLetter == 0) {
					baseCnt = curCnt;	
					++curLetter;
				}
				
				// f -> w
				else if (curLetter == 3) {
					baseCnt = 0;
					curLetter = 0;
				}
				else {
					++curLetter;
				}
				
				curCnt = 1;
			}
		}
		
		if (baseCnt != curCnt || curLetter != 3) {
			result = 0;
		}
		
		System.out.println(result);
	}
}