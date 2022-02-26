
import java.io.*;
import java.util.*;

public class Main_BOJ_15671_오델로 {

    static int N = 6;
    static int M;

    static char[][] board = new char[N + 1][N + 1];
    static int[][] logs;

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            Arrays.fill(board[i], '.');
        }

        board[3][3] = board[4][4] = 'W';
        board[3][4] = board[4][3] = 'B';

        M = Integer.parseInt(br.readLine());

        logs = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            logs[i][0] = Integer.parseInt(st.nextToken());
            logs[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {

            othello(i % 2, logs[i][0], logs[i][1]);

        }

        int black = 0;
        int white = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(board[i][j]);

                if (board[i][j] == 'B') black++;
                else if (board[i][j] == 'W') white++;
            }
            sb.append("\n");
        }

        sb.append(black > white ? "Black" : "White");

        System.out.println(sb);
    }

    private static void othello(int turn, int r, int c) {

        board[r][c] = turn % 2 == 0 ? 'B' : 'W';

        for (int i = 0; i < 8; i++) {

            int nr = r + dr[i];
            int nc = c + dc[i];

            boolean flag = true;

            while (true) {

                if (nr <= 0 || nr > N || nc <= 0 || nc > N || board[nr][nc] == '.') {
                    flag = false;
                    break;
                }

                if (board[nr][nc] == board[r][c]) break;

                nr += dr[i];
                nc += dc[i];
            }

            if (flag) {

                nr = r + dr[i];
                nc = c + dc[i];

                while (board[nr][nc] != board[r][c]) {
                    board[nr][nc] = board[r][c];
                    nr += dr[i];
                    nc += dc[i];
                }
            }
        }

    }
}
