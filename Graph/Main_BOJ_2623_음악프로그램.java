package algorithm.M10.D08;

import java.io.*;
import java.util.*;

public class Main_BOJ_2623_음악프로그램 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		Queue<Integer> q = new LinkedList<>();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] in = new int[N + 1];

		LinkedList<Integer>[] adj = new LinkedList[N + 1];

		for (int i = 0; i <= N; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int K = Integer.parseInt(st.nextToken());

			int before = 0;

			for (int j = 0; j < K; j++) {
				int S = Integer.parseInt(st.nextToken());

				if (j > 0) {
					adj[before].add(S);
					in[S]++;
				}
				before = S;
			}
		}

		int count = 0;
		for (int i = 1; i <= N; i++) {
			if (in[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			count++;

			int v = q.poll();

			sb.append(v).append("\n");

			for (int next : adj[v]) {
				if (--in[next] == 0)
					q.add(next);
			}
		}

		if (count < N) {
			System.out.println(0);
		} else {
			System.out.print(sb.toString());
		}
	}

}
