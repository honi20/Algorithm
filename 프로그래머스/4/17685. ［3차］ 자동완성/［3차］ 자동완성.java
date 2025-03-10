import java.util.*;

class Solution {
    static class Node {
        Node parent;
		Map<Character, Node> child;
		boolean endOfWord;
		
		public Node(Node parent) {
            this.parent = parent;
			this.child = new HashMap<>();
			this.endOfWord = false;
		}
	}
	
	static Node root;
    static List<Node> lastNode;
    
    public int solution(String[] words) {
        int answer = 0;
        
        root = new Node(null);
        lastNode = new ArrayList<>();
        for (String word : words) {
            insert(word);
        }
        
        for (int idx = 0; idx < words.length; idx++) {
            int skip = solve(lastNode.get(idx));
            answer += (words[idx].length() - skip);
        }
        
        return answer;
    }
    
    private int solve(Node curNode) {
        Node node = curNode;
        int cnt = 0;
        
        while (node.parent != null) {
            Node parent = node.parent;
            
            if (node.endOfWord && !node.child.isEmpty()) {
                break;
            }
            
            if (parent.endOfWord) {
                break;
            }
            else if (parent.child.size() > 1) {
                break;
            }
            else {
                cnt++;
                node = parent;
            }
        }
        
        return cnt;
    }
    
    private void insert(String str) {
		Node node = root;
		
		for (int idx = 0; idx < str.length(); idx++) {
			char c = str.charAt(idx);
			
			node.child.putIfAbsent(c, new Node(node));
			
			// 자식 노드로 이동
			node = node.child.get(c);
		}
		
		node.endOfWord = true;
        lastNode.add(node);
	}
}