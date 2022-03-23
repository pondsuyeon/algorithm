import java.io.*;
import java.util.*;

public class Main_BOJ_2502_떡먹는호랑이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] fib = new int[D + 1];

        fib[1] = 1;
        fib[2] = 1;

        for (int i = 3; i <= D; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        // fib[D - 2] * a + fib[D - 1] * b = K;
        int A = 0, B = 0;
        for (int i = 1; i * fib[D - 2] <= K; i++) {
            if ((K - i * fib[D - 2]) % fib[D - 1] == 0) {
                A = i;
                B = (K - i * fib[D - 2]) / fib[D - 1];
                break;
            }
        }

        System.out.println(A);
        System.out.println(B);
    }
}
