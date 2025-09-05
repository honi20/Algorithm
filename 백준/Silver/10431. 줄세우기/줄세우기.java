import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int MAX = 20;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		List<Integer> student = new ArrayList<>();

		int testCase = Integer.parseInt(br.readLine().trim());
		while (testCase-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			int num = Integer.parseInt(st.nextToken());
			int cnt = 0;

			student.clear();
			for (int idx = 0; idx < MAX; idx++) {
				int height = Integer.parseInt(st.nextToken());

				int point = Collections.binarySearch(student, height);
				if (point < 0) {
					point = (point + 1) * (-1);
				}

				cnt += (idx - point);
				student.add(height);
				Collections.sort(student);
			}

			sb.append(num).append(" ").append(cnt).append("\n");
		}

		System.out.println(sb);
	}
}