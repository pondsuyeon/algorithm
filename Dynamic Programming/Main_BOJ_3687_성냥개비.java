package algorithm.M10.D29;

import java.io.*;
import java.util.*;

public class Main_BOJ_3687_성냥개비 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		
		String[] arr = {"1234", "234"};
		
		Arrays.sort(arr);
		
		int T = Integer.parseInt(br.readLine());

		String[] min = new String[101];
		String[] max = new String[101];

		min[2] = "1";
		min[3] = "7";
		min[4] = "4";
		min[5] = "2";
		min[6] = "6";
		min[7] = "8";

		max[2] = "1";
		max[3] = "7";

		for (int i = 2; i <= 100; i++) {
			if (i <= 21) {

				for (int j = i; i + j <= 21; j++) {
					if (min[j] == null)
						continue;

					String ij, ji;
					if (i == 6 && j == 6) {
						ij = ji = "60";
					} else if (i == 6) {
						ij = 6 + min[j];
						ji = min[j] + "0";
					} else if (j == 6) {
						ij = min[i] + "0";
						ji = 6 + min[i];
					} else {
						ij = min[i] + min[j];
						ji = min[j] + min[i];
					}

					String temp = (compare(ij, ji) < 0) ? ij : ji;

					if (min[i + j] == null)
						min[i + j] = temp;
					else {
						min[i + j] = (compare(min[i + j], temp) < 0) ? min[i + j] : temp;
					}
				}
			} else {
				min[i] = min[i - 7] + "8";
			}
		}

		for (int i = 4; i <= 100; i++) {
			max[i] = max[i - 2] + "1";
		}

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(min[n] + " " + max[n] + "\n");
		}
		System.out.print(sb.toString());
	}

	private static int compare(String a, String b) {
		int alen = a.length();
		int blen = b.length();

		if (alen != blen)
			return alen - blen;

		for (int i = 0; i < alen; i++) {
			if (a.charAt(i) != b.charAt(i))
				return a.charAt(i) - b.charAt(i);

		}

		return 0;
	}
}
