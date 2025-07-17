import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Node implements Comparable<Node> {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }

    static int nodeCnt;
    static int pathCnt;
    static List<Node>[] list;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        pathCnt = Integer.parseInt(st.nextToken());

        list = new List[nodeCnt + 1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            list[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < pathCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[node1].add(new Node(node2, weight));
            list[node2].add(new Node(node1, weight));
        }

        System.out.println(solve(1, nodeCnt));
    }

    private static int solve(int source, int dest) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] minDist = new int[nodeCnt + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[source] = 0;
        queue.offer(new Node(source, minDist[source]));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNode = cur.node;
            int curWeight = cur.weight;

            // 목적지인 경우, 종료
            if (curNode == nodeCnt) {
                return curWeight;
            }

            // 인접 헛간으로 계속 이동
            List<Node> nexts = list[curNode];

            if (nexts == null) {
                continue;
            }

            for (Node next : nexts) {
                if (curWeight + next.weight < minDist[next.node]) {
                    minDist[next.node] = curWeight + next.weight;
                    queue.offer(new Node(next.node, minDist[next.node]));
                }
            }
        }

        return 0;
    }
}