import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ElectricCord[] ec = new ElectricCord[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ec[i] = new ElectricCord(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(ec);

        int max = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (ec[j].to <= ec[i].to) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(N - max);
    }

    static class ElectricCord implements Comparable<ElectricCord> {
        int from;
        int to;

        public ElectricCord(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(ElectricCord electricCord) {
            return this.from - electricCord.from;
        }
    }
}