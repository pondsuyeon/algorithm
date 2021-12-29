package algorithm.M12.D28;

import java.io.*;
import java.util.*;

public class Main_BOJ_7579_앱 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] m = new int[N + 1];
		int[] c = new int[N + 1];

		int[][] dp = new int[N + 1][10001];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {

			for (int j = 1; j <= 10000; j++) {

				dp[i][j] = dp[i - 1][j];
				if (j >= c[i]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - c[i]] + m[i]);
				}
			}
		}

		int min = 0;
		for (int i = 0; i <= 10000; i++) {
			if (dp[N][i] >= M) {
				min = i;
				break;
			}
		}
		System.out.println(min);
	}
}
