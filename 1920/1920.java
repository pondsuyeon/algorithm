import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Arrays.sort(arr);
        for (int i = 0; i < M; i++) {
            System.out.println(binarySearch(arr, Integer.parseInt(st.nextToken())));
        }
    }

    public static int binarySearch(int[] num, int target) {
        int first = 0;
        int last = num.length - 1;
        int mid;

        while (first <= last) {
            mid = (first + last) / 2;

            if (target == num[mid])
                return 1;
            else if (target < num[mid]) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }
        return 0;
    }
}