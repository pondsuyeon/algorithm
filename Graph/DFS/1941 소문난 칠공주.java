import java.io.*;
import java.text.DateFormatSymbols;
import java.util.Arrays;

public class Main {
    static char[][] seat = new char[5][5];
    static boolean[][] checked = new boolean[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        for (int i = 0; i < 5; i++) {
            seat[i] = br.readLine().toCharArray();
        }
        ans += sevenPrincesses(0, 0, 0);
        checked[0][0] = true;
        ans += sevenPrincesses(0, 0, 1);
        System.out.println(ans);
    }

    static int sevenPrincesses(int x, int y, int depth) {
        int ans = 0;
        if (depth == 7) {
            int num = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (checked[i][j] && seat[i][j] == 'S') num++;
                    if (num >= 4) break;
                }
                if (num >= 4) break;
            }

            if (num >= 4 && isPrincessGroup()) {
                return 1;
            }
            return 0;
        }

        for (int i = x; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == x && j <= y) continue;

                if (!checked[i][j]) {
                    checked[i][j] = true;
                    ans += sevenPrincesses(i, j, depth + 1);
                    checked[i][j] = false;
                }
            }
        }
        return ans;
    }

    static boolean isPrincessGroup() {
        for (int i = 0; i < 5; i++) {
            Arrays.fill(visited[i], false);
        }

        int tx = -1;
        int ty = -1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (checked[i][j]) {
                    tx = i;
                    ty = j;
                    break;
                }
            }
            if (tx != -1) break;
        }
        return DFS(tx, ty) == 7;
    }

    static int DFS(int x, int y) {
        int ans = 0;
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (tx >= 0 && tx < 5 && ty >= 0 && ty < 5 && checked[tx][ty] && !visited[tx][ty]) {
                ans += DFS(tx, ty);
            }
        }

        return 1 + ans;
    }
}