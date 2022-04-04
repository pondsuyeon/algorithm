import java.io.*;
import java.util.*;

public class Main_BOJ_2616_소형기관차 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        int[][] dp = new int[4][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 3; i++) {
            for (int j = M * i; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], arr[j] - arr[j - M] + dp[i - 1][j - M]);
            }
        }

        System.out.println(dp[3][N]);
    }
}
