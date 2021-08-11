import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        long[][] dp = new long[N - 1][21];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][arr[0]]++;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] == 0) continue;
                if (j - arr[i] >= 0) dp[i][j - arr[i]] += dp[i - 1][j];
                if (j + arr[i] <= 20) dp[i][j + arr[i]] += dp[i - 1][j];
            }
        }
        System.out.println(dp[N - 2][arr[N - 1]]);
    }
}