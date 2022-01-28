import java.util.*;
import java.io.*;

public class Main_BOJ_3020_개똥벌레 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[H + 1];    // 석순
        int[] arr2 = new int[H + 1];    // 종유석

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) arr1[h]++;
            else arr2[h]++;
        }

        for (int i = H - 1; i >= 1; i--) {
            arr1[i] += arr1[i + 1];
            arr2[i] += arr2[i + 1];
        }

        int min = N + 1;
        int count = 0;

        for (int i = 1; i <= H; i++) {
            int sum = arr1[i] + arr2[H - i + 1];

            if (sum < min) {
                min = sum;
                count = 1;
            } else if (sum == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }
}
