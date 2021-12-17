package algorithm.M12.D17;

import java.io.*;
import java.util.*;

public class Main_BOJ_18500_미네랄2 {

	static int R, C, N;
	static char[][] cave;
	static int[][] cluster;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		cave = new char[R][C];

		for (int i = 0; i < R; i++) {
			cave[i] = br.readLine().toCharArray();
		}

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			int H = R - Integer.parseInt(st.nextToken());
			destroy(i % 2, H);
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(cave[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void destroy(int dir, int H) {

		int beforeClusterCount = getClusterCount();

		int col = destroyMineral(dir, H);

		int afterClusterCount = getClusterCount();

		if (col != -1 && beforeClusterCount < afterClusterCount) {

			for (int i = 0; i < 4; i++) {

				int nr = H + dr[i];
				int nc = col + dc[i];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C || cave[nr][nc] == '.')
					continue;

				if (checkCluster(cluster[nr][nc])) {
					cave = moveCluster(cluster[nr][nc]);
					break;
				}
			}
		}
	}

	private static char[][] moveCluster(int clusterIndex) {
		char[][] temp = new char[R][C];
		List<Node> nodes = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (cluster[i][j] != clusterIndex) {
					temp[i][j] = cave[i][j];
				} else {
					temp[i][j] = '.';
					nodes.add(new Node(i, j));
				}
			}
		}

		while (true) {

			boolean flag = true;

			for (Node node : nodes) {
				if (node.r + 1 >= R || temp[node.r + 1][node.c] != '.') {
					flag = false;
					break;
				}
			}

			if (!flag)
				break;

			for (Node node : nodes) {
				node.r += 1;
			}
		}

		for (Node node : nodes) {

			temp[node.r][node.c] = 'x';
		}

		return temp;
	}

	private static boolean checkCluster(int clusterIndex) {

		for (int i = 0; i < C; i++) {
			if (cave[R - 1][i] == 'x' && cluster[R - 1][i] == clusterIndex)
				return false;
		}

		return true;

	}

	private static int getClusterCount() {
		cluster = new int[R][C];

		int clusterIndex = 1;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (cave[i][j] == 'x' && cluster[i][j] == 0)
					setClusterGroup(i, j, clusterIndex++);
			}
		}
		return clusterIndex - 1;
	}

	private static void setClusterGroup(int r, int c, int clusterIndex) {
		cluster[r][c] = clusterIndex;

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C || cluster[nr][nc] != 0 || cave[nr][nc] == '.')
				continue;
			setClusterGroup(nr, nc, clusterIndex);
		}
	}

	private static int destroyMineral(int dir, int H) {

		int col, d;

		if (dir == 0) {
			col = C - 1;
			d = -1;
		} else {
			col = 0;
			d = 1;
		}

		while (true) {

			if (col < 0 || col >= C) {
				col = -1;
				break;
			}

			if (cave[H][col] == 'x') {
				cave[H][col] = '.';
				break;
			}
			col += d;
		}
		return col;
	}
}
