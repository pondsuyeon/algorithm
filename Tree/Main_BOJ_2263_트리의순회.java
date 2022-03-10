import java.io.*;
import java.util.*;

public class Main_BOJ_2263_트리의순회 {
    static int n;
    static int[] in, post;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        in = new int[n + 1];
        post = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        preorder(1, n, 1, n, sb);
        System.out.println(sb);
    }

    private static void preorder(int is, int ie, int ps, int pe, StringBuilder sb) {

        if (is > ie || ps > pe) return;
        int root = post[pe];

        sb.append(root).append(" ");

        if (is == ie || ps == pe) return;

        for (int i = is; i <= ie; i++) {
            if (root == in[i]) {
                if (i != is)
                    preorder(is, i - 1, ps, ps + i - is - 1, sb);
                if (i != ie)
                    preorder(i + 1, ie, ps + i - is, pe - 1, sb);

                break;
            }
        }
    }
}
