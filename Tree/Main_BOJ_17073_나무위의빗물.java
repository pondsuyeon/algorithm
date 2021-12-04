package algorithm.M12.D04;

import java.io.*;
import java.util.*;

public class Main_BOJ_17073_나무위의빗물 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] count = new int[N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			count[u]++;
			count[v]++;
		}

		int leaf = 0;
		for (int i = 2; i <= N; i++) {
			if (count[i] == 1)
				leaf++;
		}
		System.out.println((double) W / leaf);
	}

}
