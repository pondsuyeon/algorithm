package algorithm.M12.D23;

import java.io.*;
import java.util.*;

public class Main_BOJ_1113_수영장만들기 {

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[][] height;
	static boolean[][] visited;
	static boolean[][] movable;

	static List<Node> nodes;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int res = 0;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		height = new int[N][M];
		movable = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				height[i][j] = temp.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			Arrays.fill(movable[i], true);
		}

		ans = 0;
		while (true) {

			boolean flag = false;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (movable[i][j]) {
						visited = new boolean[N][M];
						res = 10;
						nodes = new ArrayList<>();
						getWall(i, j);

						if (res == -1 || res == 10) {
							for (Node n : nodes) {
								movable[n.x][n.y] = false;

							}
						} else {
							ans += (res - height[i][j]) * nodes.size();
							for (Node n : nodes) {
								height[n.x][n.y] = res;
							}

							flag = true;
						}
					}
				}
			}

			if (!flag)
				break;
		}

		System.out.println(ans);
	}

	private static void getWall(int x, int y) {
		visited[x][y] = true;
		nodes.add(new Node(x, y));

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || height[x][y] > height[nx][ny]) {
				res = -1;
				continue;
			}
			if (!visited[nx][ny] && height[x][y] == height[nx][ny])
				getWall(nx, ny);
			else if (height[x][y] < height[nx][ny])
				res = Math.min(res, height[nx][ny]);
		}
	}

}
