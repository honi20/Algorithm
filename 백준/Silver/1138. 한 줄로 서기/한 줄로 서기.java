import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int studentCnt;
	static int[] students;
	static boolean[] isSelected;
	static int[] result;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		studentCnt = Integer.parseInt(br.readLine().trim());
		students = new int[studentCnt+1];

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= studentCnt; idx++) {
			students[idx] = Integer.parseInt(st.nextToken());
		}

		isSelected = new boolean[studentCnt+1];
		result = new int[studentCnt+1];
		solve(0);

		System.out.println(sb);
	}

	private static void solve(int elementIdx) {
		// 모든 칸을 선택한 경우, 확인
		if (elementIdx == studentCnt) {
			for (int idx = 1; idx <= studentCnt; idx++) {
				sb.append(result[idx]).append(" ");
			}

			return;
		}

		// 다음 칸에 세울 학생 선택
		for (int idx = 1; idx <= studentCnt; idx++) {
			if (!isSelected[idx]) {
				// 왼쪽에 선 사람 중 큰 사람의 수가 같은 경우, 세운다.
				if (isAvail(elementIdx, idx, students[idx])) {
					isSelected[idx] = true;
					result[elementIdx + 1] = idx;

					solve(elementIdx + 1);

					isSelected[idx] = false;
					result[elementIdx + 1] = 0;
				}
			}
		}
	}

	private static boolean isAvail(int elementIdx, int height, int higherCnt) {
		int cnt = 0;
		for (int idx = 1; idx <= elementIdx; idx++) {
			if (result[idx] > height) {
				++cnt;
			}
		}

		return cnt == higherCnt;
	}
}