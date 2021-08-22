import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] riceCake;
	static boolean[][] visited;
	static int[] selected;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		riceCake = new int[N][];
		visited = new boolean[N][10];
		selected = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int M = Integer.parseInt(st.nextToken());
			riceCake[i] = new int[M];

			for (int j = 0; j < M; j++) {
				riceCake[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		saveDongHee(0, 0);
		if (flag) {
			for (int rc : selected) {
				sb.append(rc).append('\n');
			}
		} else {
			sb.append("-1\n");
		}
		System.out.print(sb.toString());
	}

	private static void saveDongHee(int today, int yesterdayRiceCake) {
		if (flag)
			return;

		if (today == N) {
			flag = true;
			return;
		}
		if (visited[today][yesterdayRiceCake])
			return;
		visited[today][yesterdayRiceCake] = true;

		for (int rc : riceCake[today]) {
			if (!flag && rc != yesterdayRiceCake) {
				selected[today] = rc;
				saveDongHee(today + 1, rc);
			}
		}
	}
}