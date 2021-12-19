package algorithm.M12.D19;

import java.io.*;
import java.util.*;

public class Main_BOJ_7570_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int k = Integer.parseInt(st.nextToken());
			dp[k] = dp[k - 1] + 1;
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, dp[i]);
		}

		System.out.println(N - max);
	}
}
