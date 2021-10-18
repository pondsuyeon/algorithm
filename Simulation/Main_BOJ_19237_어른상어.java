package algorithm.M10.D18;

import java.io.*;
import java.util.*;

public class Main_BOJ_19237_어른상어 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Node[] shark = new Node[M + 1];
		Smell[][] smell = new Smell[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int index = Integer.parseInt(st.nextToken());

				if (index == 0)
					continue;
				shark[index] = new Node(i, j);

				smell[i][j] = new Smell(index, 0);
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= M; i++) {
			shark[i].d = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int i = 1; i <= M; i++) {
			int[][] dir = new int[4][4];

			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					dir[j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
			shark[i].dir = dir;
		}

		int sec = 0;
		int sharkCount = M;

		while (sharkCount > 1 && sec++ < 1000) {
			Queue<Integer> q = new LinkedList<>();

			for (int i = 1; i <= M; i++) {
				if (shark[i].dead)
					continue;
				int x = shark[i].x;
				int y = shark[i].y;

				int[] dir = shark[i].dir[shark[i].d];

				boolean flag = false;

				for (int d : dir) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;

					if (smell[nx][ny] == null) {

						shark[i].x = nx;
						shark[i].y = ny;
						shark[i].d = d;

						flag = true;
						q.offer(i);
						break;
					}
				}

				if (!flag) {
					for (int d : dir) {
						int nx = x + dx[d];
						int ny = y + dy[d];

						if (nx < 0 || nx >= N || ny < 0 || ny >= N)
							continue;

						if (smell[nx][ny] != null && smell[nx][ny].o == i) {

							smell[nx][ny] = new Smell(i, sec);

							shark[i].x = nx;
							shark[i].y = ny;
							shark[i].d = d;

							break;
						}
					}
				}
			}
			while (!q.isEmpty()) {
				int index = q.poll();

				int x = shark[index].x;
				int y = shark[index].y;

				if (smell[x][y] == null) {
					smell[x][y] = new Smell(index, sec);
				} else {
					shark[index].dead = true;
					sharkCount--;
				}
			}

			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (smell[j][k] != null && sec - smell[j][k].t >= K) {
						smell[j][k] = null;
					}
				}
			}
			System.out.println(sec+"초");
			print(smell);
		}

		System.out.println(sec > 1000 ? -1 : sec);
	}

	private static void print(Smell[][] smell) {
		int N = smell.length;
		
		for(int i = 0 ; i <N; i++) {
			for(int j = 0 ;j  < N; j++) {
				if(smell[i][j] == null) System.out.print("0/0 ");
				else System.out.print(smell[i][j].o+"/"+smell[i][j].t+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static class Node {
		int x, y, d;
		boolean dead = false;
		int[][] dir;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Smell {
		int o, t;

		Smell(int o, int t) {
			this.o = o;
			this.t = t;
		}
	}
}
