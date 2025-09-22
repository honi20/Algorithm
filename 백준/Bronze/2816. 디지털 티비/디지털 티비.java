import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int channelCnt;
	static String[] arr;
	static String result = "";

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		channelCnt = Integer.parseInt(br.readLine());
		arr = new String[channelCnt + 1];

		for (int idx = 1; idx <= channelCnt; idx++) {
			arr[idx] = br.readLine().trim();
		}

		// KBS1을 1번 채널로 만들기
		int pos1 = findChannel("KBS1");
		int cnt1 = pos1 - 1;
		for (int idx = 0; idx < cnt1; idx++) {
			result += "1";
		}

		for (int idx = pos1; idx > 1; idx--) {
			result += "4";
			swap(idx, idx - 1);
		}

		// 2번 채널로 이동
		result += "1";

		// KBS2를 2번 채널로 만들기
		int pos2 = findChannel("KBS2");
		int cnt2 = pos2 - 2;
		for (int idx = 0; idx < cnt2; idx++) {
			result += "1";
		}

		for (int idx = pos2; idx > 2; idx--) {
			result += "4";
			swap(idx, idx - 1);
		}

		System.out.println(result);
	}

	private static int findChannel(String channel) {
		for (int idx = 1; idx <= channelCnt; idx++) {
			if (arr[idx].equals(channel)) {
				return idx;
			}
		}

		return 0;
	}

	private static void swap(int pos1, int pos2) {
		String tmp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = tmp;
	}
}