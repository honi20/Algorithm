import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MAX = 100_000;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < N; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int maxCnt = 1;
		int[] cnt = new int[MAX + 1];
		++cnt[arr[start]];
		
		while (start < N) {
			if (cnt[arr[end]] > K) {
				--cnt[arr[start]];
				++start;
			}
			else {
				maxCnt = Math.max(maxCnt, end - start + 1);
				
				++end;
				if (end < N) {
					++cnt[arr[end]];
				}
				
				if (end == N) {
					break;
				}
			}
		}
		
		System.out.println(maxCnt);
	}
}
