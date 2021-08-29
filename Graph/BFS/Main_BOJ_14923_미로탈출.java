import java.io.*;
import java.util.*;

public class Main_BOJ_14923_미로탈출 {
	static int N, M;
	static int Hx, Hy;
	static int Ex, Ey;

	static int[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Hx = Integer.parseInt(st.nextToken());
		Hy = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Ex = Integer.parseInt(st.nextToken());
		Ey = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(escape());
	}

	private static int escape() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[2][N + 1][M + 1];

		q.offer(new Node(Hx, Hy, 1));
		visited[1][Hx][Hy] = true;

		int depth = -1;
		while (!q.isEmpty()) {
			depth++;
			int size = q.size();

			while (size-- > 0) {
				Node v = q.poll();

				if (v.x == Ex && v.y == Ey)
					return depth;

				for (int i = 0; i < 4; i++) {
					int nx = v.x + dx[i];
					int ny = v.y + dy[i];

					if (nx <= 0 || nx > N || ny <= 0 || ny > M)
						continue;

					if (v.k == 1 && map[nx][ny] == 1) {
						visited[0][nx][ny] = true;
						q.offer(new Node(nx, ny, 0));
					} else if (map[nx][ny] != 1 && !visited[v.k][nx][ny]) {
						visited[v.k][nx][ny] = true;
						q.offer(new Node(nx, ny, v.k));
					}
				}
			}
		}
		return -1;
	}

	static class Node {
		int x, y, k;

		Node(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
}