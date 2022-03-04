import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_15657_N과M_8 {

    static int N, M;
    static int[] selected;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        NM(0, sb);

        System.out.print(sb);
    }

    private static void NM(int count, StringBuilder sb) {

        if (count >= M) {
            for (int num : selected) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i == 0 || selected[count - 1] < arr[i]) {
                selected[count] = arr[i];
                NM(count + 1, sb);
            }
        }
    }
}
