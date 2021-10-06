package algorithm.M10.D06;

import java.io.*;
import java.util.*;

public class Main_BOJ_9019_DSLR {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			sb.append(getCommand(A, B) + "\n");
		}

		System.out.print(sb.toString());
	}

	private static String getCommand(int a, int b) {
		Queue<Node> q = new LinkedList<>();

		boolean[] checked = new boolean[10000];

		checked[a] = true;
		q.offer(new Node(a, ""));

		while (!q.isEmpty()) {
			Node n = q.poll();

			if (n.n == b)
				return n.s;

			int temp = (n.n * 2) % 10000;
			if (!checked[temp]) {
				checked[temp] = true;
				q.offer(new Node(temp, n.s + 'D'));
			}

			temp = (10000 + n.n - 1) % 10000;
			if (!checked[temp]) {
				checked[temp] = true;
				q.offer(new Node(temp, n.s + 's'));
			}

			temp = (n.n % 1000) * 10 + (n.n / 1000);
			if (!checked[temp]) {
				checked[temp] = true;
				q.offer(new Node(temp, n.s + 'L'));
			}

			temp = (n.n % 10) * 1000 + n.n / 10;
			if (!checked[temp]) {
				checked[temp] = true;
				q.offer(new Node(temp, n.s + 'R'));
			}

		}

		return null;
	}

	static class Node {
		int n;
		String s;

		Node(int n, String s) {
			this.n = n;
			this.s = s;
		}
	}
}
