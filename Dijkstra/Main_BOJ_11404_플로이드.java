import java.io.*;
import java.util.*;

public class Main_BOJ_11404_플로이드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] d = new int[N + 1][N + 1];


        for (int i = 0; i <= N; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            d[a][b] = Math.min(d[a][b], c);
        }


        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j || d[i][k] == Integer.MAX_VALUE || d[k][j] == Integer.MAX_VALUE) continue;
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(d[i][j] != Integer.MAX_VALUE ? d[i][j] : 0).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
