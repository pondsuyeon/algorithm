package algorithm.M11.D19;

import java.io.*;
import java.util.*;

public class Main_BOJ_17070_파이프옮기기1 {

	// 막대가 대각선일 때 벽인지 아닌지 체크할 델타 배열
	static int[] wdx = { 0, -1 };
	static int[] wdy = { -1, 0 };

	// 현재 점과 또다른 점 찾기 위한 델타 배열
	static int[] bdx = { 0, -1, -1 };
	static int[] bdy = { -1, 0, -1 };

	// 각 막대 타입별 탐색 가능한 경우
	static int[][] check = { { 0, 2 }, { 1, 2 }, { 0, 1, 2 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N + 1][N + 1];
		int[][][] dp = new int[3][N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 가로막대이면서 1, 2에 끝점이 놓일 때
		dp[0][1][2] = 1;

		for (int x = 1; x <= N; x++) {
			next: for (int y = 1; y <= N; y++) {
				// 이미 끝점이 벽인 경우
				if (map[x][y] == 1)
					continue;

				// 현재 놓인 막대가 가로, 세로, 대각선인 경우
				for (int k = 0; k < 3; k++) {

					// 대각선일 경우, 위, 왼쪽 벽인지 확인해야 함
					if (k == 2) {
						for (int w = 0; w < 2; w++) {
							int cx = x + wdx[w];
							int cy = y + wdy[w];

							if (!isRange(cx, cy, N) || map[cx][cy] == 1)
								continue next;
						}
					}

					// 또 다른 끝점
					int bx = x + bdx[k];
					int by = y + bdy[k];

					if (!isRange(bx, by, N) || map[bx][by] == 1)
						continue;

					for (int c : check[k]) {
						dp[k][x][y] += dp[c][bx][by];
					}
				}
			}
		}

		System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
	}

	static boolean isRange(int x, int y, int N) {
		return x >= 1 && x <= N && y >= 1 && y <= N;
	}

}
