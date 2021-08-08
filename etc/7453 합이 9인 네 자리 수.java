import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] arr;
    static int[] sumAB;
    static int[] sumCD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sumAB = new int[n * n];
        sumCD = new int[n * n];

        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumAB[index++] = arr[i][0] + arr[j][1];
            }
        }

        index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumCD[index++] = arr[i][2] + arr[j][3];
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        long res = 0;
        long before = 0;

        for (int i = 0; i < n * n; i++) {
            if (i > 0 && sumAB[i] == sumAB[i - 1]) {
                res += before;
                continue;
            }
            int find = -sumAB[i];

            int s = 0;
            int e = n * n - 1;

            long temp = 0;
            while (s <= e) {
                int mid = (s + e) / 2;

                if (sumCD[mid] == find) {
                    temp++;

                    int left = mid;
                    int right = mid;

                    while (left - 1 >= s && sumCD[left - 1] == find) {
                        left--;
                        temp++;
                    }
                    while (right + 1 <= e && sumCD[right + 1] == find) {
                        right++;
                        temp++;
                    }
                    break;
                } else if (sumCD[mid] > find) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            }
            res += temp;
            before = temp;
        }

        System.out.println(res);
    }
}