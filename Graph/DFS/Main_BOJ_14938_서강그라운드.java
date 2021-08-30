package algorithm.D0830;

import java.io.*;
import java.util.*;

public class Main_BOJ_14938_서강그라운드 {
	static int N, M, R;
	static int[] items;
	static LinkedList<Node>[] adj;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		adj = new LinkedList[N + 1];
		items = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new LinkedList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[u].offer(new Node(v, w));
			adj[v].offer(new Node(u, w));
		}

		int max = -1;

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			max = Math.max(max, getItems(i, 0));
		}

		System.out.println(max);
	}

	private static int getItems(int v, int length) {
		int res = 0;
		if (!visited[v]) {
			visited[v] = true;
			res = items[v];
		}

		for (Node next : adj[v]) {
			if (length + next.weight <= M) {
				res += getItems(next.index, length + next.weight);
			}
		}
		return res;
	}

	static class Node {
		int index;
		int weight;

		Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
	}
}
