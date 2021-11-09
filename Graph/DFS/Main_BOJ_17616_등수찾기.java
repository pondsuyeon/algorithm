package algorithm.M11.D09;

import java.io.*;
import java.util.*;

public class Main_BOJ_17616_등수찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<Integer>[] down = new ArrayList[N + 1];
		List<Integer>[] up = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			down[i] = new ArrayList<>();
			up[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			down[A].add(B);
			up[B].add(A);
		}

		int U = 1 + DFS(X, up, new boolean[N + 1]) - 1;
		int V = N - DFS(X, down, new boolean[N + 1]) + 1;

		System.out.println(U + " " + V);
	}

	private static int DFS(int x, List<Integer>[] adj, boolean[] visited) {

		visited[x] = true;

		int res = 1;

		for (int next : adj[x]) {
			if (visited[next])
				continue;

			res += DFS(next, adj, visited);
		}

		return res;
	}

}
