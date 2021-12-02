package algorithm.M12.D02;

import java.io.*;
import java.util.*;

public class Main_BOJ_13904_과제 {

	static class Node {
		int d, w;

		Node(int d, int w) {
			this.d = d;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Node> homeworks = new PriorityQueue<>((o1, o2) -> o1.d - o2.d);
		PriorityQueue<Node> done = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			homeworks.add(new Node(d, w));
		}

		while (!homeworks.isEmpty()) {

			Node homework = homeworks.poll();

			if (done.size() < homework.d) {
				done.add(homework);
			} else if (done.peek().w < homework.w) {
				done.poll();
				done.add(homework);
			}

		}

		int ans = 0;

		while (!done.isEmpty()) {
			ans += done.poll().w;
		}

		System.out.println(ans);
	}

}
