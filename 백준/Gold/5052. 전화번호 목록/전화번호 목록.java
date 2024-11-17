import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int testCase;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		while (testCase-- > 0) {
			int N = Integer.parseInt(br.readLine().trim());
			String[] arr = new String[N];
			
			for (int idx = 0; idx < N; idx++) {
				arr[idx] = br.readLine().trim();
			}
			
			Arrays.sort(arr);
			
			if (checkConsist(N, arr)) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb);
		
	}
	
	private static boolean checkConsist(int N, String[] arr) {
        for (int i = 0; i < N - 1; i++) {
            if (arr[i + 1].startsWith(arr[i])) {
                return false;
            }
        }
 
        return true;
    }
}