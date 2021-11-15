package algorithm.M11.D15;

import java.io.*;

public class Main_BOJ_15668_방번호 {

	static int MAX_LENGTH;
	static int N;

	static boolean[] used = new boolean[10];

	static boolean flag = false;
	static long A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp = br.readLine();

		MAX_LENGTH = temp.length();
		N = Integer.parseInt(temp);

		perm(0, 0);

		if (flag) {
			System.out.println(A + " + " + B);
		} else {
			System.out.println(-1);
		}
	}

	private static void perm(int count, long prev) {
		if (flag || prev > N || count > MAX_LENGTH)
			return;

		long tempB = N - prev;
		boolean possible = prev != 0 && N - prev != 0;
		boolean[] tempUsed = used.clone();

		while (tempB != 0) {
			int digit = (int)tempB % 10;

			if (tempUsed[digit]) {
				possible = false;
				break;
			}
			tempUsed[digit] = true;
			tempB /= 10;
		}

		if (possible) {
			flag = true;
			A = prev;
			B = N - prev;
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (used[i])
				continue;

			used[i] = true;
			perm(count + 1, prev * 10 + i);
			used[i] = false;
		}
	}
}
