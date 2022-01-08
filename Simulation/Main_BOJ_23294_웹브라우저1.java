package algorithm.M01.D03;

import java.io.*;
import java.util.*;

public class Main_BOJ_23294_웹브라우저1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] CAP = new int[N + 1];

		int totalCache = 0;

		Deque<Integer> back = new LinkedList<>();
		Deque<Integer> front = new LinkedList<>();

		int now = -1;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			CAP[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			char type = st.nextToken().charAt(0);

			if (type == 'B') {
				if (back.isEmpty())
					continue;

				front.addLast(now);
				now = back.pollLast();
			} else if (type == 'F') {
				if (front.isEmpty())
					continue;

				back.addLast(now);
				now = front.pollLast();
			} else if (type == 'A') {
				int index = Integer.parseInt(st.nextToken());

				while (!front.isEmpty()) {
					totalCache -= CAP[front.pollLast()];
				}

				if (now != -1) {
					back.addLast(now);
				}
				now = index;
				totalCache += CAP[now];

				while (totalCache > C) {
					totalCache -= CAP[back.pollFirst()];
				}
			} else {
				Deque<Integer> compressed = new LinkedList<>();
				int before = -1;

				while (!back.isEmpty()) {
					int page = back.pollFirst();

					if (before == page) {
						totalCache -= CAP[page];
					} else {
						if (before != -1)
							compressed.addLast(before);
						before = page;

					}
				}

				if (before != -1)
					compressed.addLast(before);

				back = compressed;
			}
		}

		sb.append(now).append("\n");

		if (back.isEmpty())
			sb.append(-1);
		else {
			while (!back.isEmpty()) {
				sb.append(back.pollLast()).append(" ");
			}
		}
		sb.append("\n");

		if (front.isEmpty())
			sb.append(-1);
		else {
			while (!front.isEmpty()) {
				sb.append(front.pollLast()).append(" ");
			}
		}
		sb.append("\n");

		System.out.print(sb.toString());
	}

}
