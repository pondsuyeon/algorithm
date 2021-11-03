package algorithm.M11.D03;

import java.io.*;
import java.util.*;

public class Main_BOJ_10429_QUENTO {

	static int N, M;

	static int[][] selected;
	static int[][] result;

	static char[][] map = new char[3][3];
	static boolean[][] visited = new boolean[3][3];

	static boolean flag = false;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		selected = new int[2 * M - 1][2];

		for (int i = 0; i < 3; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i <= 8; i += 2) {
			int x = i / 3;
			int y = i % 3;

			visited = new boolean[3][3];

			visited[x][y] = true;
			selected[0] = new int[] { x, y };
			DFS(x, y, 0, map[x][y] - '0');
			if (flag)
				break;
		}

		if (flag) {
			sb.append(1).append("\n");
			for (int i = 0; i < 2 * M - 1; i++) {
				sb.append(result[i][0] + " " + result[i][1] + "\n");
			}
		} else {
			sb.append(0).append("\n");
		}

		System.out.print(sb.toString());
	}

	private static void DFS(int x, int y, int count, int pre) {
		if (flag)
			return;

		if (count == 2 * M - 1) {
			if (pre == N) {
				result = selected.clone();

				for (int i = 0; i < 2 * M - 1; i++) {
					result[i] = selected[i].clone();
				}

				flag = true;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3 || visited[nx][ny])
				continue;

			visited[nx][ny] = true;

			selected[count] = new int[] { nx, ny };

			if (map[x][y] == '+') {
				DFS(nx, ny, count + 1, pre + (map[nx][ny] - '0'));
			} else if (map[x][y] == '-') {
				DFS(nx, ny, count + 1, pre - (map[nx][ny] - '0'));
			} else {
				DFS(nx, ny, count + 1, pre);
			}

			visited[nx][ny] = false;
		}

	}
}