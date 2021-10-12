package algorithm.M10.D12;

import java.io.*;
import java.util.*;

public class Main_BOJ_17837_새로운게임2 {

	static int N, K;

	static int[][] board;

	static Node[] nodes;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static int[] op = { 1, 0, 3, 2 };

	static int[][][] map;
	static int[][] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N + 1][N + 1];
		size = new int[N + 1][N + 1];
		map = new int[N + 1][N + 1][3];
		nodes = new Node[K + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			nodes[i] = new Node(x, y, d, 0);
			map[x][y][0] = i;
			size[x][y]++;
		}

		int turn = 0;

		while (turn++ < 1000 && !move()) {
		}
		System.out.println(turn < 1000 ? turn : -1);
	}

	static boolean move() {
		for (int i = 1; i <= K; i++) {

			int x = nodes[i].x;
			int y = nodes[i].y;
			int d = nodes[i].d;
			int s = nodes[i].s;

			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx <= 0 || nx > N || ny <= 0 || ny > N || board[nx][ny] == 2) {
				d = op[d];
				nodes[i].d = d;

				nx = x + dx[d];
				ny = y + dy[d];

			}

			if (nx <= 0 || nx > N || ny <= 0 || ny > N || board[nx][ny] == 2)
				continue;
			if (board[nx][ny] == 1) {

				int a = map[x][y][s];
				int b = map[x][y][size[x][y] - 1];

				nodes[a].s = size[x][y] - 1;
				nodes[b].s = s;

				map[x][y][s] = b;
				map[x][y][size[x][y] - 1] = a;

			}

			int ns = size[nx][ny];

			int ts = size[x][y];

			for (int j = s; j < ts; j++) {
				int idx = map[x][y][j];
				nodes[idx].x = nx;
				nodes[idx].y = ny;
				nodes[idx].s = ns + j - s;

				map[x][y][j] = 0;

				if (++size[nx][ny] > 3)
					return true;
				size[x][y]--;

				map[nx][ny][ns + j - s] = idx;
			}
		}
		return false;
	}

	static class Node {
		int x, y, d, s;

		Node(int x, int y, int d, int s) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.s = s;
		}
	}
}
