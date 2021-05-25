import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int[][] map;
    static int ans = -1;
    static boolean finished = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 2;
            map[a][b + 1] = 1;
        }
        for (int depth = 0; depth <= 3; depth++) {
            ans = depth;
            ladderDFS(0);
            if (finished) break;
        }
        System.out.println(finished ? ans : -1);
    }

    static void ladderDFS(int count) {
        if (finished) return;
        if (ans == count) {
            if (ladderCheck()) {
                finished = true;
            }
            return;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 2;
                    map[i][j + 1] = 1;

                    ladderDFS(count + 1);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    static boolean ladderCheck() {

        for (int i = 1; i <= N; i++) {
            int tr = 1;
            int tc = i;

            while (tr <= H) {
                if (map[tr][tc] == 1) tc--;
                else if (map[tr][tc] == 2) tc++;
                tr++;
            }
            if (tc != i) return false;
        }
        return true;
    }
}