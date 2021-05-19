import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] cnt = new int[11];
        cnt[10] = 1;
        for (int i = 1; i < 10; i++) {
            int temp = i;
            do {
                temp = (temp * i) % 10;
                cnt[i]++;
            } while (i != temp);
        }
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int ans = 0;
            if (a % 10 == 0) {
                ans = 10;
            } else {
                a = a % 10;
                b = (b % cnt[a] != 0) ? b % cnt[a] : cnt[a];
                ans = (int) (Math.pow(a, b)) % 10;
            }
            System.out.println(ans);
        }
    }
}
