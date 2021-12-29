package algorithm.M12.D29;

import java.io.*;
import java.util.*;

public class Main_BOJ_22945_팀빌딩 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] x = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			x[i] = Integer.parseInt(st.nextToken());
		}

		int s = 0;
		int e = N - 1;
		int max = 0;

		while (s < e) {

			max = Math.max(max, (e - s - 1) * Math.min(x[s], x[e]));

			if (x[s] < x[e])
				s++;
			else
				e--;
		}

		System.out.println(max);
	}

}
