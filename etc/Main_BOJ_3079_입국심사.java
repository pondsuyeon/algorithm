import java.io.*;
import java.util.*;

public class Main_BOJ_3079_입국심사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] T = new long[N];

        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(br.readLine());
        }

        long time = 0;
        long s = 1;
        long e = 1000000000000000000L;

        while (s <= e) {
            long mid = (s + e) / 2;

            if (check(mid, T, M)) {
                time = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        System.out.println(time);
    }

    private static boolean check(long time, long[] T, int M) {

        long res = 0;
        for (long t : T) {
            res += time / t;
            if (res >= M) return true;
        }

        return false;
    }
}
