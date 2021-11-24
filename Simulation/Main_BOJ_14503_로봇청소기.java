package algorithm.M11.D24;

import java.io.*;
import java.util.*;

public class Main_BOJ_14503_로봇청소기 {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		boolean[][] clear = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		clear[r][c] = true;
		map[r][c] = 2;
		int ans = 2;

		while (true) {

			boolean flag = false;
			for (int i = 0; i < 4; i++) {

				d = (3 + d) % 4;
				int nr = r + dx[d];
				int nc = c + dy[d];

				if (!clear[nr][nc] && map[nr][nc] == 0) {
					r = nr;
					c = nc;

					clear[r][c] = true;
					map[r][c] = ++ans;

					flag = true;
					break;
				}
			}

			if (!flag) {
				int nr = r - dx[d];
				int nc = c - dy[d];

				if (map[nr][nc] != 1) {
					r = nr;
					c = nc;
				} else {
					break;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println(ans);
	}

}
