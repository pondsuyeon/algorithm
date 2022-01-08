package algorithm.M01.D07;

import java.io.*;
import java.util.*;

public class Main_BOJ_1520_내리막길 {

	static int M, N;
	static int[][] map;
	static int[][] dp;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			Arrays.fill(dp[i], -1);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[M - 1][N - 1] = 1;

		System.out.println(move(0, 0));
	}

	private static int move(int x, int y) {

		if (dp[x][y] != -1)
			return dp[x][y];

		dp[x][y] = 0;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= M || ny < 0 || ny >= N || map[x][y] <= map[nx][ny])
				continue;

			dp[x][y] += move(nx, ny);
		}

		return dp[x][y];
	}
}
