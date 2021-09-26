package algorithm.M09.D26;

import java.io.*;
import java.util.*;

public class Main_BOJ_9328_열쇠_DFS {
	static int H, W;
	static char[][] map;
	static boolean[][] visited;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int newKey;
	static int ans;

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

			while (true) {
				newKey = key;

				visited = new boolean[H + 2][W + 2];
				findKey(0, 0, key);

				if (newKey == key)
					break;
				key = newKey;
			}

			visited = new boolean[H + 2][W + 2];
			ans = 0;

			findDocument(0, 0, key);

			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void findDocument(int x, int y, int key) {
		visited[x][y] = true;

		if (map[x][y] == '$')
			ans++;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx > H + 1 || ny < 0 || ny > W + 1 || visited[nx][ny] || map[nx][ny] == '*')
				continue;

			if (isDoor(map[nx][ny]) && (key & 1 << map[nx][ny] - 'A') == 0)
				continue;

			findDocument(nx, ny, key);
		}
	}

	private static void findKey(int x, int y, int key) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx > H + 1 || ny < 0 || ny > W + 1 || visited[nx][ny] || map[nx][ny] == '*')
				continue;

			if (isDoor(map[nx][ny]) && (key & 1 << map[nx][ny] - 'A') == 0)
				continue;

			if (isKey(map[nx][ny]) && (key & 1 << map[nx][ny] - 'a') == 0) {
				newKey |= 1 << map[nx][ny] - 'a';
			}

			findKey(nx, ny, key);
		}
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
