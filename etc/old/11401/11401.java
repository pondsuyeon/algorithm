import java.io.*;
import java.util.*;

public class Main {
    public static int p = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] fac = new long[N + 1];
        fac[0] = 1;
        for (int i = 1; i <= N; i++) {
            fac[i] = (fac[i - 1] * i) % p;
        }
        long pp = pow((fac[K] * fac[N - K]) % p, p - 2);
        long ans = (fac[N] * pp) % p;
        System.out.println(ans);
    }

    public static long pow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long n = pow(a, b / 2) % p;
        long temp = (n * n) % p;
        if (b % 2 == 0) {
            return temp;
        } else {
            return (a * temp) % p;
        }

    }
}
