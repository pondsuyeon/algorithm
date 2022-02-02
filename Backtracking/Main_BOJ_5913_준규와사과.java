import java.io.*;
import java.util.*;

public class Main_BOJ_5913_준규와사과 {

    static int K;
    static int N = 5;
    static int[][] visited = new int[N + 1][N + 1];

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            visited[x][y] = -1;
        }

        backtracking(1, 1, 1);
        System.out.println(ans);
    }

    private static void backtracking(int x, int y, int count) {
        visited[x][y] = 1;

        if (count == N * N - K) {
            if (x == N && y == N)
                ans++;
        } else {
            if (!(x == N && y == N)) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx <= 0 || nx > N || ny <= 0 || ny > N || visited[nx][ny] != 0) continue;

                    backtracking(nx, ny, count + 1);
                }
            }
        }

        visited[x][y] = 0;
    }
}
