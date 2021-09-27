package algorithm.M09.D27;

import java.io.*;

public class Main_BOJ_1248_맞춰봐 {
	static int N;
	static boolean isFinished = false;

	static char[][] S;

	static int[] sum;
	static int[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		sum = new int[N + 1];
		selected = new int[N + 1];

		S = new char[N + 1][N + 1];

		char[] temp = br.readLine().toCharArray();
		int tempIndex = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = i; j <= N; j++) {
				S[i][j] = temp[tempIndex++];
			}
		}
		getAnswer(1);

		for (int i = 1; i <= N; i++) {
			sb.append(selected[i] + " ");
		}
		System.out.println(sb.toString());
	}

	private static void getAnswer(int index) {

		if (index > N) {
			isFinished = true;
			return;
		}

		next: for (int num = -10; num <= 10; num++) {
			if (isFinished)
				return;

			selected[index] = num;
			sum[index] = sum[index - 1] + num;

			for (int i = 1; i <= index; i++) {
				int part = sum[index] - sum[i - 1];

				if ((S[i][index] == '+' && part <= 0) || (S[i][index] == '0' && part != 0)
						|| (S[i][index] == '-' && part >= 0)) {
					continue next;
				}
			}

			getAnswer(index + 1);
		}
	}
}
