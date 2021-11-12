package algorithm.M11.D12;

import java.io.*;
import java.util.*;

public class Main_BOJ_13549_숨바꼭질3 {

	static final int MAX = 100001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		System.out.println(BFS(N, K));
	}

	private static int BFS(int N, int K) {
		boolean[] visited = new boolean[MAX];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(N, 0));

		while (!pq.isEmpty()) {

			int x = pq.peek().x;
			int s = pq.poll().s;

			if (x == K)
				return s;

			if (x < 0 || x >= MAX || visited[x])
				continue;

			visited[x] = true;

			pq.offer(new Node(x + 1, s + 1));
			pq.offer(new Node(x - 1, s + 1));
			pq.offer(new Node(x * 2, s));
		}

		return -1;
	}

	static class Node implements Comparable<Node> {
		int x, s;

		Node(int x, int s) {
			this.x = x;
			this.s = s;
		}

		@Override
		public int compareTo(Node o) {
			return this.s - o.s;
		}
	}
}
