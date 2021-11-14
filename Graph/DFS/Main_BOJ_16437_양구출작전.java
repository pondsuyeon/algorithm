package algorithm.M11.D14;

import java.io.*;
import java.util.*;

public class Main_BOJ_16437_양구출작전 {

	static int N;
	static List<Integer>[] adj;
	static Info[] info;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		adj = new ArrayList[N + 1];
		info = new Info[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			char t = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			adj[p].add(i);
			info[i] = new Info(t, a);
		}

		info[1] = new Info('S', 0);

		System.out.println(DFS(1));
	}

	private static long DFS(int v) {

		long res = info[v].t == 'S' ? info[v].a : -info[v].a;

		for (int next : adj[v]) {
			res += DFS(next);
		}

		if (res < 0)
			return 0;
		return res;
	}

	static class Info {
		char t;
		int a;

		Info(char t, int a) {
			this.t = t;
			this.a = a;
		}
	}
}
