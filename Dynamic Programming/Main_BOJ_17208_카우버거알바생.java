package algorithm.M11.D30;

import java.io.*;
import java.util.*;

public class Main_BOJ_17208_카우버거알바생 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Node[] orders = new Node[N + 1];
		int[][][] dp = new int[N + 1][M + 1][K + 1];

		for (int i = 1; i <= N; i++) {

			st = new StringTokenizer(br.readLine());

			int cheese = Integer.parseInt(st.nextToken());
			int fried = Integer.parseInt(st.nextToken());

			orders[i] = new Node(cheese, fried);
		}

		for (int i = 1; i <= N; i++) {
			int cheese = orders[i].cheese;
			int fried = orders[i].fried;

			for (int j = 1; j <= M; j++) {

				for (int k = 1; k <= K; k++) {

					dp[i][j][k] = dp[i - 1][j][k];

					if (j - cheese >= 0 && k - fried >= 0) {
						dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - cheese][k - fried] + 1);
					}
				}
			}
		}

		System.out.println(dp[N][M][K]);
	}

	static class Node {
		int cheese;
		int fried;

		Node(int cheese, int fried) {
			this.cheese = cheese;
			this.fried = fried;
		}

	}
}
