import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] properties = new int[N];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            properties[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(properties);

        long total = Long.MAX_VALUE;
        int l = 0, m = 0, r = 0;

        for (int i = 0; i < N - 2; i++) {
            int j = i + 1;
            int k = N - 1;

            while (true) {

                if (j >= k) break;

                long temp = (long) properties[i] + (long) properties[j] + (long) properties[k];

                if (Math.abs(total) > Math.abs(temp)) {
                    l = i;
                    m = j;
                    r = k;
                    total = temp;
                }

                if (temp < 0) j++;
                else if (temp > 0) k--;
                else break;
            }
        }

        System.out.println(properties[l] + " " + properties[m] + " " + properties[r]);
    }
}