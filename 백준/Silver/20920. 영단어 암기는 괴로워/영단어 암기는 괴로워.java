import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Word implements Comparable<Word> {
        String str;
        int cnt;

        public Word(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Word w) {
            if (this.cnt == w.cnt) {
                if (this.str.length() == w.str.length()) {
                    // 3. 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치
                    return this.str.compareTo(w.str);
                }

                // 2. 해당 단어의 길이가 길수록 앞에 배치
                return w.str.length() - this.str.length();
            }

            // 1. 자주 나오는 단어일수록 앞에 배치
            return w.cnt - this.cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine().trim());
        int wordCnt = Integer.parseInt(st.nextToken());
        int minLen = Integer.parseInt(st.nextToken());

        Map<String, Integer> dict = new HashMap<>();
        while (wordCnt-- > 0) {
            String word = br.readLine().trim();

            if (word.length() < minLen) {
                continue;
            }

            if (dict.containsKey(word)) {
                dict.put(word, dict.get(word) + 1);
            }
            else {
                dict.put(word, 1);
            }
        }

        List<Word> words = new ArrayList<>();
        for (String key : dict.keySet()) {
            words.add(new Word(key, dict.get(key)));
        }
        
        Collections.sort(words);

        for (Word word : words) {
            sb.append(word.str).append("\n");
        }

        System.out.println(sb);
    }
}