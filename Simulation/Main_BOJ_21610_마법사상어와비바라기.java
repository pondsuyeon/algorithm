package algorithm.M09.D0915;

import java.util.*;
import java.io.*;

public class Main_BOJ_21610_마법사상어와비바라기 {

	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static int[] cx = { -1, -1, 1, 1 };
	static int[] cy = { -1, 1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] A = new int[N + 1][N + 1];

		int[] d = new int[M];
		int[] s = new int[M];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			d[i] = Integer.parseInt(st.nextToken()) - 1;
			s[i] = Integer.parseInt(st.nextToken());
		}

		boolean[][] isCloud = new boolean[N + 1][N + 1];

		isCloud[N][1] = true;
		isCloud[N][2] = true;
		isCloud[N - 1][1] = true;
		isCloud[N - 1][2] = true;
		for (int m = 0; m < M; m++) {
			isCloud = moveCloud(N, d[m], s[m], isCloud);

			rain(N, isCloud, A);

			isCloud = findCloud(N, A, isCloud);
		}

		System.out.println(sumOfWater(N, A));
	}

	private static boolean[][] moveCloud(int N, int d, int s, boolean[][] isCloud) {
		boolean[][] tempCloud = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (isCloud[i][j]) {
					int nx = (s * N + i + s * dx[d]) % N;
					int ny = (s * N + j + s * dy[d]) % N;

					if (nx == 0)
						nx = N;
					if (ny == 0)
						ny = N;

					tempCloud[nx][ny] = true;
				}
			}
		}
		return tempCloud;
	}

	private static void rain(int N, boolean[][] isCloud, int[][] A) {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (isCloud[i][j]) {
					A[i][j]++;
				}
			}
		}

		int[][] tempA = new int[N +1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for(int j = 1; j <= N ; j++) {
				tempA[i][j] = A[i][j];
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (isCloud[i][j]) {
					int count = 0;
					for (int c = 0; c < 4; c++) {
						int nx = i + cx[c];
						int ny = j + cy[c];

						if (nx <= 0 || nx > N || ny <= 0 || ny > N)
							continue;

						if(A[nx][ny] > 0)
							count++;
					}

					tempA[i][j] += count;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for(int j = 1; j <= N ; j++) {
				A[i][j] = tempA[i][j];
			}
		}		
	}

	private static boolean[][] findCloud(int N, int[][] A, boolean[][] isCloud) {
		boolean[][] tempCloud = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (A[i][j] >= 2 && !isCloud[i][j]) {
					tempCloud[i][j] = true;
					A[i][j] -= 2;
				}
			}
		}
		return tempCloud;
	}

	private static int sumOfWater(int N, int[][] A) {
		int res = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				res += A[i][j];
			}
		}
		return res;
	}
}
