package algorithm.M09.D0915;

import java.util.*;
import java.io.*;

public class Main_BOJ_21608_상어초등학교 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int[] score = { 0, 1, 10, 100, 1000 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] order = new int[N * N];

		HashSet<Integer>[] like = new HashSet[N * N + 1];

		int[][] checked = new int[N][N];

		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());

			order[i] = num;
			like[num] = new HashSet<>();

			while (st.hasMoreTokens()) {
				like[num].add(Integer.parseInt(st.nextToken()));
			}
		}
		for (int num : order) {

			int emptyCount = -1;
			int maxCount = -1;
			int r = 0;
			int c = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (checked[i][j] != 0)
						continue;

					int eCount = 0;
					int fCount = 0;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if (nx < 0 || nx >= N || ny < 0 || ny >= N)
							continue;
						if (checked[nx][ny] == 0)
							eCount++;
						else if (like[num].contains(checked[nx][ny]))
							fCount++;
					}
					if (maxCount < fCount || (maxCount == fCount && emptyCount < eCount)) {
						maxCount = fCount;
						emptyCount = eCount;
						r = i;
						c = j;
					}
				}
			}
			checked[r][c] = num;
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int count = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					else if (like[checked[i][j]].contains(checked[nx][ny]))
						count++;
				}

				ans += score[count];
			}
		}
		System.out.println(ans);
	}
}
