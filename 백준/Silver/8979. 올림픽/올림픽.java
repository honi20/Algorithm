import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Country implements Comparable<Country> {
        int num;
        int gold;
        int silver;
        int copper;

        public Country(int num, int gold, int silver, int copper) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.copper = copper;
        }

        @Override
        public int compareTo(Country c) {
            if (this.gold == c.gold) {
                if (this.silver == c.silver) {
                    return c.copper - this.copper;
                }

                return c.silver - this.silver;
            }

            return c.gold - this.gold;
        }

        public boolean isSame(Country c) {
            return (this.gold == c.gold) && (this.silver == c.silver) && (this.copper == c.copper);
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        List<Country> countries = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        int countryCnt = Integer.parseInt(st.nextToken());
        int resultNum = Integer.parseInt(st.nextToken());

        for (int idx = 0; idx < countryCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int copper = Integer.parseInt(st.nextToken());

            countries.add(new Country(num, gold, silver, copper));
        }

        Collections.sort(countries);

        int cnt = 0;
        int curScore = 1;
        for (int idx = 0; idx < countryCnt; idx++) {
            Country country = countries.get(idx);
            
            if (idx == 0) {
                ++cnt;
            }
            else {
                // 이전 나라와 점수가 같은 경우
                if (country.isSame(countries.get(idx-1))) {
                    ++cnt;
                }
                else {
                    curScore += cnt;
                    cnt = 0;
                }
            }

            if (country.num == resultNum) {
                break;
            }
        }

        System.out.println(curScore);
    }
}
