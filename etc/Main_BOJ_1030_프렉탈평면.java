package algorithm.M09.D30;

import java.io.*;
import java.util.*;

public class Main_BOJ_1030_프렉탈평면 {

	static int s, N, K, R1, R2, C1, C2;

	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		s = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R1 = Integer.parseInt(st.nextToken());
		R2 = Integer.parseInt(st.nextToken());
		C1 = Integer.parseInt(st.nextToken());
		C2 = Integer.parseInt(st.nextToken());

		map = new int[R2 - R1 + 1][C2 - C1 + 1];
		partition(0, 0, (int) Math.pow(N, s), s);

		for (int i = 0; i <= R2 - R1; i++) {
			for (int j = 0; j <= C2 - C1; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.print(sb.toString());
	}

	private static void partition(int x, int y, int size, int s) {
		if (s == 0)
			return;

		if (x > R2 || x + size < R1 || y > C2 || y + size < C1)
			return;

		int len = size / N;

		int white = (size - len * K) / 2;

		for (int i = x; i < x + size; i += len) {
			for (int j = y; j < y + size; j += len) {

				if (i >= x + white && i < x + size - white && j >= y + white && j < y + size - white) {
					paint(i, j, len);
				} else {
					partition(i, j, len, s - 1);
				}
			}
		}
	}

	private static void paint(int x, int y, int len) {
		if (x > R2 || x + len < R1 || y > C2 || y + len < C1)
			return;
		for (int i = x; i < x + len; i++) {
			for (int j = y; j < y + len; j++) {
				if (i < R1 || i > R2 || j < C1 || j > C2)
					continue;
				map[i - R1][j - C1] = 1;
			}
		}
	}
}
