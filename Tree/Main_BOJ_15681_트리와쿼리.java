
import java.io.*;
import java.util.*;

public class Main_BOJ_15681_트리와쿼리 {

    static int N, R, Q;
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] sub;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());


        adj = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        sub = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(R);

        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(sub[q]).append("\n");
        }
        System.out.print(sb);
    }

    private static int dfs(int v) {
        visited[v] = true;

        sub[v] = 1;
        for (int next : adj[v]) {
            if (visited[next]) continue;

            sub[v] += dfs(next);
        }

        return sub[v];
    }
}
