import java.io.*;
import java.util.*;

public class Main {
    static int N, S, D;
    static LinkedList<Integer>[] adj;
    static boolean[] visited;
    static int[] childDepth;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        adj = new LinkedList[N + 1];
        visited = new boolean[N + 1];
        childDepth = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].offer(v);
            adj[v].offer(u);
        }
        findDepth(S);
        Arrays.fill(visited, false);
        findRoute(S);

        System.out.println(count * 2);
    }

    public static int findDepth(int v) {
        visited[v] = true;
        for (int next : adj[v]) {
            if (!visited[next]) {
                childDepth[v] = Math.max(childDepth[v], findDepth(next) + 1);
            }
        }
        return childDepth[v];
    }

    public static void findRoute(int v) {
        visited[v] = true;

        for (int next : adj[v]) {
            if (!visited[next] && childDepth[next] >= D) {
                count++;
                findRoute(next);
            }
        }
    }
}