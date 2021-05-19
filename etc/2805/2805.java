import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] height = new int[N];
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(height);
        System.out.println(cutMaxTreeHeight(height, 1, height[N - 1], M));
    }

    public static int cutMaxTreeHeight(int[] arr, int start, int end, long target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target <= sumTree(arr, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    public static long sumTree(int[] arr, int h) {
        long result = 0;
        for (int i : arr) {
            result += Math.max(i - h, 0);
        }
        return result;
    }
}