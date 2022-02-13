
import java.io.*;
import java.util.*;

public class Main_BOJ_13397_구간나누기2 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMinOfMax());
    }

    private static int getMinOfMax() {

        int s = 0;
        int e = 9999;

        int ans = 0;
        while (s <= e) {
            int mid = (s + e) / 2;

            if (check(mid)) {
                e = mid - 1;
                ans = mid;
            } else {
                s = mid + 1;
            }
        }

        return ans;
    }

    private static boolean check(final int MAX_VALUE) {

        int max = arr[0];
        int min = arr[0];

        int count = 1;
        for (int i = 1; i < N; i++) {
            // [a b] c
            if (max < arr[i]) {
                if (arr[i] - min > MAX_VALUE) {
                    count++;
                    min = max = arr[i];
                } else {
                    max = arr[i];
                }
            } else if (min > arr[i]) {
                if (max - arr[i] > MAX_VALUE) {
                    count++;
                    max = min = arr[i];
                } else {
                    min = arr[i];
                }
            }

            if (count > M) return false;

        }

        return true;
    }
}
