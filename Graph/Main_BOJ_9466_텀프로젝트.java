package algorithm.M09.D0923;

import java.util.*;
import java.io.*;

public class Main_BOJ_9466_텀프로젝트 {

	static int N;
	static int[] checked;
	static boolean[] possible;
	static int[] like;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			checked = new int[N + 1];
			possible = new boolean[N + 1];
			like = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				like[i] = Integer.parseInt(st.nextToken());

			}

			int graphCount = 0;
			for (int i = 1; i <= N; i++) {
				if (checked[i] != 0)
					continue;
				DFS(i, ++graphCount);
			}
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (!possible[i])
					ans++;
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static int DFS(int v, int graphIndex) {
		checked[v] = graphIndex;

		int next = like[v];

		if (checked[next] != 0) {
			if (checked[next] == graphIndex) {
				possible[v] = true;
				if (v == next)
					return -1;
				else
					return next;
			} else {
				return -1;
			}
		} else {
			int res = DFS(next, graphIndex);

			if (res == -1) {
				possible[v] = false;
				return -1;
			} else {
				possible[v] = true;
				if (v == res) {
					return -1;
				} else {
					return res;
				}
			}
		}
	}
}