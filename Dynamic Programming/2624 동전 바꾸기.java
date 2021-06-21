import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        Coin[] coins = new Coin[K + 1];

        int[][] dp = new int[K + 1][T + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = new Coin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            dp[i][0] = 1;

        }
        dp[0][0] = 1;
        // i개의 동전으로 j값을 만드는 경우
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= T; j++) {
                for (int c = 0; c <= coins[i].count; c++) {
                    if (j - c * coins[i].value < 0) break;
                    dp[i][j] += dp[i - 1][j - c * coins[i].value];
                }
            }
        }
        System.out.println(dp[K][T]);
    }

    static class Coin {
        int count;
        int value;

        public Coin(int value, int count) {
            this.value = value;
            this.count = count;

        }
    }
}