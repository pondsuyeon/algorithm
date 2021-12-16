package algorithm.M12.D16;

import java.io.*;
import java.util.*;

public class Main_BOJ_2056_작업 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] time = new int[N + 1];
		int[] end = new int[N + 1];
		int[] in = new int[N + 1];
		List<Integer>[] adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			time[i] = Integer.parseInt(st.nextToken());

			int K = Integer.parseInt(st.nextToken());

			in[i] += K;

			for (int j = 0; j < K; j++) {
				int before = Integer.parseInt(st.nextToken());
				adj[before].add(i);
			}
		}

		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (in[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {

			int v = q.poll();
			end[v] += time[v];

			for (int next : adj[v]) {
				if (--in[next] == 0)
					q.add(next);
				end[next] = Math.max(end[next], end[v]);
			}
		}

		int max = 0;

		for (int i = 1; i <= N; i++) {
			max = Math.max(max, end[i]);
		}

		System.out.println(max);
	}
}
