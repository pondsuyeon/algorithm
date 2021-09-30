package algorithm.M09.D30;

import java.io.*;
import java.util.*;

public class Main_BOJ_18808_스티커붙이기 {

	static int N, M, K;
	static int[][] notebook;
	static int[][][] sticker;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		notebook = new int[N][M];

		sticker = new int[K][][];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			sticker[i] = new int[R][C];

			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					sticker[i][r][c] = Integer.parseInt(st.nextToken());
				}
			}
		}

		for (int i = 0; i < K; i++) {
			int t = 0;
			while(!stick(sticker[i]) && ++t < 4) {
				sticker[i] = rotate(sticker[i]);
			}
			
		}

		int ans = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (notebook[i][j] == 1)
					ans++;
			}
		}

		System.out.println(ans);
	}

	private static boolean stick(int[][] sticker) {
		int R = sticker.length;
		int C = sticker[0].length;

		for (int i = 0; i <= N - R; i++) {
			for (int j = 0; j <= M - C; j++) {

				if (checkAndStick(i, j, sticker, R, C))
					return true;

			}
		}
		return false;
	}

	private static boolean checkAndStick(int x, int y, int[][] sticker, int R, int C) {

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (notebook[x + i][y + j] == 1 && sticker[i][j] == 1)
					return false;
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (sticker[i][j] == 1)
					notebook[x + i][y + j] = 1;
			}
		}
		return true;
	}

	private static int[][] rotate(int[][] sticker) {
		int R = sticker.length;
		int C = sticker[0].length;

		int[][] temp = new int[C][R];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				temp[j][R - i - 1] = sticker[i][j];
			}
		}

		return temp;
	}

}