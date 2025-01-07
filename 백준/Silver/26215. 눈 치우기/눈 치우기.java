import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX = 1440;
	
	static int homeCnt;
	static List<Integer> snow;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		homeCnt = Integer.parseInt(br.readLine().trim());
		
		snow = new ArrayList<>();
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < homeCnt; idx++) {
			snow.add(Integer.parseInt(st.nextToken()));
		}
		
		while (true) {
			Collections.sort(snow);
			
			if (snow.get(homeCnt - 1) <= 0) {
				break;
			}
			
			snow.set(homeCnt-1, snow.get(homeCnt-1) - 1);
			
			if (homeCnt > 1 && snow.get(homeCnt-2) > 0) {
				snow.set(homeCnt-2, snow.get(homeCnt-2) - 1);
			}
			
			if (++result > MAX) {
				break;
			}
		}
		
		System.out.println((result > MAX) ? -1 : result);
	}
}
