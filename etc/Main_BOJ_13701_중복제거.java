import java.io.*;
import java.util.*;

public class Main_BOJ_13701_중복제거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        boolean[] check = new boolean[1 << 25];
        int count = 0;

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int A = Integer.parseInt(st.nextToken());
            if (!check[A]) {
                check[A] = true;
                sb.append(A).append(" ");
                count++;
            }
            if (count == 1 << 25) break;
        }

        System.out.println(sb);
    }
}
