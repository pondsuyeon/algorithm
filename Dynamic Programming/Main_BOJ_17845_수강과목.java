package Y22.M05.D14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17845_수강과목 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1];

        Node[] lecture = new Node[K + 1];

        lecture[0] = new Node(0, 0);
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());

            int I = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            lecture[i] = new Node(I, T);
        }

        Arrays.sort(lecture);

        for (int i = 1; i <= K; i++) {

            for (int j = 1; j <= N; j++) {

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (j >= lecture[i].T)
                    dp[i][j] = Math.max(dp[i][j], lecture[i].I + dp[i - 1][j - lecture[i].T]);
            }
        }

        System.out.println(dp[K][N]);
    }

    static class Node implements Comparable<Node> {
        int I, T;

        Node(int I, int T) {
            this.I = I;
            this.T = T;
        }

        @Override
        public int compareTo(Node o) {
            return this.T - o.T;
        }
    }
}
