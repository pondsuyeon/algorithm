import java.io.*;
import java.util.*;

public class Main_BOJ_2580_스도쿠 {

    static int[][] map = new int[9][9];
    static int[][] res = new int[9][9];

    static int[] row = new int[9];
    static int[] col = new int[9];
    static int[] rec = new int[9];

    static List<Node> zero = new ArrayList<>();
    static int zeroSize;

    static boolean finished = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0) {
                    zero.add(new Node(i, j));
                } else {
                    row[i] |= 1 << map[i][j];
                    col[j] |= 1 << map[i][j];
                    rec[(i / 3) * 3 + j / 3] |= 1 << map[i][j];
                }
            }
        }

        zeroSize = zero.size();

        dfs(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(res[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int index) {
        if (finished) {
            return;
        }

        if (index == zeroSize) {
            finished = true;
            for (int i = 0; i < 9; i++) {
                res[i] = map[i].clone();
            }
            return;
        }

        int x = zero.get(index).x;
        int y = zero.get(index).y;

        for (int i = 1; i <= 9; i++) {
            if (finished || (row[x] & 1 << i) != 0 || (col[y] & 1 << i) != 0 || (rec[(x / 3) * 3 + y / 3] & 1 << i) != 0)
                continue;

            map[x][y] = i;
            row[x] |= 1 << i;
            col[y] |= 1 << i;
            rec[(x / 3) * 3 + y / 3] |= 1 << i;

            dfs(index + 1);

            row[x] ^= 1 << i;
            col[y] ^= 1 << i;
            rec[(x / 3) * 3 + y / 3] ^= 1 << i;
            map[x][y] = 0;
        }
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
