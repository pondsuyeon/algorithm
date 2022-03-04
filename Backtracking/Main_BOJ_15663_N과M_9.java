import java.io.*;
import java.util.*;

public class Main_BOJ_15663_N과M_9 {

    static int MAX = 10001;
    static int N, M;
    static int[] count = new int[MAX];
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            count[num]++;
        }

        NM(0, sb);
        System.out.print(sb);
    }

    private static void NM(int cnt, StringBuilder sb) {
        if (cnt >= M) {
            for (int num : selected) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < MAX; i++) {
            if (count[i] > 0) {
                count[i]--;
                selected[cnt] = i;
                NM(cnt + 1, sb);
                count[i]++;
            }
        }
    }
}
