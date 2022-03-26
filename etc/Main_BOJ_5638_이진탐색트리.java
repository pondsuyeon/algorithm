import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main_BOJ_5638_이진탐색트리 {
    static List<Integer> preorder = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {

            String input = br.readLine();

            if (input == null || input.equals("")) {
                break;
            }

            preorder.add(Integer.parseInt(input));
        }

        N = preorder.size();

        Node root = new Node(preorder.get(0));
        for (int i = 1; i < N; i++) {
            setTree(root, preorder.get(i));
        }

        postorder(root, sb);

        System.out.println(sb);
    }

    private static void setTree(Node node, int number) {

        if (number < node.number) {
            if (node.left == null) {
                node.left = new Node(number);
            } else {
                setTree(node.left, number);
            }
        } else if (number > node.number) {
            if (node.right == null) {
                node.right = new Node(number);
            } else {
                setTree(node.right, number);
            }
        }
    }

    private static void postorder(Node node, StringBuilder sb) {

        if (node == null) return;

        postorder(node.left, sb);
        postorder(node.right, sb);
        sb.append(node.number).append("\n");
    }

    static class Node {
        int number;
        Node left, right;

        Node(int number) {
            this.number = number;
        }
    }
}
