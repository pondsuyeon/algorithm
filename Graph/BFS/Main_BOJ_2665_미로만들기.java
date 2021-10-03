package algorithm.M10.D03;

import java.io.*;
import java.util.*;

public class Main_BOJ_2665_미로만들기 {
	static int N;
	static char[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		System.out.println(makeMaze());
	}

	private static int makeMaze() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[][] visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}

		pq.offer(new Node(0, 0, 0));
		visited[0][0] = 0;

		while (!pq.isEmpty()) {
			Node v = pq.poll();

			if (v.x == N - 1 && v.y == N - 1)
				return v.c;

			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				int nc = v.c;
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if (visited[nx][ny] <= visited[v.x][v.y])
					continue;

				if (map[nx][ny] == '0')
					nc++;

				visited[nx][ny] = nc;

				pq.offer(new Node(nx, ny, nc));
			}
		}
		
		return -1;
	}

	static class Node implements Comparable<Node> {
		int x, y, c;

		Node(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public int compareTo(Node node) {
			return this.c - node.c;
		}
	}
}
