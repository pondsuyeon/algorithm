import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] preorder;
    static int[] inorder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            preorder = new int[N];
            inorder = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }
            postorder(0, 0, N - 1);
            System.out.println();
        }
    }

    public static void postorder(int r, int s, int e) {
        if (s > e) return;
        for (int i = s; i <= e; i++) {
            if (preorder[r] == inorder[i]) {
                postorder(r + 1, s, i - 1);
                postorder(i - s + r + 1, i + 1, e);
                System.out.print(preorder[r] + " ");
            }
        }
    }
}