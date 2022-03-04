import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BOJ_15666_N과M_12 {

    static int N, M;
    static int[] arr;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        HashSet<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
        }

        arr = new int[set.size()];
        int index = 0;
        for (int num : set) {
            arr[index++] = num;
        }

        Arrays.sort(arr);

        NM(0, sb);

        System.out.print(sb);
    }

    private static void NM(int cnt, StringBuilder sb) {
        if (cnt >= M) {
            for (int num : selected) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int j : arr) {
            if (cnt == 0 || selected[cnt - 1] <= j) {
                selected[cnt] = j;
                NM(cnt + 1, sb);
            }
        }
    }
}
