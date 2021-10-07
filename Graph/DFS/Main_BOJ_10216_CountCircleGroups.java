package algorithm.M10.D07;

import java.io.*;
import java.util.*;

public class Main_BOJ_10216_CountCircleGroups {

	static int N;

	static Node[] nodes;
	static LinkedList<Integer>[] adj;

	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {

			N = Integer.parseInt(br.readLine());

			nodes = new Node[N];

			adj = new LinkedList[N];

			for (int i = 0; i < N; i++) {
				adj[i] = new LinkedList<>();
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());

				nodes[i] = new Node(x, y, r);
			}

			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					if (nodes[i].isSameGroupWith(nodes[j])) {
						adj[i].add(j);
						adj[j].add(i);
					}
				}
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i])
					continue;
				DFS(i);
				ans++;
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}

	static void DFS(int v) {
		visited[v] = true;

		for (int next : adj[v]) {
			if (visited[next])
				continue;
			DFS(next);
		}
	}

	static class Node {
		int x, y, r;

		Node(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}

		public boolean isSameGroupWith(Node node) {
			return (this.x - node.x) * (this.x - node.x) + (this.y - node.y) * (this.y - node.y) <= (this.r + node.r)
					* (this.r + node.r);
		}
	}
}
