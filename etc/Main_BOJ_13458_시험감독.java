package algorithm.M10.D28;

import java.io.*;
import java.util.*;

public class Main_BOJ_13458_시험감독 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int count = 0;

		for (int i = 0; i < N; i++) {
			count++;

			int left = arr[i] - B;
			if (left <= 0)
				continue;

			count += (left) / C;
			if (left % C > 0)
				count++;
		}
		System.out.println(count);
	}

}
