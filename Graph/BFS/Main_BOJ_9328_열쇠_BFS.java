package algorithm.M09.D26;

import java.io.*;
import java.util.*;

public class Main_BOJ_9328_열쇠_BFS {
	static int H, W;
	static char[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static boolean keyAdded = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H + 2][W + 2];

			for (int i = 1; i <= H; i++) {
				String temp = br.readLine();
				for (int j = 1; j <= W; j++) {
					map[i][j] = temp.charAt(j - 1);
				}
			}

			char[] keys = br.readLine().toCharArray();

			int key = 0;
			for (char k : keys) {
				key |= 1 << (k - 'a');
			}

			keyAdded = true;
			while (keyAdded) {
				keyAdded = false;
				key = findKey(0, 0, key);
			}

			sb.append(findDocument(0, 0, key)).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static int findDocument(int x, int y, int key) {
		int result = 0;

		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[H + 2][W + 2];

		q.offer(new Node(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (nx < 0 || nx > H + 1 || ny < 0 || ny > W + 1 || visited[nx][ny] || map[nx][ny] == '*')
					continue;

				if (isDoor(map[nx][ny]) && (key & 1 << map[nx][ny] - 'A') == 0)
					continue;

				if (map[nx][ny] == '$')
					result++;

				visited[nx][ny] = true;
				q.offer(new Node(nx, ny));
			}
		}
		return result;
	}

	private static int findKey(int x, int y, int key) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[H + 2][W + 2];

		int result = key;

		q.offer(new Node(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (nx < 0 || nx > H + 1 || ny < 0 || ny > W + 1 || visited[nx][ny] || map[nx][ny] == '*')
					continue;

				if (isDoor(map[nx][ny]) && (key & 1 << map[nx][ny] - 'A') == 0)
					continue;

				if (isKey(map[nx][ny]) && (key & 1 << map[nx][ny] - 'a') == 0) {
					keyAdded = true;
					result |= 1 << map[nx][ny] - 'a';
				}
				visited[nx][ny] = true;
				q.offer(new Node(nx, ny));
			}
		}
		return result;
	}

	private static boolean isDoor(char c) {
		return c >= 'A' && c <= 'Z';
	}

	private static boolean isKey(char c) {
		return c >= 'a' && c <= 'z';
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
