import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] card = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            card[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(card);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sb.append(binarySearch(card, Integer.parseInt(st.nextToken()))).append(" ");
        }
        System.out.println(sb);
    }

    public static int binarySearch(int[] arr, int target) {
        return (upperBound(arr, target) - lowerBound(arr, target));
    }

    public static int lowerBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target <= arr[mid])
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    public static int upperBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target >= arr[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}
