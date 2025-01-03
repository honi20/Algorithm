import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static double H, W, N, M;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int rowCnt = (int)Math.ceil(H/(N+1));
		int colCnt = (int)Math.ceil(W/(M+1));
		System.out.println(rowCnt * colCnt);
	}
}
