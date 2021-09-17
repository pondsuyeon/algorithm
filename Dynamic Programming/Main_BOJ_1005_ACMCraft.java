package algorithm.M09.D0916;

import java.io.*;
import java.util.*;

public class Main_BOJ_1005_ACMCraft {

	static int N, K, W;
	static int[] D;
	static int[] dp;
	static LinkedList<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			D = new int[N + 1];
			dp = new int[N + 1];
			Arrays.fill(dp, -1);
			adj = new LinkedList[N + 1];

			for (int i = 1; i <= N; i++) {
				adj[i] = new LinkedList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());

				adj[Y].offer(X);
			}

			W = Integer.parseInt(br.readLine());

			sb.append(getMinTime(W)).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static int getMinTime(int v) {
		if(dp[v] != -1) return dp[v];
		
		dp[v] = 0;
		
		for(int next : adj[v]) {
			dp[v] = Math.max(getMinTime(next), dp[v]);
		}
		
		return dp[v] = dp[v] + D[v];
	}
}