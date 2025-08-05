import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int[][] LED = {
			{1,1,1,0,1,1,1},
			{0,0,1,0,0,1,0},
			{1,0,1,1,1,0,1},
			{1,0,1,1,0,1,1},
			{0,1,1,1,0,1,0},
			{1,1,0,1,0,1,1},
			{1,1,0,1,1,1,1},
			{1,0,1,0,0,1,0},
			{1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1},
	};
	static final int MAX = 9;
	static final int LED_MAX = 7;

	static int[][] toggle;
	static int floorCnt;
	static int display;
	static int limit;
	static String curFloor;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		initToggle();

		st = new StringTokenizer(br.readLine().trim());
		floorCnt = Integer.parseInt(st.nextToken());
		display = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		curFloor = st.nextToken();

		// 디스플레이 크기만큼 앞에 0 붙이기
		int len = curFloor.length();
		for (int idx = 0; idx < display - len; idx++) {
			curFloor = "0" + curFloor;
		}

		int result = 0;
		for (int floor = 1; floor <= floorCnt; floor++) {
			if (isAvailTo(floor)) {
//				System.out.println(floor);
				++result;
			}
		}

		System.out.println(result);
	}

	private static boolean isAvailTo(int floor) {
		int cnt = 0;

		for (int idx = curFloor.length() - 1; idx >= 0; idx--) {
			int base = getNum(curFloor, idx);
			int num = floor % 10;

			floor /= 10;

			cnt += toggle[base][num];
			if (cnt > limit) {
				break;
			}
		}

		if (cnt == 0 || cnt > limit) {
			return false;
		}

		return true;
	}

	private static int getNum(String floor, int index) {
		return floor.charAt(index) - '0';
	}

	private static void initToggle() {
		toggle = new int[MAX+1][MAX+1];

		for (int cur = 0; cur < MAX; cur++) {
			for (int cmp = cur + 1; cmp <= MAX; cmp++) {
				toggle[cur][cmp] = toggle[cmp][cur] = getToggleCnt(cur, cmp);
			}
		}
	}

	private static int getToggleCnt(int num1, int num2) {
		int cnt = 0;

		for (int idx = 0; idx < LED_MAX; idx++) {
			if (LED[num1][idx] != LED[num2][idx]) {
				++cnt;
			}
		}

		return cnt;
	}
}