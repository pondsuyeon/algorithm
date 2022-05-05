package Y22.M05.D05;

import java.io.*;
import java.util.*;

public class Main_BOJ_11985_오렌지출하 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] dp = new long[N + 1];
        long[] A = new long[N + 1];


        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
            dp[i] = (long) K * i;
        }

        for (int i = 0; i < N; i++) {
            long min = A[i + 1];
            long max = A[i + 1];

            for (int j = 1; i + j <= N && j <= M; j++) {
                min = Math.min(min, A[i + j]);
                max = Math.max(max, A[i + j]);

                dp[i + j] = Math.min(dp[i + j], dp[i] + K + j * (max - min));
            }
        }

        System.out.println(dp[N]);
    }
}
