import java.io.*;
import java.util.*;

public class Main_BOJ_1106_호텔 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] city = new int[N][2];
        int[] dp = new int[C + 1];

        Arrays.fill(dp, 12345689);

        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            city[i][0] = Integer.parseInt(st.nextToken());
            city[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < C; i++) {

            if (dp[i] == 12345689) continue;

            for (int j = 0; j < N; j++) {
                int next = Math.min(i + city[j][1], C);
                dp[next] = Math.min(dp[next], dp[i] + city[j][0]);
            }
        }

        System.out.println(dp[C]);
    }
}
