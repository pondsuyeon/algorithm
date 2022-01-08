package algorithm.M01.D04;

import java.io.*;
import java.util.*;

public class Main_BOJ_2140_지뢰찾기 {

	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int count = (N - 2) * (N - 2);

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {

				if (i == 1 || i == N - 2 || j == 1 || j == N - 2) {

					boolean flag = true;
					for (int k = 0; k < 8; k++) {
						int x = i + dx[k];
						int y = j + dy[k];

						if (x < 0 || x >= N || y < 0 || y >= N || map[x][y] == '#')
							continue;

						if (map[x][y] <= '0') {
							flag = false;
							break;
						}
					}

					if (flag) {

						for (int k = 0; k < 8; k++) {
							int x = i + dx[k];
							int y = j + dy[k];

							if (x < 0 || x >= N || y < 0 || y >= N || map[x][y] == '#')
								continue;

							map[x][y]--;
						}
					} else {
						count--;
					}
				}

			}
		}

		System.out.println(count);
	}

}
