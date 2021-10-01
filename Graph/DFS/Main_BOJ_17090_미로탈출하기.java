package algorithm.M10.D01;

import java.io.*;
import java.util.*;

public class Main_BOJ_17090_미로탈출하기 {

	static int N, M;

	static int[][] dp;
	static int[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dp = new int[N][M];
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();

			for (int j = 0; j < M; j++) {
				char c = temp.charAt(j);
				if (c == 'U') {
					map[i][j] = 0;
				} else if (c == 'D') {
					map[i][j] = 1;
				} else if (c == 'L') {
					map[i][j] = 2;
				} else {
					map[i][j] = 3;
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ans += escape(i, j);
			}
		}
		System.out.println(ans);
	}

	private static int escape(int x, int y) {
		if (dp[x][y] != -1)
			return dp[x][y];

		dp[x][y] = 0;

		int nx = x + dx[map[x][y]];
		int ny = y + dy[map[x][y]];

		
		if(nx < 0 || nx >= N || ny < 0 || ny >= M) dp[x][y] = 1;
		else dp[x][y] = escape(nx, ny);
		
		return dp[x][y];
	}
}
