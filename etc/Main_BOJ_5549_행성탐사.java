package algorithm.M10.D28;

import java.io.*;
import java.util.*;

public class Main_BOJ_5549_행성탐사 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(br.readLine());

		int[][][] sums = new int[3][M + 1][N + 1];

		for (int i = 1; i <= M; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 1; j <= N; j++) {
				if (temp[j - 1] == 'J') {
					sums[0][i][j]++;
				} else if (temp[j - 1] == 'O') {
					sums[1][i][j]++;
				} else {
					sums[2][i][j]++;
				}
				for (int k = 0; k < 3; k++) {
					sums[k][i][j] += sums[k][i - 1][j] + sums[k][i][j - 1] - sums[k][i - 1][j - 1];
				}

			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			int[] res = new int[3];

			for (int k = 0; k < 3; k++) {
				res[k] = sums[k][c][d] - sums[k][a - 1][d] - sums[k][c][b - 1] + sums[k][a - 1][b - 1];
			}

			sb.append(res[0] + " " + res[1] + " " + res[2] + "\n");
		}
		System.out.print(sb.toString());
	}
}