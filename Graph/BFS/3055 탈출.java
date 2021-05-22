import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();

        sc.nextLine();

        char[][] map = new char[R][C];
        int[][] wVal = new int[R][C];
        int[][] hVal = new int[R][C];

        Position S = null;

        for (int i = 0; i < R; i++) {
            String tmp = sc.nextLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'S') S = new Position(i, j);
            }
        }
        waterBFS(map, wVal);        // 물이 차는데 걸리는 시간을 BFS로 저장
        int answer = hedgehogBFS(S, map, hVal, wVal);   // 물에 따른 고슴도치의 이동 시간 BFS 탐색
        System.out.println((answer != -1) ? (answer - 1) : "KAKTUS");   // 걸리는 시간을 +1 해줬기 때문에 -1 해줘야 함.
    }

    public static void waterBFS(char[][] map, int[][] wVal) {
        int R = map.length;
        int C = map[0].length;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<Position> q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '*') {
                    q.offer(new Position(i, j));
                    wVal[i][j] = 1;
                }
            }
        }

        while (!q.isEmpty()) {
            Position v = q.poll();
            for (int i = 0; i < 4; i++) {
                int tr = v.r + dr[i];
                int tc = v.c + dc[i];

                // D와 X가 아닌 곳에 물이 차는데 걸리는 시간을 너비 우선 탐색으로 값 저장
                if (tr < R && tr >= 0 && tc < C && tc >= 0 && wVal[tr][tc] == 0 && map[tr][tc] != 'D' && map[tr][tc] != 'X') {
                    wVal[tr][tc] = wVal[v.r][v.c] + 1;
                    q.offer(new Position(tr, tc));
                }
            }
        }
    }

    public static int hedgehogBFS(Position S, char[][] map, int[][] hVal, int[][] wVal) {
        int R = map.length;
        int C = map[0].length;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<Position> q = new LinkedList<>();
        q.offer(S);
        hVal[S.r][S.c] = 1;

        while (!q.isEmpty()) {
            Position v = q.poll();

            for (int i = 0; i < 4; i++) {
                int tr = v.r + dr[i];
                int tc = v.c + dc[i];

                if (tr < R && tr >= 0 && tc < C && tc >= 0 && hVal[tr][tc] == 0 && map[tr][tc] != 'X' && map[tr][tc] != '*') {
                    // 이동하려는 곳이 물이 찰 예정이 없거나, 바로 같은 시간에 물이 차지 않는 곳인지 확인
                    if (wVal[tr][tc] == 0 || hVal[v.r][v.c] + 1 < wVal[tr][tc]) {
                        hVal[tr][tc] = hVal[v.r][v.c] + 1;
                        q.offer(new Position(tr, tc));

                        if (map[tr][tc] == 'D')     // 'D 동굴이면 종료'
                            return hVal[tr][tc];
                    }
                }
            }
        }

        return -1;
    }

    static class Position {
        int r;
        int c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}