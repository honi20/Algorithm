import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAX = 55;

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static long[] arr;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		init();

		st = new StringTokenizer(br.readLine().trim());
		long start = Long.parseLong(st.nextToken());
		long end = Long.parseLong(st.nextToken());

		System.out.println(getBitCnt(end) - getBitCnt(start - 1));
	}

	private static long getBitCnt(long num) {
		long cnt = num & 1;

		for (int bit = MAX - 1; bit > 0; bit--) {
			if (isOneBit(num, bit)) {
				cnt += arr[bit - 1] + (num - (1L << bit) + 1);
				num -= (1L << bit);
			}
		}

		return cnt;
	}

	private static boolean isOneBit(long num, int bit) {
		return (num & (1L << bit)) != 0;
	}

	private static void init() {
		arr = new long[MAX];
		arr[0] = 1;

		for (int idx = 1; idx < MAX; idx++) {
			arr[idx] = 2L * arr[idx - 1] + (1L << idx);
		}
	}
}