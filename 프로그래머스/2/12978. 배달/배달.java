import java.util.*;

class Solution {
    final int INF = 555_555;
    
    class Node implements Comparable<Node> {
        int town;
        int dist;
        
        public Node(int town, int dist) {
            this.town = town;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }
    
    int[][] roads;
    int[] minDist;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        initRoads(N, road);
        
        solve(N, K);
        
        for (int idx = 1; idx <= N; idx++) {
            if (minDist[idx] != INF) {
                ++answer;
            }
        }

        return answer;
    }
    
    private void solve(int N, int K) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        minDist = new int[N+1];
        Arrays.fill(minDist, INF);
        
        minDist[1] = 0;
        queue.offer(new Node(1, 0));
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int curTown = node.town;
            int curDist = node.dist;
            
            if (curDist > minDist[curTown]) {
                continue;
            }
            
            // 다음 마을로 이동
            for (int next = 1; next <= N; next++) {
                // 자기 자신인 경우, 패스
                if (next == curTown) {
                    continue;
                }
                
                // 연결된 도로가 없는 경우, 패스
                if (roads[curTown][next] == INF) {
                    continue;
                }
                
                // K 시간을 초과하는 경우, 패스
                if (curDist + roads[curTown][next] > K) {
                    continue;
                }
                
                if (curDist + roads[curTown][next] < minDist[next]) {
                    minDist[next] = curDist + roads[curTown][next];
                    queue.offer(new Node(next, minDist[next]));
                }
            }
        }
    }
    
    private void initRoads(int N, int[][] road) {
        roads = new int[N+1][N+1];
        for (int idx = 0; idx <= N; idx++) {
            Arrays.fill(roads[idx], INF);
        }
        
        for (int idx = 0; idx < road.length; idx++) {
            int town1 = road[idx][0];
            int town2 = road[idx][1];
            
            roads[town1][town2] = Math.min(roads[town1][town2], road[idx][2]);
            roads[town2][town1] = Math.min(roads[town2][town1], road[idx][2]);
        }
    }
}