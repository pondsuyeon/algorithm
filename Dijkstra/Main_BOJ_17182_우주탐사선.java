package algorithm.M11.D06;

import java.io.*;
import java.util.*;

public class Main_BOJ_17182_우주탐사선 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] times = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				times[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(explore(N, K, times));
	}

	private static int explore(int N, int K, int[][] times) {

		PriorityQueue<Node> q = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][1 << N];

		q.offer(new Node(K, 1 << K, 0));

		while (!q.isEmpty()) {
			Node n = q.poll();

			if (n.f == (1 << N) - 1)
				return n.t;

			if (visited[n.i][n.f])
				continue;
			visited[n.i][n.f] = true;
			for (int i = 0; i < N; i++) {
				if (n.i == i)
					continue;

				q.offer(new Node(i, n.f | 1 << i, n.t + times[n.i][i]));
			}

		}
		return -1;
	}

	static class Node implements Comparable<Node> {
		int i, f, t;

		Node(int i, int f, int t) {
			this.i = i;
			this.f = f;
			this.t = t;
		}

		@Override
		public int compareTo(Node o) {
			return this.t - o.t;
		}
	}
}
