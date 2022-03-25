import java.io.*;
import java.util.*;

public class Main_BOJ_24041_성싶당밀키트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] S = new int[N];
        int[] L = new int[N];
        int[] O = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            S[i] = Integer.parseInt(st.nextToken());
            L[i] = Integer.parseInt(st.nextToken());
            O[i] = Integer.parseInt(st.nextToken());
        }

        long s = 1;
        long e = 2L * (int)Math.pow(10, 9);

        long ans = 0;

        while (s <= e) {

            long mid = (s + e) / 2;

            if (check(mid, S, L, O, N, G, K)) {
                ans = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean check(long x, int[] s, int[] l, int[] o, int N, int G, int K) {

        long total = 0;

        PriorityQueue<Long> q = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            long g = s[i] * Math.max(1, x - l[i]);
            if (o[i] == 0 && g > G) return false;
            total += g;
            if (o[i] == 1) q.add(g);
        }

        if (total <= G) return true;

        int count = K;
        while (!q.isEmpty() && count-- > 0) {

            total -= q.poll();

            if (total <= G) return true;
        }

        return false;
    }
}
