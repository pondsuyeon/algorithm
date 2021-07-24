import java.io.*;
import java.util.*;

public class Main {
    static int N, K, R;
    static boolean[][][][] isRoad;
    static boolean[][] visited;
    static boolean[][] isCow;
    static Cow[] cows;

    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        isRoad = new boolean[N + 1][N + 1][N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        isCow = new boolean[N + 1][N + 1];
        cows = new Cow[N];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            isRoad[r1][c1][r2][c2] = true;
            isRoad[r2][c2][r1][c1] = true;
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cows[i] = new Cow(r, c);
            isCow[r][c] = true;
        }

        int ans = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(visited[j], false);
            }
            DFS(cows[i]);
            ans += getCountOfNonVisited();
        }
        System.out.println(ans / 2);
    }

    public static int getCountOfNonVisited() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j] && isCow[i][j]) count++;
            }
        }
        return count;
    }

    public static void DFS(Cow cow) {
        visited[cow.r][cow.c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = cow.r + d[i][0];
            int nc = cow.c + d[i][1];

            if (nr > 0 && nr <= N && nc > 0 && nc <= N && !visited[nr][nc] && !isRoad[cow.r][cow.c][nr][nc]) {
                DFS(new Cow(nr, nc));
            }
        }
    }

    static class Cow {
        int r;
        int c;

        Cow(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}