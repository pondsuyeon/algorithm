package algorithm.M11.D10;

import java.io.*;
import java.util.*;

public class Main_BOJ_1029_그림교환 {

	static int N;
	static int[][] value;

	static int ans = 0;

	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		value = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < N; j++) {
				value[i][j] = input.charAt(j) - '0';
			}
		}

		visited[0] = true;
		DFS(0, 1, 0);
		System.out.println(ans);
	}

	private static void DFS(int v, int count, int prev) {
		ans = Math.max(ans, count);

		for (int i = 0; i < N; i++) {
			if (visited[i] || prev > value[v][i])
				continue;
			visited[i] = true;
			DFS(i, count + 1, value[v][i]);
			visited[i] = false;
		}
	}
}
