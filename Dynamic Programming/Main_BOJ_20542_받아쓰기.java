package Y22.M05.D11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20542_받아쓰기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[][] dp = new long[n + 1][m + 1];

        char[] submit = new char[n + 1];
        char[] answer = new char[m + 1];

        char[] tempSubmit = br.readLine().toCharArray();
        char[] tempAnswer = br.readLine().toCharArray();

        for (int i = 0; i < n; i++) {
            submit[i + 1] = tempSubmit[i];
        }

        for (int i = 0; i < m; i++) {
            answer[i + 1] = tempAnswer[i];
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (submit[i] == answer[j]
                        || (submit[i] == 'i' && (answer[j] == 'j' || answer[j] == 'l'))
                        || (submit[i] == 'v' && answer[j] == 'w')
                ) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
