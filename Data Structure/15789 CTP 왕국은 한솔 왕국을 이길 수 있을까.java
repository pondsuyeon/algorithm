import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Main {
	static int N, M, C, H, K;
	static int[] parents;

	static class Node implements Comparable<Node> {
		int root;
		int count;

		public Node(int root, int count) {
			this.root = root;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			return -this.count + o.count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());

			union(X, Y);
		}

		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 1; i <= N; i++) {
			int root = find(i);
			if (!map.containsKey(root)) {
				map.put(root, 0);
			}
			map.put(root, map.get(root) + 1);
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			pq.offer(new Node(entry.getKey(), entry.getValue()));
		}

		H = find(H);
		C = find(C);
		int power = map.get(C);

		int k = 0;
		while (k < K && !pq.isEmpty()) {
			Node node = pq.poll();

			if (node.root == H)
				continue;

			power += node.count;
			union(C, node.root);
			C = find(C);
			k++;
		}
		System.out.println(power);
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return;
		if (x < y)
			parents[y] = x;
		else
			parents[x] = y;
	}

	private static int find(int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = find(parents[x]);
	}
}