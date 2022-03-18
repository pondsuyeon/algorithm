import java.io.*;
import java.util.*;

public class Main_BOJ_20955_민서응급수술 {

    static int[] parent;
    static int[] children;
    static int[] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            union(u, v);
        }

        int group = 0;
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (parent[i] == i) {
                count += Math.abs(children[i] - 1 - edge[i]);
                group++;
            }
        }

        count += group - 1;

        System.out.println(count);
    }

    private static void init(int N) {
        parent = new int[N + 1];
        children = new int[N + 1];
        edge = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            children[i] = 1;
        }
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            edge[x]++;
            return;
        }

        if (x > y) {
            parent[x] = y;
            children[y] += children[x];
            edge[y] += edge[x] + 1;
        } else {
            parent[y] = x;
            children[x] += children[y];
            edge[x] += edge[y] + 1;
        }
    }
}
