import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
        System.out.println(routerDistance(house, N, C));
    }

    public static int routerDistance(int[] arr, int N, int C) {
        int s = 1;
        int e = arr[N - 1] - arr[0];
        while (s <= e) {
            int mid = (s + e) / 2;
            boolean status = false;
            int a = arr[0];
            int b = 1;
            int cnt = 1;
            while (true) {
                if (b >= N || cnt >= C)
                    break;
                if (arr[b] - a >= mid) {
                    cnt++;
                    a = arr[b];
                }
                b++;
            }
            if (cnt >= C)
                s = mid + 1;
            else e = mid - 1;
        }
        return e;
    }
}