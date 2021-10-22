package algorithm.M10.D22;

import java.io.*;
import java.util.*;

public class Main_BOJ_20057_마법사상어와토네이도 {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	static int[][] ds = { { -1, 1, -1, 1, -2, 2, -1, 1, 0 }, { 0, 0, -1, -1, -1, -1, -2, -2, -3 } };
	static double[] p = { 0.01, 0.01, 0.07, 0.07, 0.02, 0.02, 0.1, 0.1, 0.05 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int out = 0;

		int x = N / 2;
		int y = N / 2;

		int[] di = { 0, 1 };
		int[] ri = { 1, 1 };

		int d = 0;

		int moveCount = 1;
		int changeCount = 0;
		int maxMove = 1;

		while (true) {

			if (x == 0 && y == 0)
				break;

			int nx = x + dx[d];
			int ny = y + dy[d];

			int sand = map[nx][ny];
			int alpha = map[nx][ny];

			map[nx][ny] = 0;

			for (int i = 0; i < 9; i++) {
				int tx = x + ri[0] * ds[di[0]][i];
				int ty = y + ri[1] * ds[di[1]][i];
				int ts = (int) (sand * p[i]);

				alpha -= ts;

				if (tx < 0 || tx >= N || ty < 0 || ty >= N) {
					out += ts;
				} else {
					map[tx][ty] += ts;
				}
			}

			int ax = x + 2 * dx[d];
			int ay = y + 2 * dy[d];

			if (ax < 0 || ax >= N || ay < 0 || ay >= N) {
				out += alpha;
			} else {
				map[ax][ay] += alpha;
			}

			x = nx;
			y = ny;

			if (moveCount == maxMove) {
				if (changeCount < 1) {
					changeCount++;
				} else {
					maxMove++;
					changeCount = 0;
				}
				moveCount = 1;
				d = (d + 1) % 4;

				if (dx[d] == 0) {
					di[0] = 0;
					di[1] = 1;
					ri[0] = 1;
					ri[1] = dy[d] * -1;
				} else {
					di[0] = 1;
					di[1] = 0;
					ri[0] = dx[d] * -1;
					ri[1] = 1;
				}

			} else {
				moveCount++;
			}
		}

		System.out.println(out);
	}

}
