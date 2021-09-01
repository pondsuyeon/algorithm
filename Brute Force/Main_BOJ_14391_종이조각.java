import java.util.*;
import java.io.*;

public class Main_BOJ_14391_종이조각 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}

		int max = -1;
		for (int bit = 0; bit < 1 << (M * N); bit++) {

			int sum = 0;

			for (int i = 0; i < N; i++) {
				int temp = 0;
				for (int j = 0; j < M; j++) {
					if ((bit & 1 << i * M + j) == 0) {
						temp = temp * 10 + map[i][j];
					} else {
						sum += temp;
						temp = 0;
					}
				}
				sum += temp;
			}
			for (int j = 0; j < M; j++) {
				int temp = 0;
				for (int i = 0; i < N; i++) {
					if ((bit & 1 << i * M + j) != 0) {
						temp = temp * 10 + map[i][j];
					} else {
						sum += temp;
						temp = 0;
					}
				}
				sum += temp;
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

}
