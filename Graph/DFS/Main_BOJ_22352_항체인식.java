package algorithm.M11.D23;

import java.io.*;
import java.util.*;

public class Main_BOJ_22352_항체인식 {

	static int N, M;

	static int[][] before, after;

	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		before = new int[N][M];
		after = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				before[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				after[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		loop: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (before[i][j] != after[i][j]) {
					setVaccine(i, j, after[i][j]);
					break loop;
				}
			}
		}

		boolean flag = true;

		loop: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (before[i][j] != after[i][j]) {
					flag = false;
					break loop;
				}
			}
		}

		System.out.println(flag ? "YES" : "NO");
	}

	private static void setVaccine(int x, int y, int k) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || before[nx][ny] != before[x][y])
				continue;
			setVaccine(nx, ny, k);
		}

		before[x][y] = k;
	}

}
