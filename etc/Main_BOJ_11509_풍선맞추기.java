
import java.io.*;
import java.util.*;

public class Main_BOJ_11509_풍선맞추기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] count = new int[1000002];
        int total = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (count[num + 1] > 0) {
                count[num + 1]--;
            } else {
                total++;
            }
            count[num]++;
        }

        System.out.println(total);
    }
}
