package algorithm.M09.D0916;

import java.io.*;
import java.util.*;

public class Main_BOJ_21609_상어중학교 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static Queue<Node> removable = new LinkedList<>();
	static int rainbowBlock = 0;

	static Queue<Node> tempQueue;
	static Queue<Node> zero = new LinkedList<>();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static final int INF = 10;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int score = 0;

		while (true) {
			findBigBlock();
			if (removable.size() < 2)
				break;

			score += removeBigBlock();

			getGravity();

			map = rotateLeft90();

			getGravity();
		}

		System.out.println(score);
	}

	private static void findBigBlock() {

		visited = new boolean[N][N];
		Queue<Node> res = new LinkedList<>();
		rainbowBlock = 0;
		int x = -1;
		int y = -1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] || 0 >= map[i][j] || map[i][j] > M)
					continue;

				tempQueue = new LinkedList<>();
				getBlockNode(i, j, map[i][j]);

				if (res.size() < tempQueue.size() || (res.size() == tempQueue.size() && rainbowBlock < zero.size())
						|| (res.size() == tempQueue.size() && rainbowBlock == zero.size() && x < i)
						|| (res.size() == tempQueue.size() && rainbowBlock == zero.size()) && x == i && y < j) {
					res = tempQueue;
					rainbowBlock = zero.size();
					x = i;
					y = j;
				}

				while (!zero.isEmpty()) {
					Node z = zero.poll();
					visited[z.x][z.y] = false;
				}
			}
		}

		removable = res;
	}

	private static void getBlockNode(int x, int y, int block) {
		tempQueue.offer(new Node(x, y));

		if (map[x][y] == 0) {
			zero.offer(new Node(x, y));
		}

		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			if (visited[nx][ny])
				continue;

			if (map[nx][ny] == 0 || map[nx][ny] == block)
				getBlockNode(nx, ny, block);

		}
	}

	private static int removeBigBlock() {
		int B = removable.size();
		while (!removable.isEmpty()) {
			Node n = removable.poll();

			map[n.x][n.y] = INF;
		}
		return B * B;
	}

	private static void getGravity() {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {

			int start = N - 1;

			while (start >= 0) {

				int r = start;
				while (r >= 0 && map[r][i] != -1) {
					if (map[r][i] != INF) {
						q.offer(map[r][i]);
						map[r][i] = INF;
					}
					r--;
				}

				int t = start;
				while (t > r && !q.isEmpty()) {
					map[t--][i] = q.poll();
				}
				start = r - 1;
			}
		}
	}

	private static int[][] rotateLeft90() {
		int[][] tempMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tempMap[i][j] = map[j][N - 1 - i];
			}
		}
		return tempMap;
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
