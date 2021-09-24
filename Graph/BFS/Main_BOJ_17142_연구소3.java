package algorithm.M09.D0924;

import java.io.*;
import java.util.*;

public class Main_BOJ_17142_연구소3 {
	static int N, M;
	static int[][] map;

	static ArrayList<Node> virus = new ArrayList<>();
	static int virusSize;

	static int[] selected;

	static int ans = Integer.MAX_VALUE;

	static int emptyCount = 0;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 2)
					virus.add(new Node(i, j));
				else if (map[i][j] == 0)
					emptyCount++;
			}
		}

		virusSize = virus.size();

		selected = new int[M];

		comb(0, 0);

		System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
	}

	private static void comb(int index, int count) {
		if (count == M) {
			ans = Math.min(ans, spreadVirus());
			return;
		}

		if (index >= virusSize)
			return;

		selected[count] = index;
		comb(index + 1, count + 1);
		comb(index + 1, count);
	}

	private static int spreadVirus() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		for (int index : selected) {
			Node v = virus.get(index);
			visited[v.x][v.y] = true;
			q.offer(v);
		}

		int time = -1;
		int infected = 0;

		while (!q.isEmpty()) {
			time++;

			if (infected == emptyCount)
				return time;

			int size = q.size();

			while (size-- > 0) {
				Node v = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = v.x + dx[i];
					int ny = v.y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == 1)
						continue;

					if (map[nx][ny] == 0) {
						infected++;
					}
					visited[nx][ny] = true;
					q.offer(new Node(nx, ny));
				}
			}
		}

		return Integer.MAX_VALUE;
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
