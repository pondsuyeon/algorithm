package algorithm.M09.D0917;

import java.io.*;
import java.util.*;

public class Main_BOJ_18111_마인크래프트 {
	static int N, M, B;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		int s = 300;
		int e = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				s = Math.min(s, map[i][j]);
				e = Math.max(e, map[i][j]);
			}
		}
		int time = Integer.MAX_VALUE;
		int height = 0;
		for (int i = s; i <= e; i++) {
			int res = getTime(i);

			if (res < time || res == time && height < i) {
				time = res;
				height = i;
			}
		}

		System.out.println(time + " " + height);
	}

	public static int getTime(int h) {
		int block = B;

		int a = 0;
		int b = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (h < map[i][j])
					a += map[i][j] - h;
				else
					b += h - map[i][j];
			}
		}

		if (block + a < b)
			return Integer.MAX_VALUE;
		else
			return a * 2 + b;
	}
}
