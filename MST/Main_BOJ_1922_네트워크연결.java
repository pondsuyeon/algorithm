package algorithm.M12.D18;

import java.io.*;
import java.util.*;

public class Main_BOJ_1922_네트워크연결 {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		init();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == b)
				continue;

			pq.add(new Edge(a, b, c));
		}

		int E = 0;
		int W = 0;
		while (!pq.isEmpty()) {

			Edge e = pq.poll();

			if (union(e.u, e.v)) {
				E++;
				W += e.w;
			}

			if (E == N - 1)
				break;
		}

		System.out.println(W);
	}

	static void init() {
		parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return false;

		parent[y] = x;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int u, v, w;

		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge e) {
			return this.w - e.w;
		}
	}
}
