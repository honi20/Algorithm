import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int SIZE = 9;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while (true) {
			String game = br.readLine().trim();

			if (game.equals("end")) {
				break;
			}

			// 개수 파악
			int cntX = 0, cntO = 0, cntN = 0;

			for (int idx = 0; idx < SIZE; idx++) {
				switch (game.charAt(idx)) {
					case 'X':
						++cntX;
						break;
					case 'O':
						++cntO;
						break;
					case '.':
						++cntN;
						break;
				}
			}

			if (cntX > 5 || cntO > 4) {
				sb.append("invalid\n");
				continue;
			}

			// X의 틱택톡 여부
			boolean isSuccessX = getTickTackTalk(game, 'X');

			// O의 틱택톡 여부
			boolean isSuccessO = getTickTackTalk(game, 'O');

			// 1) !isSuccessX  & isSuccessO => valid
			if (isSuccessX && !isSuccessO) {
				if (cntX == cntO + 1) {
					sb.append("valid\n");
				}
				else {
					sb.append("invalid\n");
				}
			}
			else if (!isSuccessX && isSuccessO) {
				if (cntX == cntO) {
					sb.append("valid\n");
				}
				else {
					sb.append("invalid\n");
				}
			}
			else if (isSuccessX && isSuccessO) {
				sb.append("invalid\n");
			}
			else {
				// 1) 게임판이 다 차지 않은 경우 => invalid
				if (cntN > 0) {
					sb.append("invalid\n");
				}
				// 2) 게임판이 다 찬 경우
				else {
					sb.append("valid\n");
				}
			}
		}

		System.out.println(sb);

	}

	private static boolean getTickTackTalk(String game, char player) {
		// 가로
		for (int idx = 0; idx < SIZE; idx += 3) {
			if (game.charAt(idx) == player && game.charAt(idx + 1) == player && game.charAt(idx + 2) == player) {
				return true;
			}
		}

		// 세로
		for (int idx = 0; idx < 3; idx++) {
			if (game.charAt(idx) == player && game.charAt(idx + 3) == player && game.charAt(idx + 6) == player) {
				return true;
			}
		}

		// 대각선
		if (game.charAt(0) == player && game.charAt(4) == player && game.charAt(8) == player) {
			return true;
		}
		if (game.charAt(2) == player && game.charAt(4) == player && game.charAt(6) == player) {
			return true;
		}

		return false;
	}
}