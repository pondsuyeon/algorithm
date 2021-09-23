package algorithm.M09.D0923;

import java.io.*;
import java.util.*;

public class Main_BOJ_17825_주사위윷놀이 {

	static int[] cases = new int[10];
	static LinkedList<Integer>[] adj = new LinkedList[33];

	static int[] scores = new int[33];

	static int[] players = new int[4];

	static int maxScore = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 10; i++) {
			cases[i] = Integer.parseInt(st.nextToken());
		}

		initMap();

		perm(0, 0);
		System.out.println(maxScore);
	}

	private static void perm(int count, int score) {
		if (count == 10) {
			maxScore = Math.max(maxScore, score);
			return;
		}

		loop: for (int i = 0; i < 4; i++) {
			int before = players[i];
			if (before == 32)
				continue;
			int now = before;
			for (int t = 0; t < cases[count]; t++) {
				if (now == 32)
					break;
				if (t == 0)
					now = adj[now].getLast();
				else
					now = adj[now].getFirst();
			}

			for (int j = 0; j < 4; j++) {
				if (now == 32)
					break;
				if (i == j)
					continue;
				if (now == players[j])
					continue loop;
			}

			players[i] = now;
//			System.out.println(count+"번째 player"+i+"번 "+before+"--->"+now);
			perm(count + 1, score + scores[now]);
			players[i] = before;
		}
	}

	private static void initMap() {

		for (int i = 0; i < 33; i++) {
			adj[i] = new LinkedList<>();
		}

		// 0번 시작, 32번 도착
		for (int i = 1; i <= 20; i++) {
			adj[i - 1].offer(i);
			scores[i] = 2 * i;
		}

		adj[20].offer(32);

		adj[5].offer(21);

		for (int i = 1; i <= 3; i++) {
			adj[20 + i].offer(20 + i + 1);
			scores[20 + i] = 10 + 3 * i;
		}
		scores[24] = 25;

		adj[10].offer(25);

		adj[25].offer(26);
		scores[25] = 22;
		adj[26].offer(24);
		scores[26] = 24;

		adj[15].offer(27);

		adj[27].offer(28);
		scores[27] = 28;
		adj[28].offer(29);
		scores[28] = 27;
		adj[29].offer(24);
		scores[29] = 26;

		adj[24].offer(30);
		adj[30].offer(31);
		scores[30] = 30;
		adj[31].offer(20);
		scores[31] = 35;
	}
}
