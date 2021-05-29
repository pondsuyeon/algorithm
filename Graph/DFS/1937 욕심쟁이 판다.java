import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] bamboo;
    static int[][] depth;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        bamboo = new int[n][n];
        depth = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                bamboo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, pandaRoute(i, j));
            }
        }
        System.out.println(max);
    }

    static int pandaRoute(int x, int y) {

        if (depth[x][y] == 0) {
            int max = 0;
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];

                if (tx >= 0 && tx < n && ty >= 0 && ty < n && bamboo[x][y] < bamboo[tx][ty]) {
                    max = Math.max(max, pandaRoute(tx, ty));
                }
            }
            depth[x][y] = max + 1;
        }
        return depth[x][y];
    }
}
