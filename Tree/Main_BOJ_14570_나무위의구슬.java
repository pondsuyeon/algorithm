package algorithm.M12.D06;

import java.io.*;
import java.util.*;

public class Main_BOJ_14570_나무위의구슬 {

	static Node[] node;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		node = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			node[i] = new Node(left, right);
		}
		long K = Long.parseLong(br.readLine());

		System.out.println(drop(1, K));
	}

	private static int drop(int v, long K) {

		if (node[v].left == -1 && node[v].right == -1)
			return v;

		if (K % 2 == 0) {
			if (node[v].right == -1)
				return drop(node[v].left, K);
			else
				return drop(node[v].right, K / 2);
		} else {
			if (node[v].left == -1)
				return drop(node[v].right, K);
			else
				return drop(node[v].left, K / 2 + 1);
		}
	}

	static class Node {
		int left, right;

		Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}
