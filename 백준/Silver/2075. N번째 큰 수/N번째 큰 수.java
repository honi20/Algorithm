import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int mapSize;
	static PriorityQueue<Long> queue;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		mapSize = Integer.parseInt(br.readLine().trim());

		queue = new PriorityQueue<>(Collections.reverseOrder());

		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());

			for (int col = 0; col < mapSize; col++) {
				queue.add(Long.parseLong(st.nextToken()));
			}
		}

		for (int idx = 0; idx < mapSize - 1; idx++) {
			queue.poll();
		}

		System.out.println(queue.peek());
	}
}