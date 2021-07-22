import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] lastYear = new int[n];
            boolean[][] thisYear = new boolean[n + 1][n + 1];
            int[] count = new int[n + 1];
            int[] order = new int[n];
            Arrays.fill(order, -1);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                lastYear[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < n - 1; i++) {
                int u = lastYear[i];
                for (int j = i + 1; j < n; j++) {
                    int v = lastYear[j];

                    thisYear[u][v] = true;
                    count[u]++;
                }
            }
            int m = Integer.parseInt(br.readLine());

            boolean flag = true;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (thisYear[a][b]) {
                    count[a]--;
                    thisYear[a][b] = false;

                    count[b]++;
                    thisYear[b][a] = true;
                } else if (thisYear[b][a]) {
                    count[b]--;
                    thisYear[b][a] = false;

                    count[a]++;
                    thisYear[a][b] = true;
                } else {
                    flag = false;
                }
            }
            if (!flag) {
                System.out.println("?");
                continue;
            }

            for (int i = 1; i <= n; i++) {
                int c = count[i];

                if (order[c] == -1) {
                    order[c] = i;
                } else {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                System.out.println("IMPOSSIBLE");
            } else {
                for (int i = n - 1; i >= 0; i--) {
                    System.out.print(order[i] + " ");
                }
                System.out.println();
            }
        }
    }
}