import java.io.*;
import java.util.*;

public class Main_BOJ_16947_서울지하철2호선 {

    static int N;

    static List<Integer>[] adj;
    static boolean[][] used;
    static boolean[] visited;

    static boolean[] isCycle;

    static int cycleStart = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        used = new boolean[N + 1][N + 1];
        isCycle = new boolean[N + 1];
        adj = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            dfs(i);
            if (cycleStart != -1) break;
        }

        for (int i = 1; i <= N; i++) {
            sb.append(bfs(i)).append(" ");
        }
        System.out.println(sb);
    }

    private static int bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[N + 1];

        q.add(v);
        visited[v] = true;

        int distance = -1;

        while (!q.isEmpty()) {
            distance++;
            int size = q.size();

            while (size-- > 0) {
                int u = q.poll();
                if (isCycle[u]) return distance;

                for (int next : adj[u]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }

        return -1;
    }

    private static boolean dfs(int v) {
        if (visited[v]) {
            cycleStart = v;
            return true;
        }

        visited[v] = true;

        for (int next : adj[v]) {
            if (used[v][next] || used[next][v]) continue;

            used[v][next] = true;
            used[next][v] = true;

            if (dfs(next)) {
                isCycle[v] = true;
                break;
            }
        }

        if (cycleStart == v) {
            return false;
        }
        return isCycle[v];
    }
}
