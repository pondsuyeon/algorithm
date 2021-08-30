package algorithm.D0830;

import java.io.*;
import java.util.*;

public class Main_BOJ_14442_벽부수고이동하기2 {
	static int N, M, K;
	static char[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		System.out.println(BFS());
	}

	private static int BFS() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][K + 1];

		visited[0][0][0] = true;
		q.offer(new Node(0, 0, 0));
		int depth = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			depth++;

			while (size-- > 0) {
				Node node = q.poll();

				if (node.x == N - 1 && node.y == M - 1)
					return depth;

				for (int i = 0; i < 4; i++) {
					int nx = node.x + dx[i];
					int ny = node.y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M)
						continue;

					if (node.k < K && map[nx][ny] == '1' && !visited[nx][ny][node.k + 1]) {
						visited[nx][ny][node.k + 1] = true;
						q.offer(new Node(nx, ny, node.k + 1));
					} else if (!visited[nx][ny][node.k] && map[nx][ny] != '1') {
						visited[nx][ny][node.k] = true;
						q.offer(new Node(nx, ny, node.k));
					}
				}
			}
		}
		return -1;
	}

	static class Node {
		int x;
		int y;
		int k;

		Node(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
}