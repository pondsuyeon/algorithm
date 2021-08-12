import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		Node R = null;
		Node B = null;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'R') {
					R = new Node(i, j);
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					B = new Node(i, j);
					map[i][j] = '.';
				}
			}
		}

		System.out.println(bfs(R, B));
	}

	private static int bfs(Node R, Node B) {
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(R, B));

		for (int i = 1; i <= 10; i++) {
			int size = q.size();

			while (size-- > 0) {
				Info info = q.poll();

				int rx = info.R.x;
				int ry = info.R.y;
				int bx = info.B.x;
				int by = info.B.y;

				visited[rx][ry][bx][by] = true;
				for (int d = 0; d < 4; d++) {
					boolean redFirst = !(d == 0 && ry == by && rx > bx) && !(d == 1 && ry == by && rx < bx)
							&& !(d == 2 && rx == bx && ry > by) && !(d == 3 && rx == bx && ry < by);

					int nrx = rx;
					int nry = ry;
					int nbx = bx;
					int nby = by;

					boolean redOut = false;
					boolean blueOut = false;

					if (redFirst) {
						while (map[nrx + dx[d]][nry + dy[d]] != '#') {
							nrx += dx[d];
							nry += dy[d];
							if (map[nrx][nry] == 'O') {
								redOut = true;
								break;
							}
						}

						while (map[nbx + dx[d]][nby + dy[d]] != '#') {
							if (!redOut && nbx + dx[d] == nrx && nby + dy[d] == nry)
								break;

							nbx += dx[d];
							nby += dy[d];
							if (map[nbx][nby] == 'O') {
								blueOut = true;
								break;
							}
						}
					} else {
						while (map[nbx + dx[d]][nby + dy[d]] != '#') {
							nbx += dx[d];
							nby += dy[d];
							if (map[nbx][nby] == 'O') {
								blueOut = true;
								break;
							}
						}

						while (!blueOut && map[nrx + dx[d]][nry + dy[d]] != '#') {
							if (nbx == nrx + dx[d] && nby == nry + dy[d])
								break;
							nrx += dx[d];
							nry += dy[d];
							if (map[nrx][nry] == 'O') {
								redOut = true;
								break;
							}
						}
					}
					if (blueOut)
						continue;
					if (redOut)
						return i;
					if (visited[nrx][nry][nbx][nby])
						continue;
					q.offer(new Info(new Node(nrx, nry), new Node(nbx, nby)));
				}
			}
		}
		return -1;
	}

	public static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Info {
		Node R;
		Node B;

		Info(Node R, Node B) {
			this.R = R;
			this.B = B;
		}
	}
}