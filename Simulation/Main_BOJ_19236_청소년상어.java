package algorithm.M10.D17;

import java.io.*;
import java.util.*;

public class Main_BOJ_19236_청소년상어 {

	static int ans = 0;

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Node[] fish = new Node[17];
		int[][] map = new int[4][4];

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken()) - 1;

				fish[a] = new Node(i, j, b);
				map[i][j] = a;
			}
		}
		
		eat(0, 0, fish[map[0][0]].d, map[0][0], fish, map);

		System.out.println(ans);
	}

	private static void eat(int x, int y, int d, int sum, Node[] fish, int[][] map) {
		ans = Math.max(ans, sum);

		int now = map[x][y];
		map[x][y] = 0;

		fishMove(fish, map);

		for (int i = 1; i < 4; i++) {
			int nx = x + i * dx[d];
			int ny = y + i * dy[d];

			if (!isRange(nx, ny) || map[nx][ny] == -1)
				continue;

			int next = map[nx][ny];

			map[x][y] = -1;
			eat(nx, ny, fish[next].d, sum + next, fish.clone(), mapCopy(map));
			map[x][y] = 0;
		}

		map[x][y] = now;
	}

	private static int[][] mapCopy(int[][] map) {
		int[][] temp = map.clone();

		for (int i = 0; i < 4; i++) {
			temp[i] = map[i].clone();
		}
		return temp;
	}

	private static void fishMove(Node[] fish, int[][] map) {
		for (int i = 1; i <= 16; i++) {
			int x = fish[i].x;
			int y = fish[i].y;
			int d = fish[i].d;

			if (i != map[x][y])
				continue;

			for (int j = 0; j < 8; j++) {
				int nd = (d + j) % 8;
				int nx = x + dx[nd];
				int ny = y + dy[nd];

				if (!isRange(nx, ny) || map[nx][ny] == 0)
					continue;

				if (map[nx][ny] == -1) {
					map[nx][ny] = i;
					map[x][y] = -1;

				} else {
					int next = map[nx][ny];

					map[nx][ny] = i;
					fish[next] = new Node(x, y, fish[next].d);
					map[x][y] = next;
				}
				fish[i] = new Node(nx, ny, nd);
				break;
			}
		}
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < 4;
	}

	static class Node {
		int x, y, d;

		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
