import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[] A;
	static LinkedList<Integer>[] adj;
	static boolean[] visited;
	static int cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1];
		visited = new boolean[N + 1];
		adj = new LinkedList[N + 1];

		for (int i = 0; i <= N; i++) {
			adj[i] = new LinkedList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[v].offer(w);
			adj[w].offer(v);
		}

		int ans = 0;
		boolean flag = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				cost = Integer.MAX_VALUE;
				makeFriends(i);
				ans += cost;
				if (ans > K) {
					flag = false;
					break;
				}
			}
		}
		System.out.println(flag ? ans : "Oh no");
	}

	private static void makeFriends(int v) {
		visited[v] = true;
		cost = Math.min(cost, A[v]);

		for (int next : adj[v]) {
			if (!visited[next]) {
				makeFriends(next);
			}
		}
	}
}