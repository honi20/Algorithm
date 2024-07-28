import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int cardCnt;
	static int result;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		cardCnt = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int idx = 0; idx < cardCnt; idx++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
	
		result = 0;
		while (pq.size() > 1) {
			int tmp = pq.poll() + pq.poll();
			result += tmp;
			pq.offer(tmp);
		}
		
		System.out.println(result);
	}
}
