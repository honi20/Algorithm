import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int rowSize, colSize;
    static int trampoline;
    static int starCnt;
    static List<Point> stars;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        trampoline = Integer.parseInt(st.nextToken());
        starCnt = Integer.parseInt(st.nextToken());

        stars = new ArrayList<>();
        for (int idx = 0; idx < starCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            stars.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int maxCnt = 0;
        for (Point s1 : stars) {
            for (Point s2 : stars) {

                int startR = Math.min(s1.row, s2.row);
                int endR = (startR + trampoline) > rowSize ? rowSize : startR + trampoline;
                int startC = Math.min(s1.col, s2.col);
                int endC = (startC + trampoline) > colSize ? colSize : startC + trampoline;

                int cnt = 0;
                for (Point star : stars) {
                    if (startR <= star.row && star.row <= endR) {
                        if (startC <= star.col && star.col <= endC) {
                            ++cnt;
                        }
                    }
                }

                maxCnt = Math.max(maxCnt, cnt);
            }
        }

        System.out.println(starCnt - maxCnt);
    }

    private static boolean isAvail(Point s1, Point s2) {
        int rowDist = Math.abs(s1.row - s2.row);
        int colDist = Math.abs(s1.col - s2.col);

        if (rowDist > rowSize || colDist > colSize) {
            return false;
        }

        return true;
    }
}
