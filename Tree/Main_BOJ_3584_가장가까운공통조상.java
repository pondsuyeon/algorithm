package algorithm.M10.D06;

import java.io.*;
import java.util.*;

public class Main_BOJ_3584_가장가까운공통조상 {

	static int N;
	static boolean[] visited;

	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			visited = new boolean[N + 1];
			parents = new int[N + 1];

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());

				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				parents[B] = A;
			}

			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			sameParents(u, 1);

			int p;

			if (visited[v])
				p = v;
			else
				p = sameParents(v, 2);
			sb.append(p).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static int sameParents(int x, int flag) {
		visited[x] = true;

		if (flag == 1) {
			if (parents[x] == 0)
				return x;
			return sameParents(parents[x], flag);
		} else {
			if (visited[parents[x]])
				return parents[x];
			return sameParents(parents[x], flag);
		}
	}
}