package algorithm.M10.D01;

import java.util.*;
import java.io.*;

public class Main_BOJ_3980_선발명단 {

	static int[][] S = new int[11][11];
	static boolean[] selected = new boolean[11];

	static int max = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int C = Integer.parseInt(br.readLine());

		while (C-- > 0) {
			for (int i = 0; i < 11; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 11; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			max = -1;
			getGoodLineUp(0, 0);

			sb.append(max).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void getGoodLineUp(int index, int sum) {
		if (index == 11) {
			max = Math.max(max, sum);
			return;
		}

		for (int j = 0; j < 11; j++) {
			if (selected[j] || S[index][j] == 0)
				continue;

			selected[j] = true;
			getGoodLineUp(index + 1, sum + S[index][j]);
			selected[j] = false;
		}
	}
}