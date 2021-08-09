import java.util.*;
import java.io.*;

public class Main {
	static int N, M, alphabet;
	static int[] bits;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		bits = new int[N];

		alphabet = (1 << ('z' - 'a' + 1)) - 1;

		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();

			for (char c : temp) {
				bits[i] |= (1 << (c - 'a'));
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int x = st.nextToken().charAt(0) - 'a';

			if (o == 1) {
				alphabet ^= (1 << x);
			} else {
				alphabet |= (1 << x);
			}

			int count = 0;
			for (int bit : bits) {
				if ((bit & alphabet) >= bit)
					count++;
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}