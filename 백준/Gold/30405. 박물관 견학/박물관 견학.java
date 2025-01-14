import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static int M;
	static List<Integer> exits;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		exits = new ArrayList<>();
		for (int cat = 0; cat < N; cat++) {
			String[] arr = br.readLine().trim().split(" ");
			exits.add(Integer.parseInt(arr[1]));
			exits.add(Integer.parseInt(arr[Integer.parseInt(arr[0])]));
		}
		
		Collections.sort(exits);
		
		System.out.println(exits.get(N-1));
	}
}