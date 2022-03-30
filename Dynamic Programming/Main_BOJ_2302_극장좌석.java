import java.io.*;
import java.util.*;

public class Main_BOJ_2302_극장좌석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dp = new int[41];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 2; i <= 40; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int ans = 1;

        int before = 0;

        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());

            ans *= dp[vip - before - 1];
            before = vip;
        }

        ans *= dp[N - before];

        System.out.println(ans);
    }
}
