package algorithm.M10.D08;

import java.io.*;
import java.util.*;

public class Main_BOJ_16946_벽부수고이동하기4 {

	static int N, M;

	static int[][] map;
	static int[][] group;

	static List<Integer> groupCount;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		group = new int[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(group[i], -1);
		}

		groupCount = new ArrayList<>();
		int groupIndex = 0;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && group[i][j] == -1) {
					groupCount.add(setGroup(i, j, groupIndex++));
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					sb.append(0);
				} else {
					sb.append(getCountOfMove(i, j));
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static int getCountOfMove(int x, int y) {
		int count = 1;
		Set<Integer> groupSet = new HashSet<>();

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (!isInRange(nx, ny) || map[nx][ny] == 1)
				continue;
			groupSet.add(group[nx][ny]);
		}

		for (int gi : groupSet) {
			count = (count + groupCount.get(gi)) % 10;
		}

		return count;
	}

	private static int setGroup(int x, int y, int g) {
		group[x][y] = g;

		int res = 1;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!isInRange(nx, ny) || map[nx][ny] == 1 || group[nx][ny] != -1)
				continue;

			res += setGroup(nx, ny, g);
		}
		return res;
	}

	private static boolean isInRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}