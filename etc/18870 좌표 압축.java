import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Node[] X = new Node[N];
		int[] ans = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			X[i] = new Node(i, Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(X);

		int now = 0;
		int count = 0;
		while (now < N) {
			int index = X[now].index;
			int value = X[now].value;
			ans[index] = count++;

			while (now + 1 < N && value == X[now + 1].value) {
				int temp = X[++now].index;
				ans[temp] = ans[index];
			}
			now++;
		}

		for (int i = 0; i < N; i++) {
			sb.append(ans[i] + " ");
		}
		System.out.println(sb.toString());
	}

	static class Node implements Comparable<Node> {
		int index;
		int value;

		Node(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}
	}
}