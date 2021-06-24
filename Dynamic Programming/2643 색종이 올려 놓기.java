import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Paper[] papers = new Paper[N];

        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a > b) papers[i] = new Paper(a, b);
            else papers[i] = new Paper(b, a);
        }
        Arrays.sort(papers);
        int max = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (papers[j].isIncluded(papers[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    static class Paper implements Comparable<Paper> {
        int w;
        int h;

        public Paper(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public int getArea() {
            return this.w * this.h;
        }

        public boolean isIncluded(Paper paper) {
            if (this.w <= paper.w && this.h <= paper.h) return true;
            else return false;
        }

        @Override
        public int compareTo(Paper paper) {
            return this.getArea() - paper.getArea();
        }
    }
}