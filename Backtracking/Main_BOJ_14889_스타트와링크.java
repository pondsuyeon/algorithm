import java.io.*;
import java.util.*;

public class Main_BOJ_14889_스타트와링크 {
    static int N;
    static int[][] S;
    static boolean[] selected;

    static int totalSum = 0;

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        selected = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
                totalSum += S[i][j];
            }
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    private static void dfs(int index, int count) {
        if (count == N / 2) {
            int selectedSum = 0;
            int unSelectedSum = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (selected[i] && selected[j]) selectedSum += S[i][j] + S[j][i];
                    else if (!selected[i] && !selected[j]) unSelectedSum += S[i][j] + S[j][i];
                }
            }

            ans = Math.min(ans, Math.abs(selectedSum - unSelectedSum) / 2);
            return;
        }
        if (index >= N) return;

        dfs(index + 1, count);

        selected[index] = true;
        dfs(index + 1, count + 1);
        selected[index] = false;
    }
}
