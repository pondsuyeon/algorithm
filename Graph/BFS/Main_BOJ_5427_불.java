package algorithm.M10.D05;

import java.io.*;
import java.util.*;

public class Main_BOJ_5427_불 {

	static int W, H;
	static char[][] map;

	static int[][] fireTime;

	static Queue<Node> fires;

	static Node start;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new char[H][W];

			fireTime = new int[H][W];

			for (int i = 0; i < H; i++) {
				Arrays.fill(fireTime[i], -1);
			}

			fires = new LinkedList<>();

			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();

				for (int j = 0; j < W; j++) {
					if (map[i][j] == '*') {
						fires.offer(new Node(i, j));
						fireTime[i][j] = 0;
						map[i][j] = '.';
					} else if (map[i][j] == '@') {
						start = new Node(i, j);
						map[i][j] = '.';
					}
				}
			}

			spreadFire();

			int ans = escape();
			sb.append(ans != -1 ? ans : "IMPOSSIBLE").append("\n");
		}
		System.out.print(sb.toString());
	}

	private static int escape() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];

		visited[start.x][start.y] = true;
		q.offer(start);

		int sec = 0;
		while (!q.isEmpty()) {
			sec++;

			int size = q.size();

			while (size-- > 0) {
				Node n = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];

					if (nx < 0 || nx >= H || ny < 0 || ny >= W)
						return sec;
					if (visited[nx][ny] || map[nx][ny] == '#' || sec >= fireTime[nx][ny])
						continue;

					visited[nx][ny] = true;
					q.offer(new Node(nx, ny));

				}
			}

		}

		return -1;
	}

	private static void spreadFire() {
		int sec = 0;

		while (!fires.isEmpty()) {
			sec++;

			int size = fires.size();

			while (size-- > 0) {
				Node n = fires.poll();

				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];

					if (nx < 0 || nx >= H || ny < 0 || ny >= W || fireTime[nx][ny] != -1 || map[nx][ny] == '#')
						continue;
					fireTime[nx][ny] = sec;

					fires.add(new Node(nx, ny));

				}
			}
		}
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
