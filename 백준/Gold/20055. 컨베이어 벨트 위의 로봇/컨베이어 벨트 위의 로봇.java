import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N, K, total;
	static int[] arr;
	static boolean[] robot;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		total = 2 * N;
		arr = new int[total];
		robot = new boolean[total];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < total; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}

		int up = 0;
		int down = N - 1;
		int turn = 1;

		while (true) {
			// 1. 벨트가 한 칸 회전
			up = (up - 1 + total) % total;
			down = (down - 1 + total) % total;

			// 내리는 위치의 로봇의 경우, 내림
			if (robot[down]) {
				robot[down] = false;
			}

			// 2. 이동할 수 있는 로봇을 이동
			for (int idx = 0; idx < N - 1; idx++) {
				int cur = (down - idx + total) % total;
				int pre = (cur - 1 + total) % total;
				
				if (robot[pre] && !robot[cur] && arr[cur] > 0) {
					--arr[cur];
					robot[pre] = false;
					
					if (cur != down) {
						robot[cur] = true;
					}
				}
			}

			// 3. 올리는 위치에 로봇을 올릴 수 있다면 올림
			if (arr[up] > 0) {
				--arr[up];
				robot[up] = true;
			}

			// 4. 0이 K개 이상 있는 경우, 종료
			if (isOverK()) {
				break;
			}
			
			++turn;
		}

		System.out.println(turn);
	}

	private static boolean isOverK() {
		int cnt = 0;
		for (int idx = 0; idx < total; idx++) {
			if (arr[idx] <= 0) {
				++cnt;
				if (cnt >= K) {
					return true;
				}
			}
		}

		return false;
	}
}