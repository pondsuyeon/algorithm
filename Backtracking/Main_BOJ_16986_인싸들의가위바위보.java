package algorithm.etc;

import java.io.*;
import java.util.*;

// 문제는 백트래킹 문제인데 이제 순열을 곁들인..
public class Main_BOJ_16986_인싸들의가위바위보 {
	static int N, K;
	static int[][] A;

	static int[][] sign;
	static int[] signIndex;
	static int[] winCount;

	static boolean finished = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][N + 1];

		sign = new int[3][20];
		signIndex = new int[3];
		winCount = new int[3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 20; j++) {
				sign[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		permutation(0, 0);

		System.out.println(finished ? 1 : 0);
	}

	private static void permutation(int count, int flag) {
		if (finished)
			return;
		if (count == N) {
			signIndex = new int[3];
			winCount = new int[3];
			if (rockScissorsPaper(0, 1))
				finished = true;
			return;
		}

		for (int i = 1; i <= N; i++) {
			if ((flag & 1 << i) != 0)
				continue;

			sign[0][count] = i;
			permutation(count + 1, flag | 1 << i);
		}
	}

	private static boolean rockScissorsPaper(int player1, int player2) {
		if (winCount[0] == K) {
			return true;
		}
		if (signIndex[0] == N || winCount[1] == K || winCount[2] == K) {
			return false;
		}
		int s1 = sign[player1][signIndex[player1]++];
		int s2 = sign[player2][signIndex[player2]++];

		int nextPlayer = 3 - player1 - player2;

		if (A[s1][s2] == 2) {
			winCount[player1]++;
			return rockScissorsPaper(player1, nextPlayer);
		} else if (A[s1][s2] == 1) {
			int winPlayer = Math.max(player1, player2);
			winCount[winPlayer]++;
			return rockScissorsPaper(winPlayer, nextPlayer);
		} else {
			winCount[player2]++;
			return rockScissorsPaper(player2, nextPlayer);
		}
	}
}