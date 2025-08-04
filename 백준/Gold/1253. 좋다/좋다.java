import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int listSize = Integer.parseInt(br.readLine().trim());

		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine().trim());
		while (st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(list);

		int result = 0;
		if (listSize >= 3) {

			for (int idx = 0; idx < listSize; idx++) {
				int cur = list.get(idx);
				int sIdx = 0;
				int eIdx = listSize - 1;

				while (sIdx < eIdx) {
					if (idx == sIdx) {
						++sIdx;
					}
					else if (idx == eIdx) {
						--eIdx;
					}

					if  (sIdx >= eIdx) {
						break;
					}

					int s = list.get(sIdx);
					int e = list.get(eIdx);

					if (s + e == cur) {
						++result;
						break;
					}
					else if (s + e < cur) {
						++sIdx;
					}
					else if (s + e > cur) {
						--eIdx;
					}
				}
			}
		}

		System.out.println(result);
	}
}
