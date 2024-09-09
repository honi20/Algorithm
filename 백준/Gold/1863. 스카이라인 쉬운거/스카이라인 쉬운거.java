import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point p) {
			return this.x - p.x;
		}
	}
	
	static int skylineCnt;
	static List<Point> skyline;
	static Stack<Integer> stack;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		skylineCnt = Integer.parseInt(br.readLine().trim());
		
		skyline = new ArrayList<>();
		stack = new Stack<>();
		for (int idx = 0; idx < skylineCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			skyline.add(new Point(x, y));
		}
		Collections.sort(skyline);
		
		for (int idx = 0; idx < skylineCnt; idx++) {
			Point point = skyline.get(idx);
			int curVal = point.y;
			
			if (stack.isEmpty()) {
				if (curVal > 0)
					stack.push(curVal);
			}
			else {
				int topVal = stack.peek();
				
				if (curVal > topVal) {
					if (curVal > 0)
						stack.push(curVal);
				}
				else if (curVal < topVal) {
					while (true) {
						int popVal = stack.pop();
						++result;
						
						if (stack.isEmpty()) {
							if (curVal > 0)
								stack.push(curVal);
							break;
						} else {
							if (stack.peek() == curVal) {
								break;
							}
							else if (stack.peek() < curVal) {
								if (curVal > 0)
									stack.push(curVal);
								break;
							}
						}
					}
				}
			}
		}
		result += stack.size();
		System.out.println(result);
	}
}