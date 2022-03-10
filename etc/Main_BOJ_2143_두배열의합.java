import java.io.*;
import java.util.*;

public class Main_BOJ_2143_두배열의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n + 1];
        HashMap<Integer, Integer> mapA = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m + 1];
        HashMap<Integer, Integer> mapB = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = B[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (!mapA.containsKey(A[i] - A[j])) {
                    mapA.put(A[i] - A[j], 0);
                }
                mapA.put(A[i] - A[j], mapA.get(A[i] - A[j]) + 1);
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < i; j++) {
                if (!mapB.containsKey(B[i] - B[j])) {
                    mapB.put(B[i] - B[j], 0);
                }
                mapB.put(B[i] - B[j], mapB.get(B[i] - B[j]) + 1);
            }
        }

        int ans = 0;
        for (int aKey : mapA.keySet()) {

            if (mapB.containsKey(T - aKey)) {
                ans += mapA.get(aKey) * mapB.get(T - aKey);
            }
        }

        System.out.println(ans);
    }
}
