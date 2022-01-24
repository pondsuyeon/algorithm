import java.io.*;
import java.util.*;

public class Main_BOJ_1365_꼬인전깃줄 {

    static int N;
    static int[] arr;
    static List<Integer> lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        lis = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if (lis.size() == 0 || arr[i] > lis.get(lis.size() - 1)) {
                lis.add(arr[i]);
            } else {

                int left = 0;
                int right = lis.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (lis.get(mid) >= arr[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }

                lis.set(right, arr[i]);
            }
        }

        System.out.println(N - lis.size());
    }
}
