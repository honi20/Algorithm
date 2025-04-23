import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		for (int idx = 0; idx < N; idx++) {
			list.add(Integer.parseInt(br.readLine().trim()));
		}
		
		Map<Integer, Integer> sushi = new HashMap<>();
		
		for (int idx = 0; idx < k; idx++) {
			int val = list.get(idx);
			if (sushi.containsKey(val)) {
				sushi.put(val, sushi.get(val) + 1);
			}
			else {
				sushi.put(val, 1);
			}
		}
		
		int maxCnt = sushi.size();
		if (!sushi.containsKey(c)) {
			++maxCnt;
		}
		
		int start = 0;
		int end = k - 1;
		while (start < N) {
			// 앞의 스시 제거
			int val = list.get(start);
			if (sushi.get(val) > 1) {
				sushi.put(val, sushi.get(val) - 1);
			}
			else {
				sushi.remove(val);
			}
			
			++start;
			
			// 다음 스시 추가
			++end;
			val = list.get(end % N);
			if (sushi.containsKey(val)) {
				sushi.put(val, sushi.get(val) + 1);
			}
			else {
				sushi.put(val, 1);
			}
			
			// 개수 갱신
			int cnt = sushi.size();
			if (!sushi.containsKey(c)) {
				cnt++;
			}
			
			maxCnt = Math.max(maxCnt, cnt);
		}
		
		System.out.println(maxCnt);
	}
}