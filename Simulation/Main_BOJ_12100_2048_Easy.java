package algorithm.M09.D0922;

import java.io.*;
import java.util.*;

public class Main_BOJ_12100_2048_Easy {

	static int N;
	static int[][] origin;
	static int[][] board;

	static int[] selected = new int[5];

	static int maxBlock = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		origin = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		perm(0);
		System.out.println(maxBlock);
	}

	private static void perm(int count) {
		if (count == 5) {
			maxBlock = Math.max(maxBlock, moveAndgetMaxBlock());
			return;
		}

		for (int i = 0; i < 4; i++) {
			selected[count] = i;
			perm(count + 1);
		}
	}

	private static int moveAndgetMaxBlock() {
		copyOriginBoard();

		for (int m : selected) {
			move(m);
		}

		return getMaxBlock();
	}

	private static void copyOriginBoard() {
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			board[i] = origin[i].clone();
		}
	}

	private static int getMaxBlock() {
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res = Math.max(res, board[i][j]);
			}
		}
		return res;
	}

	private static void move(int m) {
		if (m == 0) {
			for (int c = 0; c < N; c++) {
				Queue<Integer> q = new LinkedList<>();

				for (int r = 0; r < N; r++) {
					if (board[r][c] != 0) {
						q.offer(board[r][c]);
						board[r][c] = 0;
					}
				}

				int row = 0;

				while (row < N && !q.isEmpty()) {
					int temp = q.poll();

					if (!q.isEmpty() && temp == q.peek()) {
						q.poll();
						board[row][c] = temp * 2;
					} else {
						board[row][c] = temp;
					}
					row++;
				}
			}
		} else if (m == 1) {
			for (int c = 0; c < N; c++) {
				Queue<Integer> q = new LinkedList<>();

				for (int r = N - 1; r >= 0; r--) {
					if (board[r][c] != 0) {
						q.offer(board[r][c]);
						board[r][c] = 0;
					}
				}

				int row = N - 1;

				while (row >= 0 && !q.isEmpty()) {
					int temp = q.poll();

					if (!q.isEmpty() && temp == q.peek()) {
						q.poll();
						board[row][c] = temp * 2;
					} else {
						board[row][c] = temp;
					}
					row--;
				}
			}
		} else if (m == 2) {
			for (int r = 0; r < N; r++) {
				Queue<Integer> q = new LinkedList<>();

				for (int c = 0; c < N; c++) {
					if (board[r][c] != 0) {
						q.offer(board[r][c]);
						board[r][c] = 0;
					}
				}

				int col = 0;

				while (col < N && !q.isEmpty()) {
					int temp = q.poll();

					if (!q.isEmpty() && temp == q.peek()) {
						q.poll();
						board[r][col] = temp * 2;
					} else {
						board[r][col] = temp;
					}
					col++;
				}
			}
		} else {
			for (int r = 0; r < N; r++) {
				Queue<Integer> q = new LinkedList<>();

				for (int c = N - 1; c >= 0; c--) {
					if (board[r][c] != 0) {
						q.offer(board[r][c]);
						board[r][c] = 0;
					}
				}

				int col = N - 1;

				while (col >= 0 && !q.isEmpty()) {
					int temp = q.poll();

					if (!q.isEmpty() && temp == q.peek()) {
						q.poll();
						board[r][col] = temp * 2;
					} else {
						board[r][col] = temp;
					}
					col--;
				}
			}
		}
	}

}
