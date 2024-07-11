/**
* 1. 난이도 의견 개수가 입력된다.
* 2. 난이도 의견이 입력된다.
* 3. 의견을 오름차순으로 정렬한다.
* 4. 앞 뒤로 15%의 사람을 제외한다.
* 5. 평균을 출력한다.
*/
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int opinionCnt;
	static List<Integer> opinion;
	static int total;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 난이도 의견 개수가 입력된다.
		opinionCnt = Integer.parseInt(br.readLine().trim());

		// 2. 난이도 의견이 입력된다.
		opinion = new ArrayList<>();
		total = 0;
		for (int idx = 0; idx < opinionCnt; idx++) {
			int value = Integer.parseInt(br.readLine().trim());
			opinion.add(value);
			total += value;
		}

		// 3. 의견을 오름차순으로 정렬한다.
		Collections.sort(opinion);

		// 4. 앞 뒤로 15%의 사람을 제외한다.
		int removal = (int) Math.round(opinionCnt * 15.0 / 100.0);
		for (int idx = 0; idx < removal; idx++) {
			total -= opinion.get(idx);
			total -= opinion.get(opinionCnt - 1 - idx);
		}
		
		// 5. 평균을 출력한다.
		System.out.println((int)Math.round((double)total / (opinionCnt - 2 * removal)));
	}
}