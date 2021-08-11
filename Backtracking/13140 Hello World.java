import java.io.*;

public class Main {
	static int N;
	static boolean[] selected = new boolean[10];
	static int[] comb = new int[7]; // h e l o w r d

	static int[] helloIndex = { 0, 1, 2, 2, 3 };
	static int[] worldIndex = { 4, 3, 5, 2, 6 };
	static int hello, world;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		if (N < 30000 || N > 200000) {
			flag = false;
		} else {
			helloWord(0);
		}

		if (flag) {
			System.out.printf("%7d\n", hello);
			System.out.printf("+%6d\n", world);
			System.out.println("-------");
			System.out.printf("%7d\n", N);
		} else {
			System.out.println("No Answer");
		}

	}

	private static void helloWord(int count) {
		if (flag)
			return;

		if (count == 7) {
			int h = 0;
			int w = 0;
			for (int index : helloIndex) {
				h = h * 10 + comb[index];
			}
			for (int index : worldIndex) {
				w = w * 10 + comb[index];
			}
			if (h + w == N) {
				flag = true;
				hello = h;
				world = w;
			}
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (!selected[i]) {
				if (i == 0) {
					if (count == 0 || count == 4)
						continue;
				}

				selected[i] = true;
				comb[count] = i;
				helloWord(count + 1);
				selected[i] = false;
			}
		}
	}
}