import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine().trim());
        int volunteerCnt = Integer.parseInt(st.nextToken());
        int playerCnt = getGameCnt(st.nextToken());

        Set<String> volunteers = new HashSet<>();
        while (volunteerCnt-- > 0) {
            String name = br.readLine().trim();

            volunteers.add(name);
        }

        System.out.println(volunteers.size() / (playerCnt - 1));
    }

    private static int getGameCnt(String type) {
        switch (type) {
            case "Y":
                return 2;   
            case "F":
                return 3;
            case "O":
                return 4;
        }

        return 0;
    }
}