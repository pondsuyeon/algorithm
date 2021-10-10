package algorithm.M10.D10;

import java.io.*;
import java.util.*;

public class Main_BOJ_10423_전기가부족해_2 {
	static Set<Integer> YNY = new HashSet<>();
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		PriorityQueue<Edge> e = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int yny = Integer.parseInt(st.nextToken());
			YNY.add(yny);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			e.add(new Edge(u, v, w));
		}

		int ans = 0;

		while (!e.isEmpty()) {
			Edge eg = e.poll();

			if (union(eg.u, eg.v)) {
				ans += eg.w;
			}
		}

		System.out.println(ans);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return false;

		if (YNY.contains(x) && YNY.contains(y))
			return false;
		else if (YNY.contains(x)) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
		return true;
	}

	static int find(int n) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n]);
	}

	static class Edge implements Comparable<Edge> {
		int u, v, w;

		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}
