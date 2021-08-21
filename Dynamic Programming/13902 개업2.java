import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		final int INF = Integer.MAX_VALUE;

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);

		int[] S = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			dp[S[i]] = 1;
		}

		for (int i = 0; i < M - 1; i++) {
			for (int j = i + 1; j < M; j++) {
				if (S[i] + S[j] <= N)
					dp[S[i] + S[j]] = 1;
			}
		}
		if (dp[N] == 1) {
			System.out.println(dp[N]);
			return;
		}

		for (int i = 1; i < N; i++) {
			if (dp[i] == INF)
				continue;
			for (int j = 1; i + j <= N; j++) {
				if (dp[j] == INF)
					continue;

				dp[i + j] = Math.min(dp[i + j], dp[i] + dp[j]);
			}
		}
		System.out.println(dp[N] != INF ? dp[N] : -1);
	}
}