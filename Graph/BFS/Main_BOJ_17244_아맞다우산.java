package algorithm.M12.D03;

import java.io.*;
import java.util.*;

public class Main_BOJ_17244_아맞다우산 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Node {
		int x, y, bit;

		Node(int x, int y, int bit) {
			this.x = x;
			this.y = y;
			this.bit = bit;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		// N 가로, M 세로
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[M][N];

		int sx = -1, sy = -1, ex = -1, ey = -1;

		int index = 1;

		for (int i = 0; i < M; i++) {

			String temp = br.readLine();

			for (int j = 0; j < N; j++) {
				char ch = temp.charAt(j);
				if (ch == '#') {
					map[i][j] = -1;
				} else if (ch == 'S') {
					map[i][j] = 0;
					sx = i;
					sy = j;
				} else if (ch == 'E') {
					map[i][j] = 0;
					ex = i;
					ey = j;
				} else if (ch == '.') {
					map[i][j] = 0;
				} else {
					map[i][j] = index++;
				}

			}
		}
		for(int i = 0; i < M; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println(BFS(M, N, map, sx, sy, ex, ey, index - 1));
	}

	private static int BFS(int M, int N, int[][] map, int sx, int sy, int ex, int ey, int xCount) {
		boolean[][][] visited = new boolean[1 << xCount][M][N];
		Queue<Node> q = new LinkedList<>();

		visited[0][sx][sy] = true;
		q.offer(new Node(sx, sy, 0));

		int time = -1;
		while (!q.isEmpty()) {
			time++;

			int size = q.size();

			while (size-- > 0) {

				Node n = q.poll();

				if (n.x == ex && n.y == ey && n.bit == (1 << (xCount)) - 1) {
					return time;
				}

				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];

					if (nx < 0 || nx >= M || ny < 0 || ny >= N || map[nx][ny] == -1)
						continue;

					int nbit = map[nx][ny] > 0 ? (n.bit | 1 << (map[nx][ny] - 1)) : n.bit;

					if (visited[nbit][nx][ny])
						continue;

					visited[nbit][nx][ny] = true;
					q.offer(new Node(nx, ny, nbit));
				}
			}
		}

		return -1;
	}
}
