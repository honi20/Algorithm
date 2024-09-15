import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public void insert(int n) {
            if (n < this.data) {
                if (this.left == null)
                    this.left = new Node(n);
                else this.left.insert(n);
            } else {
                if (this.right == null)
                    this.right = new Node(n);
                else this.right.insert(n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        Node root = new Node(Integer.parseInt(br.readLine()));
        String input;
        
        while (true) {
            input = br.readLine();
            if (input == null || input.equals(""))
                break;
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);
    }

    static void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }
}