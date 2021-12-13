package algorithm.M12.D13;

import java.io.*;
import java.util.*;

public class Main_BOJ_18809_Gaaaaaaaaaarden {

	static int N, M, G, R;

	static int[][] origin;

	static List<Node> possible;

	static int[] selected;

	static int ans = 0;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		origin = new int[N][M];
		possible = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());

				if (origin[i][j] == 2) {
					possible.add(new Node(i, j));
				}
			}
		}
		selected = new int[possible.size()];

		comb(0, 0, 0);

		System.out.println(ans);
	}

	private static void comb(int index, int g, int r) {
		if (g == G && r == R) {
			ans = Math.max(ans, gerFlowerCount());
			return;
		}

		if (index >= possible.size()) {
			return;
		}

		selected[index] = 0;
		comb(index + 1, g, r);
		selected[index] = 1;
		comb(index + 1, g + 1, r);
		selected[index] = 2;
		comb(index + 1, g, r + 1);
		selected[index] = 0;
	}

	private static int gerFlowerCount() {

		int[][] garden = new int[N][M];
		int[][] time = new int[N][M];

		int sec = 1;
		Queue<Node> q = new LinkedList<>();

		for (int i = 0; i < possible.size(); i++) {
			if (selected[i] == 0)
				continue;

			int x = possible.get(i).x;
			int y = possible.get(i).y;

			garden[x][y] = selected[i];
			time[x][y] = 1;

			q.offer(possible.get(i));
		}

		while (!q.isEmpty()) {

			sec++;

			int size = q.size();

			while (size-- > 0) {

				Node n = q.poll();
				int x = n.x;
				int y = n.y;

				if (garden[x][y] == 3)
					continue;

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M || origin[nx][ny] == 0)
						continue;

					if (time[nx][ny] == 0) {
						time[nx][ny] = sec;
						garden[nx][ny] = garden[x][y];

						q.offer(new Node(nx, ny));
					} else if (time[nx][ny] == sec && garden[x][y] != garden[nx][ny]) {
						garden[nx][ny] = 3;
					}

				}
			}
		}

		int res = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (garden[i][j] == 3)
					res++;
			}
		}

		return res;
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
