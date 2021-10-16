package algorithm.M10.D16;

import java.io.*;
import java.util.*;

public class Main_BOJ_20061_모노미노도미노2 {

	static int[][] dx = { {}, {}, { 0 }, { 1 } };
	static int[][] dy = { {}, {}, { 1 }, { 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] green = new int[6][4];
		int[][] blue = new int[6][4];

		int score = 0;
		int count = 0;

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			score += setBlock(t, 0, y, green);

			if (t == 2) {
				t = 3;
			} else if (t == 3) {
				t = 2;
			}
			score += setBlock(t, 0, x, blue);
		}

		count += getCountOfBlock(green);
		count += getCountOfBlock(blue);

		System.out.println(score);
		System.out.println(count);
	}

	private static int getCountOfBlock(int[][] block) {
		int cnt = 0;
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 4; c++) {
				if (block[r][c] != 0)
					cnt++;
			}
		}
		return cnt;
	}

	private static int setBlock(int t, int x, int y, int[][] block) {

		int blockCount = 1 + dx[t].length;
		int[] xs = new int[blockCount];
		int[] ys = new int[blockCount];

		xs[0] = x;
		ys[0] = y;

		for (int i = 1; i < blockCount; i++) {
			xs[i] = x + dx[t][0];
			ys[i] = y + dy[t][0];
		}

		while (true) {
			boolean flag = true;
			for (int i = 0; i < blockCount; i++) {
				if (xs[i] + 1 > 5 || block[xs[i] + 1][ys[i]] != 0) {
					flag = false;
					break;
				}
			}

			if (!flag)
				break;
			for (int i = 0; i < blockCount; i++) {
				xs[i]++;
			}
		}

		for (int i = 0; i < blockCount; i++) {
			block[xs[i]][ys[i]] = t;
		}

		int res = 0;

		for (int r : xs) {
			int cnt = 0;
			for (int b = 0; b < 4; b++) {
				if (block[r][b] != 0)
					cnt++;
			}

			if (cnt == 4) {
				deleteRow(block, r);
				res++;
			}
		}

		int cnt = 0;
		for (int r = 0; r < 2; r++) {
			for (int c = 0; c < 4; c++) {
				if (block[r][c] != 0) {
					cnt++;
					break;
				}
			}
		}

		while (cnt-- > 0) {
			deleteRow(block, 5);
		}
		return res;
	}

	private static void deleteRow(int[][] block, int row) {
		for (int r = row; r > 0; r--) {
			block[r] = block[r - 1];
		}
		block[0] = new int[4];
	}

}
