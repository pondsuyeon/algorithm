package algorithm.M11.D08;

import java.io.*;
import java.util.*;

public class Main_BOJ_13905_세부 {

	static int N, M;
	static int s, e;

	static PriorityQueue<Edge> edges;
	static int[] parent;

	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		edges = new PriorityQueue<>();
		parent = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int h1 = Integer.parseInt(st.nextToken());
			int h2 = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			edges.add(new Edge(h1, h2, k));
		}

		kruskal();

		System.out.println(ans);
	}

	private static void kruskal() {

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		while (!edges.isEmpty()) {

			Edge edge = edges.poll();

			int u = edge.u;
			int v = edge.v;
			int w = edge.w;

			if (union(u, v)) {
				if (find(s) == find(e)) {
					ans = w;
					break;
				}
			}
		}
	}

	private static boolean union(int u, int v) {
		u = find(u);
		v = find(v);

		if (u == v)
			return false;

		if (u < v)
			parent[v] = u;
		else
			parent[u] = v;
		return true;
	}

	private static int find(int n) {
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
			return -this.w + o.w;
		}
	}
}
