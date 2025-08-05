import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int wordCnt = Integer.parseInt(br.readLine().trim());

		List<String> words = new ArrayList<>();
		for (int idx = 0; idx < wordCnt; idx++) {
			words.add(br.readLine().trim());
		}

		int resultLen = 0;
		String resultA = null, resultB = null;

		for (int cur = 0; cur < wordCnt - 1; cur++) {
			String curWord = words.get(cur);

			for (int cmp = cur + 1; cmp < wordCnt; cmp++) {
				String cmpWord = words.get(cmp);

				// 두 단어가 같은 경우, 패스
				if (curWord.equals(cmpWord)) {
					continue;
				}

				int len = getLen(curWord, cmpWord);

				// 접두사가 긴 경우
				if (len > resultLen) {
					resultLen = len;
					resultA = curWord;
					resultB = cmpWord;
				}
			}
		}

		System.out.println(resultA + "\n" + resultB);
	}

	private static int getLen(String word1, String word2) {
		int len = 0;

		while (true) {
			if (word1.charAt(len) != word2.charAt(len)) {
				break;
			}

			++len;
			if (len == word1.length() || len == word2.length()) {
				break;
			}
		}

		return len;
	}
}