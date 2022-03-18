import java.io.*;
import java.util.*;

public class Main_BOJ_14675_단절점과단절선 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a]++;
            arr[b]++;
        }

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (t == 1) {
                sb.append(arr[k] > 1 ? "yes" : "no");
            } else {
                sb.append("yes");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
