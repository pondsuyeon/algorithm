import java.io.*;
import java.util.*;

public class Main_BOJ_2933_미네랄 {

    static int N;
    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visited = new boolean[R][C];

            shoot(R - Integer.parseInt(st.nextToken()), i % 2);

            for (int j = 0; j < C; j++) {
                if (map[R - 1][j] == 'x' && !visited[R - 1][j]) {
                    dfs(R - 1, j);
                }
            }

            move();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    private static void shoot(int H, int D) {

        if (D == 0) {
            for (int i = 0; i < C; i++) {
                if (map[H][i] == 'x') {
                    map[H][i] = '.';
                    break;
                }
            }
        } else {
            for (int i = C - 1; i >= 0; i--) {
                if (map[H][i] == 'x') {
                    map[H][i] = '.';
                    break;
                }
            }
        }
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == '.') continue;
            dfs(nr, nc);
        }
    }

    private static void move() {

        char[][] temp = new char[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(temp[i], '.');
        }

        List<Node> parts = new ArrayList<>();

        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.' || visited[i][j]) {
                    temp[i][j] = map[i][j];
                    continue;
                }

                parts.add(new Node(i, j));
            }
        }

        int max = R;

        for (Node part : parts) {
            for (int i = 1; i <= max; i++) {
                if (part.r + i >= R || temp[part.r + i][part.c] != '.') {
                    max = i - 1;
                    break;
                }
            }
        }

        for (Node part : parts) {
            temp[part.r + max][part.c] = 'x';
        }

        map = temp;
    }

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
