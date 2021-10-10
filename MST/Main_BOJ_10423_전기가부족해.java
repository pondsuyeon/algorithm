package algorithm.M10.D10;

import java.io.*;
import java.util.*;

public class Main_BOJ_10423_전기가부족해 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>();

		boolean[] visited = new boolean[N + 1];
		List<Node>[] adj = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int yny = Integer.parseInt(st.nextToken());
			pq.add(new Node(yny, 0));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[u].add(new Node(v, w));
			adj[v].add(new Node(u, w));
		}

		int ans = 0;

		int cnt = 0;
		while (!pq.isEmpty()) {
			Node v = pq.poll();

			if (visited[v.index])
				continue;
			visited[v.index] = true;

			ans += v.weight;

			if(++cnt == N) break;
			
			for (Node next : adj[v.index]) {
				if (visited[next.index])
					continue;

				pq.offer(next);
			}
		}

		System.out.println(ans);
	}

	static class Node implements Comparable<Node> {
		int index;
		int weight;

		Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
