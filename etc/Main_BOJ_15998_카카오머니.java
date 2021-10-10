package algorithm.M10.D10;

import java.io.*;
import java.util.*;

public class Main_BOJ_15998_카카오머니 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		long[][] logs = new long[N + 1][2];
		long M = 0;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			logs[i][0] = Long.parseLong(st.nextToken());
			logs[i][1] = Long.parseLong(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {

			if (logs[i][0] < 0 && logs[i - 1][1] + logs[i][0] < 0) {
				if (M == 0) {
					M = logs[i][1] - logs[i][0] - logs[i - 1][1];
				} else {
					M = GCD(logs[i][1] - logs[i][0] - logs[i - 1][1], M);
				}
				if (M == 1)
					break;
			}

		}
		boolean flag = true;
		for (int i = 1; i <= N; i++) {

			if (logs[i - 1][1] + logs[i][0] == logs[i][1])
				continue;

			if (logs[i][0] < 0 && logs[i - 1][1] + logs[i][0] < 0 && M > logs[i][1])
				continue;

			flag = false;
			break;
		}
		if (M == 0)
			M = 1;
		System.out.println(flag ? M : -1);

	}

	static long GCD(long x, long y) {
		if (y == 0)
			return x;

		return GCD(y, x % y);
	}
}
