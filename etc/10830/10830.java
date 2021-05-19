import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] ans = matrixPow(A, B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] matrixPow(int[][] arr, long B) {
        int length = arr.length;
        int[][] res = new int[length][length];
        if (B == 0) {
            for (int i = 0; i < length; i++) {
                res[i][i] = 1;
            }
        } else if (B % 2 == 1) {
            res = matrixPow(arr, B / 2);
            res = matrixProduct(matrixProduct(res, res), arr);
        } else if (B % 2 == 0) {
            res = matrixPow(arr, B / 2);
            res = matrixProduct(res, res);
        }
        return res;
    }

    public static int[][] matrixProduct(int[][] a, int[][] b) {
        int length = a.length;
        int[][] res = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % 1000;
                }
            }
        }
        return res;
    }
}