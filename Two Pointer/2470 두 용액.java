import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] properties = new int[N];
        int left = 0;
        int right = N - 1;
        int a = left;
        int b = right;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            properties[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(properties);

        int total = Math.abs(properties[0] + properties[N - 1]);
        while (true) {
            if (left + 1 >= right) break;

            int[] temp = new int[2];
            temp[0] = Math.abs(properties[left + 1] + properties[right]);
            temp[1] = Math.abs(properties[left] + properties[right - 1]);

            if (temp[0] < temp[1]) {
                if (temp[0] < total) {
                    a = left + 1;
                    b = right;
                    total = temp[0];
                }
                left++;
            } else {
                if (temp[1] < total) {
                    a = left;
                    b = right - 1;
                    total = temp[1];
                }
                right--;
            }
        }
        System.out.println(properties[a] + " " + properties[b]);
    }
}