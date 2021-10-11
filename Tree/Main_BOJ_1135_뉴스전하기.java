package algorithm.M10.D11;

import java.io.*;
import java.util.*;

public class Main_BOJ_1135_뉴스전하기 {
	static LinkedList<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		adj = new LinkedList[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new LinkedList<>();
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p == -1)
				continue;

			adj[p].add(i);
		}
		System.out.println(call(0));
	}

	private static int call(int v) {
		ArrayList<Integer> list = new ArrayList<>();

		for (int child : adj[v]) {
			list.add(call(child));
		}

		int res = 0;
		Collections.sort(list, Collections.reverseOrder());

		for (int i = 0; i < list.size(); i++) {
			res = Math.max(res, 1 + i + list.get(i));
		}

		return res;
	}

}