import java.io.*;
import java.util.*;

class Main {
    static int R, C;
    static char[][] board;

    static int IR, IC;
    static int[] dr = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'I') {
                    IR = i;
                    IC = j;
                }
            }
        }
        String direction = br.readLine();
        boolean flag = true;
        for (int i = 0; i < direction.length(); i++) {
            if (!moveRobots(direction.charAt(i) - '0' - 1)) {
                System.out.println("kraj " + (i + 1));
                flag = false;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
    }

    static boolean moveRobots(int direction) {
        int r = IR + dr[direction];
        int c = IC + dc[direction];

        if (board[r][c] == 'R') return false;

        board[IR][IC] = '.';
        board[r][c] = 'I';
        IR = r;
        IC = c;

        int[][] tempBoard = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'R') {
                    PriorityQueue<Robot> robots = new PriorityQueue<>();

                    for (int k = 0; k < 9; k++) {
                        int tr = i + dr[k];
                        int tc = j + dc[k];
                        int td = Math.abs(IR - tr) + Math.abs(IC - tc);

                        if (tr < 0 || tr >= R || tc < 0 || tc >= C) continue;
                        if (board[tr][tc] == 'I') return false;

                        robots.offer(new Robot(tr, tc, td));
                    }
                    if (!robots.isEmpty()) {
                        Robot robot = robots.poll();
                        tempBoard[robot.r][robot.c]++;
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == IR && j == IC) continue;

                if (tempBoard[i][j] == 1) {
                    board[i][j] = 'R';
                } else {
                    board[i][j] = '.';
                }
            }
        }
        return true;
    }

    static class Robot implements Comparable<Robot> {
        int r;
        int c;
        int d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Robot robot) {
            return this.d - robot.d;
        }
    }
}