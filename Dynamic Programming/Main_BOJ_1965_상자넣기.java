import java.io.*;
import java.util.*;

public class Main_BOJ_1965_상자넣기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            dp[num] = 1;

            for (int j = 1; j < num; j++) {
                if (dp[j] != 0) {
                    dp[num] = Math.max(dp[num], dp[j] + 1);
                }
            }
        }

        Arrays.sort(dp);
        System.out.println(dp[1000]);
    }
}
