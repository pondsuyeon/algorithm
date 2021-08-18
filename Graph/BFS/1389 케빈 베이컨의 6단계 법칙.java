import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static LinkedList<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new LinkedList[N + 1];

		for (int i = 0; i <= N; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adj[u].offer(v);
			adj[v].offer(u);
		}
		int minIndex = -1;
		int minBacon = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int result = bacon(i);
			if (minBacon > result) {
				minIndex = i;
				minBacon = result;
			}
		}

		System.out.println(minIndex);
	}

	private static int bacon(int start) {
		Queue<Integer> q = new LinkedList<>();
		int result = 0;

		boolean[] visited = new boolean[N + 1];
		int depth = -1;

		visited[start] = true;
		q.offer(start);

		while (!q.isEmpty()) {
			int size = q.size();

			result += ++depth * size;

			while (size-- > 0) {
				int v = q.poll();

				for (int next : adj[v]) {
					if (!visited[next]) {
						visited[next] = true;
						q.offer(next);
					}
				}
			}
		}
		return result;
	}
}