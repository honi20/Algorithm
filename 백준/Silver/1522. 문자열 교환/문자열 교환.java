import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String  str = br.readLine().trim();
		
		int len = str.length();
		int cntB = 0;
		for (int idx = 0; idx < len; idx++) {
			if (str.charAt(idx) == 'b') {
				++cntB;
			}
		}
		
		int start = 0;
		int end = cntB - 1;
		int curB = 0;
		int maxCnt = 0;
		
		for (int idx = start; idx <= end; idx++) {
			if (str.charAt(idx) == 'b') {
				++curB;
			}
			
			maxCnt = curB;
		}
		
		while (start < len) {
			if (str.charAt(start) == 'b') {
				--curB; 
			}
			
			++start;
			++end;
			
			if (str.charAt(end % len) == 'b') {
				++curB;
			}
			
			maxCnt = Math.max(maxCnt, curB);
		}
		
		System.out.println(cntB - maxCnt);
	}
}
