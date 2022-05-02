package Y22.M05.D02;

import java.io.*;
import java.util.*;

public class Main_BOJ_15558_점프게임 {

    static int N, K;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[2][N];
        visited = new boolean[2][N];

        for (int i = 0; i < 2; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j] - '0';
            }
        }

        System.out.println(jumpGame() ? 1 : 0);
    }

    private static boolean jumpGame() {

        int[] dx = {0, 0, 1};
        int[] dy = {1, -1, K};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        int second = 0;

        while (!q.isEmpty() && second < N) {

            int size = q.size();

            while (size-- > 0) {
                int[] now = q.poll();
                int x = now[0];
                int y = now[1];

                for (int i = 0; i < 3; i++) {
                    int nx = (x + dx[i]) % 2;
                    int ny = y + dy[i];

                    if (ny >= N) return true;

                    if (ny <= second || map[nx][ny] == 0 || visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }

            second++;
        }
        return false;
    }
}
