import java.util.*;

class Solution {
    static final int MAX = 1000_000;
    static final int ODD_EVEN = 0;
    static final int REVERSE_ODD_EVEN = 1;
    
    static class Node {
        int node;
        int parent;
        
        public Node(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
    
    static List<Integer>[] arr;
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        // edges를 기준으로 각 노드 별로 연결된 노드를 저장한다.
        initArr();
        
        for (int idx = 0; idx < edges.length; idx++) {
            int node1 = edges[idx][0];
            int node2 = edges[idx][1];
            
            arr[node1].add(node2);
            arr[node2].add(node1);
        }
        
        // 각 노드를 root로 했을 때의 트리의 타입을 구한다.
        for (int idx = 0; idx < nodes.length; idx++) {
            int result = bfs(nodes[idx]);
            
            if (result == ODD_EVEN) {
                // System.out.println(nodes[idx] + " -> ODD_EVEN!");
                ++answer[ODD_EVEN];
            }
            else if (result == REVERSE_ODD_EVEN) {
                // System.out.println(nodes[idx] + " -> REVERSE_ODD_EVEN!");
                ++answer[REVERSE_ODD_EVEN];
            }
        }
        
        return answer;
    }
    
    private int bfs(int root) {
        // root의 노드 타입을 구한다.
        int type = getType(root, arr[root].size());
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(root, -1));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            // 다음 자식 노드로 이동
            for (int nxt : arr[cur.node]) {
                // 부모 노드인 경우, 패스
                if (nxt == cur.parent) {
                    continue;
                }
                
                int nxtType = getType(nxt, arr[nxt].size() - 1);
                
                // 자식 노드의 타입이 다른 경우
                if (nxtType != type) {
                    return -1;
                }
                
                queue.offer(new Node(nxt, cur.node));
            }
        }
        
        return type;
    }
    
    private int getType(int root, int childCnt) {
        // 홀수 트리인 경우
        if ((root % 2 != 0) && (childCnt % 2 != 0)) {
            return ODD_EVEN;
        }
        // 짝수 트리인 경우
        else if ((root % 2 == 0) && (childCnt % 2 == 0)) {
            return ODD_EVEN;
        }
        // 역홀수 or 역짝수 트리인 경우
        else {
            return REVERSE_ODD_EVEN;
        }
    }
    
    private void initArr() {
        arr = new ArrayList[MAX + 1];
        
        for (int idx = 0; idx <= MAX; idx++) {
            arr[idx] = new ArrayList<>();
        }
    }
}