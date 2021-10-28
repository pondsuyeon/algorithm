package algorithm.M10.D28;

import java.io.*;
import java.util.*;

public class Main_BOJ_3190_뱀 {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		Deque<int[]> snake = new LinkedList<>();
		Queue<int[]> info = new LinkedList<>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			map[x][y] = 1;
		}

		int L = Integer.parseInt(br.readLine());

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			int X = Integer.parseInt(st.nextToken());

			int C;
			if (st.nextToken().equals("L")) {
				C = 1;
			} else {
				C = -1;
			}

			info.add(new int[] { X, C });
		}

		snake.add(new int[] { 0, 0 });
		map[0][0] = -1;
		int D = 3;

		int time = 0;
		while (true) {
			time++;

			int nx = snake.peekLast()[0] + dx[D];
			int ny = snake.peekLast()[1] + dy[D];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1)
				break;

			if (map[nx][ny] == 0) {
				int[] clear = snake.pollFirst();
				map[clear[0]][clear[1]] = 0;
			}

			map[nx][ny] = -1;
			snake.addLast(new int[] { nx, ny });

			if(!info.isEmpty() && info.peek()[0] == time) {
				D = (4 + D + info.poll()[1]) % 4;
			}
		}

		System.out.println(time);
	}
}
