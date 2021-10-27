package algorithm.M10.D27;

import java.io.*;
import java.util.*;

public class Main_BOJ_23291_어항정리 {

	static int N, K;

	static int[][] map;

	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[N - 1][i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;

		while (true) {
			ans++;

			putFish();

			int[] status = stackFishbowl1();

			adjustFishCount();

			moveToOneline(status);

			status = stackFishbowl2();

			adjustFishCount();

			moveToOneline(status);

			if (compareFish())
				break;
		}
		System.out.println(ans);
	}

	private static boolean compareFish() {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			min = Math.min(min, map[N - 1][i]);
			max = Math.max(max, map[N - 1][i]);
		}

		if (K >= max - min)
			return true;
		return false;
	}

	private static int[] stackFishbowl2() {

		int from = 0;
		int to = N / 2;

		for (int k = 0; k < 2; k++) {
			int count = 0;
			while (map[N - 1 - count][to - 1] != 0) {
				count++;
			}

			for (int j = 0; j < count; j++) {
				for (int i = to - 1; i >= from; i--) {
					map[N - 2 * count + j][2 * to - i - 1] = map[N - 1 - j][i];
					map[N - 1 - j][i] = 0;
				}
			}

			from = to;
			to = (to + N) / 2;
		}
		return new int[] { from, N };
	}

	private static void moveToOneline(int[] status) {
		int row = N - 1;
		int col = status[0];
		int index = 0;

		while (col < status[1]) {
			while (row >= 0 && map[row][col] != 0) {
				map[N - 1][index++] = map[row][col];
				map[row--][col] = 0;
			}
			row = N - 1;
			col++;
		}

	}

	private static void adjustFishCount() {
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;

				for (int d = 0; d < 2; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 0)
						continue;

					int D = Math.abs(map[i][j] - map[nx][ny]) / 5;

					if (D == 0)
						continue;

					if (map[i][j] > map[nx][ny]) {
						temp[i][j] -= D;
						temp[nx][ny] += D;
					} else {
						temp[i][j] += D;
						temp[nx][ny] -= D;
					}
				}
			}
		}

		map = temp;
	}

	private static int[] stackFishbowl1() {
		int from = 0;
		int to = 1;
		while (true) {

			int count = 0;
			while (map[N - 1 - count][to - 1] != 0) {
				count++;
			}

			if (count > N - to)
				break;

			for (int i = to - 1; i >= from; i--) {
				for (int j = 0; j < count; j++) {
					map[N - 1 - (to - i)][to + j] = map[N - 1 - j][i];
					map[N - 1 - j][i] = 0;
				}
			}
			from = to;
			to = to + count;
		}
		return new int[] { from, to };
	}

	private static void putFish() {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			min = Math.min(min, map[N - 1][i]);
		}
		for (int i = 0; i < N; i++) {
			if (min == map[N - 1][i]) {
				map[N - 1][i]++;
			}
		}
	}

}
