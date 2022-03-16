import java.io.*;
import java.util.*;

public class Main_BOJ_2780_비밀번호 {

    static int mod = 1234567;
    static int max = 1001;
    static int[][] dp = new int[10][max];
    static int[] sum = new int[max];

    static int[][] adj = {
            {7},
            {2, 4},
            {1, 3, 5},
            {2, 6},
            {1, 5, 7},
            {2, 4, 6, 8},
            {3, 5, 9},
            {0, 4, 8},
            {5, 7, 9},
            {6, 8},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        init();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(sum[N]).append("\n");
        }
        System.out.print(sb);
    }

    private static void init() {

        for (int i = 1; i < max; i++) {

            for (int j = 0; j < 10; j++) {
                if (i == 1) {
                    dp[j][i] = 1;
                } else {


                    for (int next : adj[j]) {
                        dp[j][i] = (dp[j][i] + dp[next][i - 1]) % mod;
                    }
                }

                sum[i] = (sum[i] + dp[j][i]) % mod;
            }
        }


    }


}
