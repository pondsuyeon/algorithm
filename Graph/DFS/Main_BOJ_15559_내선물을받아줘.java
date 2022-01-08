package algorithm.M01.D08;

import java.io.*;
import java.util.*;

public class Main_BOJ_15559_내선물을받아줘 {

	static int N, M;

	static int[][] group;
	static int[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		group = new int[N][M];

		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {

				switch (temp[j]) {
				case 'N':
					map[i][j] = 0;
					break;
				case 'S':
					map[i][j] = 1;
					break;
				case 'W':
					map[i][j] = 2;
					break;
				case 'E':
					map[i][j] = 3;
					break;
				}
			}
		}

		int groupIndex = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (group[i][j] == 0) {
					dfs(i, j, groupIndex++);
				}
			}
		}

		System.out.println(ans);
	}

	private static void dfs(int x, int y, int groupIndex) {

		group[x][y] = groupIndex;

		int d = map[x][y];
		int nx = x + dx[d];
		int ny = y + dy[d];

		if (group[nx][ny] == 0)
			dfs(nx, ny, groupIndex);
		else {
			if (group[nx][ny] == groupIndex)
				ans++;
			return;
		}
	}
}