import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Road {
		int cross;
		int cost;
		int limit;

		public Road(int cross, int cost, int limit) {
			this.cross = cross;
			this.cost = cost;
			this.limit = limit;
		}
	}
	
	static class Point implements Comparable<Point> {
		int cross;
		int cost;
		
		public Point(int cross, int cost) {
			this.cross = cross;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Point p) {
			return this.cost - p.cost;
		}
	}

	static int crossCnt;
	static int roadCnt;
	static int budget;
	static List<Road>[] roads;

	static int maxParticapant;
	static PriorityQueue<Point> queue;
	static int[] minCost;

	public static void main(String[] args) throws Exception {
		input();

		queue = new PriorityQueue<>();
		minCost = new int[crossCnt+1];
		System.out.println(solve());
	}

	private static int solve() {
		// 마라톤 참가 인원수를 이분 탐색을 통해 구한다.
		int start = 0;
		int end = maxParticapant;
		int result = -1;
		
		while (start < end) {
			int mid = (start + end) / 2;

			if (isPossible(mid)) {
				result = mid;
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		return result;
	}

	private static boolean isPossible(int participantCnt) {
		queue.clear();
		Arrays.fill(minCost, -1);
		
		queue.add(new Point(1, 0));
		minCost[1] = 0;
		
		while (!queue.isEmpty()) {
			Point point = queue.poll();
			int curCross = point.cross;
			int curCost = point.cost;
			
			// 마지막 교차로인 경우, 예산 안에서 가능한 마라톤 코스
			if (curCross == crossCnt) {
				return true;
			}
			
			if (curCost > minCost[curCross]) {
				continue;
			}
			
			// 인접 교차로를 탐색한다.
			List<Road> list = roads[curCross];
			for (int idx = 0; idx < list.size(); idx++) {
				Road road = list.get(idx);
				
				// 인접 교차로에 가는데 필요한 비용을 파악한다.
				int nextCost = getCost(participantCnt, road);
				
				// 예산을 초과하는 경로인 경우, 패스 
				if (curCost + nextCost > budget) {
					continue;
				}
				
				// 이미 더 적은 비용으로 가는 경로가 있는 경우, 패스
				if (minCost[road.cross] == -1 || curCost + nextCost < minCost[road.cross]) {
					minCost[road.cross] = curCost + nextCost;
					queue.add(new Point(road.cross, minCost[road.cross]));
				}
			}
		}
		
		return false;
	}

	private static int getCost(int participantCnt, Road road) {
		if (participantCnt <= road.limit) {
			return 0;
		}
		
		return road.cost * (int)Math.pow((participantCnt-road.limit), 2);
	}
	
	private static void input() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		crossCnt = Integer.parseInt(st.nextToken());
		roadCnt = Integer.parseInt(st.nextToken());
		budget = Integer.parseInt(st.nextToken());

		roads = new ArrayList[crossCnt + 1];

		int minCost = Integer.MAX_VALUE;
		int maxLimit = 0;

		for (int idx = 0; idx < roadCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int cross1 = Integer.parseInt(st.nextToken());
			int cross2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());

			if (roads[cross1] == null)
				roads[cross1] = new ArrayList<>();

			if (roads[cross2] == null)
				roads[cross2] = new ArrayList<>();

			roads[cross1].add(new Road(cross2, cost, limit));
			roads[cross2].add(new Road(cross1, cost, limit));

			minCost = Math.min(minCost, cost);
			maxLimit = Math.max(maxLimit, limit);
		}

		maxParticapant = (int) Math.sqrt(budget / minCost) + 1 + maxLimit;
	}
}
