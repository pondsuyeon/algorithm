import java.io.*;
import java.util.*;

public class Main_BOJ_1756_피자굽기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] d = new int[D + 1];
        int[] p = new int[N];
        d[0] = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= D; i++) {
            d[i] = Math.min(d[i - 1], Integer.parseInt(st.nextToken()));
        }

        boolean flag = true;
        int depth = D;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            while (depth > 0 && p[i] > d[depth]) {
                depth--;
            }

            if (depth == 0) {
                flag = false;
                break;
            }
            depth--;
        }

        System.out.println(flag ? (depth + 1) : 0);
    }
}
