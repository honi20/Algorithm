import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int PLUS = 0;
	static final int MINUS = 1;

	static class Student implements Comparable<Student> {
		int index;
		int skill;

		public Student(int index, int skill) {
			this.index = index;
			this.skill = skill;
		}

		@Override
		public int compareTo(Student s) {
			return this.skill - s.skill;
		}
	}

	static int studentCnt;
	static int algorithmCnt;
	static int limit;
	static int result = 0;

	static List<Student> skills;
	static boolean[][] student;
	static int[] algorithm;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		studentCnt = Integer.parseInt(st.nextToken());
		algorithmCnt = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());

		skills = new ArrayList<>();
		student = new boolean[studentCnt][algorithmCnt + 1];
		algorithm = new int[algorithmCnt + 1];

		for (int idx = 0; idx < studentCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int cnt = Integer.parseInt(st.nextToken());
			int skill = Integer.parseInt(st.nextToken());

			skills.add(new Student(idx, skill));

			Arrays.fill(student[idx], false);
			st = new StringTokenizer(br.readLine().trim());
			while (cnt-- > 0) {
				student[idx][Integer.parseInt(st.nextToken())] = true;
			}
		}

		// 학생의 실력 순으로 오름차순 정렬
		Collections.sort(skills);

		solve();

		System.out.println(result);
	}

	private static void solve() {
		initAlgorithm();

		int start = 0;
		int end = 0;

		while (start <= end) {
			// 종료 조건
			if (end == studentCnt) {
				break;
			}

			Student minSt = skills.get(start);
			Student maxSt = skills.get(end);

			// 실력 차이가 D를 초과하는 경우
			if (maxSt.skill - minSt.skill > limit) {
				updateEfficiency(start, MINUS);
				++start;
			}
			// 실력 차이가 D 이하인 경우
			else {
				// 효율성을 계산한다.
				result = Math.max(result, getEfficiency(start, end));

				++end;

				if (end < studentCnt) {
					updateEfficiency(end, PLUS);
				}
			}
		}
	}

	private static void updateEfficiency(int idx, int type) {
		int studentIdx = skills.get(idx).index;

		for (int algo = 1; algo <= algorithmCnt; algo++) {
			if (student[studentIdx][algo]) {
				// 값을 추가하는 경우
				if (type == PLUS) {
					++algorithm[algo];
				}
				// 값을 빼는 경우
				else {
					--algorithm[algo];

				}
			}
		}
	}

	private static int getEfficiency(int start, int end) {
		int cnt = 0;
		int groupCnt = end - start + 1;

		for (int idx = 1; idx <= algorithmCnt; idx++) {
			if (0 < algorithm[idx] && algorithm[idx] < groupCnt) {
				++cnt;
			}
		}

		return cnt * groupCnt;
	}

	private static void initAlgorithm() {
		Arrays.fill(algorithm, 0);

		int studentIdx = skills.get(0).index;
		for (int algo = 1; algo <= algorithmCnt; algo++) {
			if (student[studentIdx][algo]) {
				++algorithm[algo];
			}
		}
	}
}
