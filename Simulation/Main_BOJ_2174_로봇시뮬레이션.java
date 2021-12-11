package algorithm.M12.D09;

import java.io.*;
import java.util.*;

public class Main_BOJ_2174_로봇시뮬레이션 {

	static class Robot {
		int x, y, d;

		Robot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static int[] dy = { -1, 0, 1, 0 }; // 북 동 남 서 시계 방향
	static int[] dx = { 0, 1, 0, -1 };

	static int A, B; // 가로 세로
	static int N, M;
	static int[][] location;

	static Robot[] robot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		robot = new Robot[N + 1];
		location = new int[B + 1][A + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = B + 1 - Integer.parseInt(st.nextToken());
			char temp = st.nextToken().charAt(0);

			int d;
			if (temp == 'N') {
				d = 0;
			} else if (temp == 'E') {
				d = 1;
			} else if (temp == 'S') {
				d = 2;
			} else {
				d = 3;
			}

			robot[i] = new Robot(x, y, d);
			location[y][x] = i;
		}

		boolean flag = true;
		for (int i = 1; i <= M; i++) {

			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			char temp = st.nextToken().charAt(0);
			int count = Integer.parseInt(st.nextToken());

			int cmd = 0;
			if (temp == 'L')
				cmd = -1;
			else if (temp == 'R')
				cmd = 1;

			int res = simulate(index, cmd, count);

			if (res == 0) {
				continue;
			}

			if (res == -1) {
				sb.append("Robot " + index + " crashes into the wall");
			} else {
				sb.append("Robot " + index + " crashes into robot " + res);
			}
			flag = false;
			break;
		}
		if (flag)
			sb.append("OK");

		System.out.println(sb.toString());
	}

	private static int simulate(int index, int cmd, int count) {

		if (cmd == 0) {
			return move(index, count);
		} else {
			rotate(index, cmd, count);
			return 0;
		}
	}

	private static void rotate(int index, int cmd, int count) {
		robot[index].d = (robot[index].d + 4 + cmd * (count % 4)) % 4;
	}

	private static int move(int index, int count) {

		int x = robot[index].x;
		int y = robot[index].y;
		int d = robot[index].d;

		int nx = x;
		int ny = y;

		while (count-- > 0) {

			nx += dx[d];
			ny += dy[d];

			if (nx <= 0 || nx > A || ny <= 0 || ny > B)
				return -1;

			if (location[ny][nx] != 0)
				return location[ny][nx];
		}

		location[y][x] = 0;
		location[ny][nx] = index;
		robot[index].x = nx;
		robot[index].y = ny;
		return 0;
	}
}
