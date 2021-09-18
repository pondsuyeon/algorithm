package algorithm.M09.D0918;

import java.io.*;
import java.util.*;

public class Main_BOJ_20058_마법사상어와파이어스톰 {
	static int N;
	static int[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static boolean[][] visited;
	static int groupCount = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		N = 1 << N;

		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());

			partition(L);

			melt();
		}
		
		int sumOfIce = 0;
		int maxCount = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sumOfIce += map[i][j];
				
				if(map[i][j] == 0 || visited[i][j]) continue;
				
				groupCount = 0;
				DFS(i, j);
				
				maxCount = Math.max(maxCount, groupCount);
			}
		}
		
		System.out.println(sumOfIce);
		System.out.println(maxCount);
	}

	private static void DFS(int x, int y) {
		groupCount++;
		visited[x][y] = true;
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == 0) continue;

			DFS(nx, ny);
		}
	}

	private static void melt() {
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;

				int iceCount = 0;
				
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (map[nx][ny] != 0)
						iceCount++;
				}

				temp[i][j] = map[i][j];

				if (iceCount < 3)
					temp[i][j]--;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}

	private static void partition(int l) {
		int len = 1 << l;
		for (int i = 0; i < N; i += len) {
			for (int j = 0; j < N; j += len) {
				rotate(i, j, len);
			}
		}
	}

	private static void rotate(int x, int y, int len) {
		int[][] temp = new int[len][len];

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				temp[j][len - 1 - i] = map[x + i][y + j];
			}
		}

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				map[x + i][y + j] = temp[i][j];
			}
		}
	}
}
