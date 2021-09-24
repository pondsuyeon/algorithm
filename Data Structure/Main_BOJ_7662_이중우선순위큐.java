package algorithm.M09.D0923;

import java.io.*;
import java.util.*;

public class Main_BOJ_7662_이중우선순위큐 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());

			PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> min = new PriorityQueue<>();

			HashMap<Integer, Integer> map = new HashMap<>();

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());

				String cmd = st.nextToken();
				int num = Integer.parseInt(st.nextToken());

				if (cmd.equals("I")) {

					if (map.containsKey(num)) {
						map.put(num, map.get(num) + 1);
					} else {
						map.put(num, 1);
					}

					max.offer(num);
					min.offer(num);
				} else {
					if (num == -1) {
						while (!min.isEmpty() && map.get(min.peek()) == 0) {
							min.poll();
						}
						if (!min.isEmpty()) {
							map.put(min.peek(), map.get(min.poll()) - 1);
						}
					} else {
						while (!max.isEmpty() && map.get(max.peek()) == 0) {
							max.poll();
						}
						if (!max.isEmpty()) {
							map.put(max.peek(), map.get(max.poll()) - 1);

						}
					}
				}
			}
			
			while (!min.isEmpty() && map.get(min.peek()) == 0) {
				min.poll();
			}
			while (!max.isEmpty() && map.get(max.peek()) == 0) {
				max.poll();
			}
			
			if (min.isEmpty() || max.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(max.poll() + " " + min.poll() + "\n");
			}
		}
		System.out.print(sb.toString());
	}

}
