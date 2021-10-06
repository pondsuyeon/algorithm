package algorithm.M10.D06;

import java.io.*;
import java.util.*;

public class Main_BOJ_1991_트리순회 {

	static int N;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		nodes = new Node[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int now = st.nextToken().charAt(0) - 'A';
			int left = st.nextToken().charAt(0);
			int right = st.nextToken().charAt(0);

			if (left == '.')
				left = -1;
			else
				left -= 'A';

			if (right == '.')
				right = -1;
			else
				right -= 'A';

			nodes[now] = new Node(left, right);
		}

		preorder(0, sb);
		sb.append("\n");
		inorder(0, sb);
		sb.append("\n");
		postorder(0, sb);

		System.out.println(sb.toString());
	}

	private static void preorder(int v, StringBuilder sb) {
		sb.append((char)(v + 'A'));
		if (nodes[v].left != -1) {
			preorder(nodes[v].left, sb);
		}
		if (nodes[v].right != -1) {
			preorder(nodes[v].right, sb);
		}

	}

	private static void inorder(int v, StringBuilder sb) {
		if (nodes[v].left != -1) {
			inorder(nodes[v].left, sb);
		}
		sb.append((char)(v + 'A'));
		if (nodes[v].right != -1) {
			inorder(nodes[v].right, sb);
		}
	}

	private static void postorder(int v, StringBuilder sb) {
		if (nodes[v].left != -1) {
			postorder(nodes[v].left, sb);
		}
		if (nodes[v].right != -1) {
			postorder(nodes[v].right, sb);
		}
		sb.append((char)(v + 'A'));
	}

	static class Node {
		int left;
		int right;

		Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}
