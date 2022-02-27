
import java.io.*;

public class Main_BOJ_3663_고득점 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            char[] name = br.readLine().toCharArray();
            int[] count = new int[name.length];
            int total = 0, ans;
            for (int i = 0; i < name.length; i++) {
                count[i] = Math.min(name[i] - 'A', 'Z' - name[i] + 1);
                total += count[i];
            }

            ans = Integer.MAX_VALUE;

            for (int i = 0; i < name.length; i++) {
                int next = i + 1;

                while (next < name.length && name[next] == 'A') next++;

                int temp = Math.min(2 * i + name.length - next, 2 * (name.length - next) + i);

                ans = Math.min(ans, temp);
            }

            ans += total;

            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}