import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static boolean[] preLight;
	static boolean[] curLight;
	static boolean[] lastLight;
	static int result = -1;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine().trim());
		
		preLight = new boolean[N];
		lastLight = new boolean[N];
		
		String str = br.readLine().trim();
		for (int idx = 0; idx < str.length(); idx++) {
			preLight[idx] = (str.charAt(idx) == '0' ? false : true);
		}
		
		str = br.readLine().trim();
		for (int idx = 0; idx < str.length(); idx++) {
			lastLight[idx] = (str.charAt(idx) == '0' ? false : true);
		}
		
		int cnt = 0;
		
		// 앞 전구 2개를 바꾸는 경우
		initCurLight();
		curLight[0] = !curLight[0];
		curLight[1] = !curLight[1];
		
		cnt = switchLight();
		if (cnt != -1) {
			if (result == -1)
				result = cnt + 1;
			else
				result = Math.min(cnt + 1, result);
		}
			
		
		// 앞 전구 2개를 바꾸지 않는 경우
		cnt = 0;
		initCurLight();
		cnt = switchLight();
		if (cnt != -1) {
			if (result == -1)
				result = cnt;
			else
				result = Math.min(cnt, result);
		}
		
		System.out.println(result);
	}
	
	private static int switchLight() {
		int cnt = 0;
		for (int idx = 0; idx < N-2; idx++) {
			if (curLight[idx] != lastLight[idx]) {
				curLight[idx] = !curLight[idx];
				curLight[idx+1] = !curLight[idx+1];
				curLight[idx+2] = !curLight[idx+2];
				++cnt;
			}
		}
		
		if (curLight[N-2] != lastLight[N-2]) {
			curLight[N-2] = !curLight[N-2];
			curLight[N-1] = !curLight[N-1];
			++cnt;
		}
		
		if (curLight[N-1] != lastLight[N-1]) {
			cnt = -1;
		}
		
		return cnt;
	}
	
	private static void initCurLight() {
		curLight = new boolean[N];
		for (int idx = 0; idx < N; idx++) {
			curLight[idx] = preLight[idx];
		}
	}
}