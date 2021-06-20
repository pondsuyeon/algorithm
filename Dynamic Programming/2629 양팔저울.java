import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] weight;
    static int[] result;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        int C = Integer.parseInt(br.readLine());
        int[] marble = new int[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            marble[i] = Integer.parseInt(st.nextToken());
        }
        
        result = new int[100000];
        checked = new boolean[40001];
        int len = 0;

        for (int i = 0; i < N; i++) {
            if (!checked[weight[i]]) {
                checked[weight[i]] = true;
            }
            result[len++] = weight[i];
            int tmp = len;

            for (int j = 0; j < tmp - 1; j++) {

                int a = result[j] + weight[i];
                if (!checked[a]) {
                    checked[a] = true;
                    result[len++] = a;
                }

                int b = Math.abs(result[j] - weight[i]);
                if (!checked[b]) {
                    checked[b] = true;
                    result[len++] = b;
                }
            }
        }
        for (int i = 0; i < C; i++) {
            if (checked[marble[i]]) {
                System.out.print("Y ");
            } else {
                System.out.print("N ");
            }
        }
    }

}