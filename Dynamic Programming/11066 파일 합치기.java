import java.io.*;
import java.util.*;

public class Main {
    static int[] preSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K];
            int[][] dp = new int[K][K];

            preSum = new int[K];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                if (i == 0) continue;
                preSum[i] = preSum[i - 1] + files[i];
            }
            sb.append(getMinCost(0, K - 1, files, dp)).append("\n");
        }
        System.out.println(sb);
    }

    static int getMinCost(int s, int e, int[] files, int[][] dp) {
        if (s != e) {
            if (dp[s][e] == 0) {
                int sum = preSum[e] - preSum[s] + files[s];
                int temp = Integer.MAX_VALUE;
                for (int i = s; i < e; i++) {
                    temp = Math.min(temp, sum + getMinCost(s, i, files, dp) + getMinCost(i + 1, e, files, dp));
                }
                dp[s][e] = temp;
            }
        }
        return dp[s][e];
    }
}