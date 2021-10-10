package algorithm.M10.D10;

import java.io.*;
import java.util.*;

public class Main_BOJ_17140_이차원배열과연산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int sec = -1;

		int R = 3;
		int C = 3;

		int[][] A = new int[100][100];

		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (sec++ < 100 && A[r][c] != k) {
			if (R >= C) {

				int maxCol = C;
				for (int i = 0; i < R; i++) {

					Map<Integer, Integer> map = new HashMap<>();

					for (int j = 0; j < C; j++) {
						if (A[i][j] == 0)
							continue;
						if (!map.containsKey(A[i][j])) {
							map.put(A[i][j], 1);
						} else {
							map.put(A[i][j], map.get(A[i][j]) + 1);
						}

						A[i][j] = 0;
					}

					List<Node> list = new ArrayList<>();

					for (int number : map.keySet()) {
						list.add(new Node(number, map.get(number)));
					}

					Collections.sort(list);

					int size = Math.min(list.size() * 2, 100);

					for (int j = 0; j < size / 2; j++) {
						A[i][2 * j] = list.get(j).number;
						A[i][2 * j + 1] = list.get(j).count;
					}

					maxCol = Math.max(maxCol, size);
				}
				C = maxCol;

			} else {

				int maxRow = R;
				for (int i = 0; i < C; i++) {

					Map<Integer, Integer> map = new HashMap<>();

					for (int j = 0; j < R; j++) {
						if (A[j][i] == 0)
							continue;
						if (!map.containsKey(A[j][i])) {
							map.put(A[j][i], 1);
						} else {
							map.put(A[j][i], map.get(A[j][i]) + 1);
						}
						A[j][i] = 0;
					}

					List<Node> list = new ArrayList<>();

					for (int number : map.keySet()) {
						list.add(new Node(number, map.get(number)));
					}

					Collections.sort(list);

					int size = Math.min(list.size() * 2, 100);

					for (int j = 0; j < size / 2; j++) {
						A[2 * j][i] = list.get(j).number;
						A[2 * j + 1][i] = list.get(j).count;
					}

					for (int j = size; j < C; j++) {
						A[j][i] = 0;
					}

					maxRow = Math.max(maxRow, size);
				}
				R = maxRow;

			}

		}

		System.out.println(A[r][c] == k ? sec : -1);
	}

	static class Node implements Comparable<Node> {
		int number;
		int count;

		Node(int number, int count) {
			this.number = number;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			if (this.count != o.count)
				return this.count - o.count;
			return this.number - o.number;
		}
	}
}
