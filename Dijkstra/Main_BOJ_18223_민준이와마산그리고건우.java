import java.util.*;
import java.io.*;

public class Main_BOJ_18223_민준이와마산그리고건우 {
	static int V, E, P;
	static LinkedList<Node>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		adj = new LinkedList[V + 1];
		for (int i = 0; i <= V; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj[a].offer(new Node(b, c, false));
			adj[b].offer(new Node(a, c, false));
		}

		System.out.println(findMinDistance() ? "SAVE HIM" : "GOOD BYE");
	}

	private static boolean findMinDistance() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];

		int[] distance = new int[V + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		distance[1] = 0;
		pq.offer(new Node(1, 0, false));
		while (!pq.isEmpty()) {
			Node v = pq.poll();

			if (visited[v.index])
				continue;
			visited[v.index] = true;

			if (v.index == V) {
				return v.visited;
			}

			if (v.index == P)
				v.visited = true;
			for (Node next : adj[v.index]) {
				if (distance[next.index] >= distance[v.index] + next.weight) {
					distance[next.index] = distance[v.index] + next.weight;
					pq.offer(new Node(next.index, distance[next.index], v.visited));
				}
			}

		}
		return false;
	}

	static class Node implements Comparable<Node> {
		int index;
		int weight;
		boolean visited;

		Node(int index, int weight, boolean visited) {
			this.index = index;
			this.weight = weight;
			this.visited = visited;
		}

		@Override
		public int compareTo(Node o) {
			if (this.weight != o.weight)
				return this.weight - o.weight;
			else if (this.visited && !o.visited)
				return -1;
			else if (!this.visited && o.visited)
				return 1;
			else
				return 0;
		}
	}
}