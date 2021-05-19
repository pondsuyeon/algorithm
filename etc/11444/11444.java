import java.io.*;
import java.util.*;

public class Main {
    public static int p = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        System.out.println(matrixFib(n));
    }

    public static long matrixFib(long n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else {
            long[][] A = matrixPow(n / 2);
            if (n % 2 == 0) {
                return A[0][1];
            } else {
                return A[1][1];
            }
        }
    }

    public static long[][] matrixPow(long n) {
        long[][] A = {{1, 1}, {1, 2}};
        if (n == 1) {
            return A;
        } else {
            long[][] temp = matrixPow(n / 2);
            temp = matrixMultiple(temp, temp);
            if (n % 2 == 1) {
                temp = matrixMultiple(temp, A);
            }
            return temp;
        }
    }

    public static long[][] matrixMultiple(long[][] a, long[][] b) {
        int len = a.length;
        long[][] res = new long[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % p;
                }
            }
        }
        return res;
    }
}