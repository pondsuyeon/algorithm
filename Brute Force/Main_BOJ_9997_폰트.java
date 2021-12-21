package algorithm.M12.D21;

import java.io.*;

public class Main_BOJ_9997_폰트 {

	static int[] bits;
	static int N;

	static int ans = 0;

	static int all = (1 << 26) - 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		bits = new int[N];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();

			for (int j = 0; j < temp.length(); j++) {
				bits[i] |= 1 << (temp.charAt(j) - 'a');
			}
		}

		makeSentence(0, 0);

		System.out.println(ans);
	}

	static void makeSentence(int index, int flag) {
		if (flag == all) {
			ans += 1 << (N + 1 - index) - 1;
			return;
		}
		if (index >= N) {
			return;
		}
		makeSentence(index + 1, flag);

		makeSentence(index + 1, flag | bits[index]);
	}
}
