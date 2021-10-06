package algorithm.M10.D06;

import java.io.*;
import java.util.*;

public class Main_BOJ_14500_테트로미노 {

	static int N, M;

	static int[][] nums;

	static int[][][] tetromino = { { { 0, 0, 0, 0 }, { 0, 1, 2, 3 } }, { { 0, 1, 2, 3 }, { 0, 0, 0, 0 } },
			{ { 0, 1, 2, 2 }, { 0, 0, 0, 1 } }, { { 0, 1, 2, 2 }, { 0, 0, 0, -1 } }, { { 0, 0, 0, 1 }, { 0, 1, 2, 2 } },
			{ { 0, 0, 0, 1 }, { 0, 1, 2, 0 } }, { { 0, 0, 1, 2 }, { 0, 1, 0, 0 } }, { { 0, 0, 1, 2 }, { 0, 1, 1, 1 } },
			{ { 0, 1, 1, 1 }, { 0, 0, 1, 2 } }, { { 0, 0, 0, -1 }, { 0, 1, 2, 2 } }, { { 0, 0, 1, 1 }, { 0, 1, 0, 1 } },
			{ { 0, 1, 1, 2 }, { 0, 0, 1, 1 } }, { { 0, 0, -1, -1 }, { 0, 1, 1, 2 } },
			{ { 0, 1, 1, 2 }, { 0, 0, -1, -1 } }, { { 0, 0, 1, 1 }, { 0, 1, 1, 2 } },
			{ { 0, 1, 1, 2 }, { 0, -1, 0, 0 } }, { { 0, 1, 1, 1 }, { 0, -1, 0, 1 } },
			{ { 0, 0, 0, 1 }, { -1, 0, 1, 0 } }, { { 0, 1, 1, 2 }, { 0, 0, 1, 0 } } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ans = Math.max(ans, getMaxTetromino(i, j));
			}
		}

		System.out.println(ans);
	}

	private static int getMaxTetromino(int x, int y) {
		int ans = 0;

		next: for (int i = 0; i < tetromino.length; i++) {
			int sum = 0;
			for (int j = 0; j < 4; j++) {
				int nx = x + tetromino[i][0][j];
				int ny = y + tetromino[i][1][j];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue next;

				sum += nums[nx][ny];
			}

			ans = Math.max(ans, sum);
		}

		return ans;
	}

}
