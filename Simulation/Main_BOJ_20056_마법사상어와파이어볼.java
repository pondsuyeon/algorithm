package algorithm.M10;

import java.io.*;
import java.util.*;

public class Main_BOJ_20056_마법사상어와파이어볼 {

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Fireball> fb = new LinkedList<>();

		Queue<Fireball>[][] map = new LinkedList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<Fireball>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			fb.add(new Fireball(r, c, m, s, d));
		}

		while (K-- > 0) {

			while (!fb.isEmpty()) {
				Fireball f = fb.poll();

				int r = (1000 * N + f.r + f.s * dr[f.d]) % N;
				int c = (1000 * N + f.c + f.s * dc[f.d]) % N;

				map[r][c].add(new Fireball(r, c, f.m, f.s, f.d));
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int cnt = map[i][j].size();

					if (cnt == 0)
						continue;

					if (cnt == 1) {
						fb.add(map[i][j].poll());
					} else {

						int m = 0;
						int s = 0;
						int d = map[i][j].peek().d % 2;
						boolean same = true;

						while (!map[i][j].isEmpty()) {
							Fireball f = map[i][j].poll();

							m += f.m;
							s += f.s;

							if (same && d != f.d % 2) {
								same = false;
							}
						}

						m = m / 5;
						s = s / cnt;

						if (m == 0)
							continue;

						int n = same ? 0 : 1;

						while (n < 8) {
							fb.add(new Fireball(i, j, m, s, n));
							n += 2;
						}

					}
				}
			}
		}

		int sum = 0;
		while (!fb.isEmpty()) {
			sum += fb.poll().m;
		}

		System.out.println(sum);
	}

	static class Fireball {
		int r, c, m, s, d;

		Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
