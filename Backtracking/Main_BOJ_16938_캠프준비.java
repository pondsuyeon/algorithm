package algorithm.M12.D01;

import java.io.*;
import java.util.*;

public class Main_BOJ_16938_캠프준비 {

	static int N, L, R, X;
	static int[] A;
	static boolean[] selected;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		A = new int[N + 1];
		selected = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		solution(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

		System.out.println(ans);
	}

	private static void solution(int count, int index, int max, int min, int sum) {
		if (index > N) {
			if (count >= 2 && sum >= L && sum <= R && max - min >= X) {
				ans++;
			}
			return;
		}

		solution(count, index + 1, max, min, sum);

		max = Math.max(max, A[index]);
		min = Math.min(min, A[index]);
		sum += A[index];

		solution(count + 1, index + 1, max, min, sum);
	}

}
