package algorithm.D0902;

import java.util.*;
import java.io.*;

public class Main_BOJ_3673_나눌수있는부분수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			int[] D = new int[d];

			st = new StringTokenizer(br.readLine());
			
			int sum = 0;
			for (int i = 1; i <= n; i++) {
				sum = (sum + Integer.parseInt(st.nextToken())) % d;

				D[sum]++;
			}

			int ans = D[0];
			for (int i = 1; i < d; i++) {
				if (D[i] > 0)
					ans += D[i] * (D[i] - 1) / 2;
			}

			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

}
