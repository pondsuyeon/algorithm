package algorithm.M10.D03;

import java.io.*;
import java.util.*;

public class Main_BOJ_2758_로또 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		long[][] dp = new long[11][2001];

		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 2000; j++) {
				if (i == 1) {
					dp[i][j] = 1;
					continue;
				}

				for (int k = 1; k <= j / 2; k++) {
					dp[i][j] += dp[i - 1][k];
				}
			}
		}

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			long ans = 0;
			for (int i = 1; i <= m; i++) {
				ans += dp[n][i];
			}
			sb.append(ans).append("\n");
		}

		System.out.print(sb.toString());
	}

}
