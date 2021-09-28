package algorithm.M09.D28;

import java.io.*;
import java.util.*;

public class Main_BOJ_14267_회사문화1 {

	static int N, M;

	static LinkedList<Integer>[] adj;

	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dp = new int[N + 1];
		adj = new LinkedList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new LinkedList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int p = Integer.parseInt(st.nextToken());

			if (p == -1)
				continue;
			adj[p].offer(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			dp[v] += w;
		}

		complement(1, 0);

		for (int i = 1; i <= N; i++) {
			sb.append(dp[i] + " ");
		}

		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	private static void complement(int v, int w) {

		dp[v] += w;

		for (int next : adj[v]) {

			complement(next, dp[v]);
		}
	}

}
