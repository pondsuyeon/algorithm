package algorithm.M11.D30;

import java.io.*;
import java.util.*;

public class Main_BOJ_1322_X와K {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		char[] xbit = Integer.toBinaryString(X).toCharArray();
		char[] kbit = Integer.toBinaryString(K).toCharArray();

		int ki = kbit.length - 1;

		for (int i = 0; i < xbit.length; i++) {
			xbit[i] = xbit[i] == '0' ? '1' : '0';
		}

		for (int i = xbit.length - 1; i >= 0; i--) {
			if (xbit[i] == '0') {
				sb.insert(0, '0');
			} else {
				if (ki >= 0) {
					sb.insert(0, kbit[ki--]);
				}
			}
		}

		while (ki >= 0) {
			sb.insert(0, kbit[ki--]);
		}

		Long Y = Long.parseLong(sb.toString(), 2);
		System.out.println(Y);
	}
}
