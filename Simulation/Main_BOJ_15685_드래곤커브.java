package algorithm.M09.D0922;

import java.io.*;
import java.util.*;

public class Main_BOJ_15685_드래곤커브 {
	static boolean[][] checked = new boolean[101][101];
	static int N;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			dragonCurve(x, y, d, g);
		}
		System.out.println(getSquareCount());
	}

	private static int getSquareCount() {
		int ans = 0;
		for (int i = 0; i < 100; i++) {
			next: for (int j = 0; j < 100; j++) {
				for (int a = 0; a < 2; a++) {
					for (int b = 0; b < 2; b++) {
						if (!checked[i + a][j + b]) {
							continue next;
						}
					}
				}
				ans++;
			}
		}

		return ans;
	}

	private static void dragonCurve(int sx, int sy, int d, int G) {
		ArrayList<Point> dc = new ArrayList<>();

		dc.add(new Point(sx, sy));

		dc.add(new Point(sx + dx[d], sy + dy[d]));

		for (int g = 0; g < G; g++) {
			int size = dc.size();

			Point end = dc.get(size - 1);

			for (int i = size - 2; i >= 0; i--) {
				dc.add(end.rotate(dc.get(i)));
			}
		}

		for (Point p : dc) {
			checked[p.x][p.y] = true;
		}
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point rotate(Point p) {
			int tx = -(p.y - this.y) + this.x;
			int ty = p.x - this.x + this.y;
			return new Point(tx, ty);
		}
	}
}
