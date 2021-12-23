package algorithm.M12.D22;

import java.io.*;
import java.util.*;

public class Main_BOJ_23059_리그오브레게노 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Map<String, List<String>> adj = new HashMap<>();
		Map<String, Integer> in = new HashMap<>();

		PriorityQueue<String> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();

			if (!adj.containsKey(A)) {
				adj.put(A, new ArrayList<>());
			}
			if (!adj.containsKey(B)) {
				adj.put(B, new ArrayList<>());
			}

			if (!in.containsKey(A)) {
				in.put(A, 0);
			}
			if (!in.containsKey(B)) {
				in.put(B, 0);
			}

			adj.get(A).add(B);
			in.put(B, in.get(B) + 1);
		}

		for (String key : in.keySet()) {
			if (in.get(key) == 0) {
				pq.add(key);
			}
		}

		int count = 0;
		while (!pq.isEmpty()) {

			int size = pq.size();

			PriorityQueue<String> temp = new PriorityQueue<>();

			while (size-- > 0) {

				String item = pq.poll();

				count++;

				sb.append(item).append("\n");

				for (String next : adj.get(item)) {
					in.put(next, in.get(next) - 1);

					if (in.get(next) == 0) {

						temp.add(next);
					}
				}
			}

			pq = temp;
		}
		if (count == in.size())
			System.out.print(sb.toString());
		else {
			System.out.println(-1);
		}
	}
}
