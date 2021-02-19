import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] wire = new int[K];
        long sum = 0;
        for (int i = 0; i < K; i++) {
            wire[i] = Integer.parseInt(br.readLine());
            sum += wire[i];
        }
        long avg = sum / N;

        long low = 1;
        long high = avg;

        while (low <= high) {
            long mid = (low + high) / 2;
            int cnt = countLanWire(wire, mid);
            if (cnt >= N) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(high);
    }


    public static int countLanWire(int[] arr, long length) {
        int size = arr.length;
        int count = 0;

        for (int i = 0; i < size; i++) {
            count += arr[i] / length;
        }
        return count;
    }
}
