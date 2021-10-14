package algorithm.M10.D14;

import java.io.*;
import java.util.*;

public class Main_BOJ_17822_원판돌리기 {

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 2][M];
		int[] top = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			for (int i = 1; x * i <= N; i++) {
				if (d == 0) {
					top[x * i] = (M + top[x * i] - k) % M;
				} else {
					top[x * i] = (top[x * i] + k) % M;
				}
			}

			Queue<int[]> q = new LinkedList<>();

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {

					int now = (top[i] + j) % M;

					if (arr[i][now] == 0)
						continue;

					boolean flag = false;
					for (int l = 0; l < 4; l++) {
						int tr = i + dr[l];
						int tc = (M + top[tr] + j + dc[l]) % M;

						if (arr[i][now] == arr[tr][tc]) {
							q.offer(new int[] { tr, tc });
							flag = true;
						}
					}

					if (flag) {
						q.offer(new int[] { i, now });
					}

				}
			}
			if (q.isEmpty()) {
				int total = 0;
				int count = 0;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (arr[i][j] == 0)
							continue;
						total += arr[i][j];
						count++;
					}
				}

				double avg = (double) total / count;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (arr[i][j] == 0)
							continue;

						if (arr[i][j] > avg)
							arr[i][j]--;
						else if (arr[i][j] < avg)
							arr[i][j]++;
					}
				}

			} else {
				while (!q.isEmpty()) {
					int[] v = q.poll();

					arr[v[0]][v[1]] = 0;
				}

			}

		}
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				ans += arr[i][j];
			}
		}

		System.out.println(ans);
	}

}
