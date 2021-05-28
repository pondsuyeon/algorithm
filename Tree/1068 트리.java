import java.io.*;
import java.util.*;

public class Main {
    static int N, R;
    static LinkedList<Integer>[] adj;
    static boolean[] removed, visited;
    static int result = 0;
    static int root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adj = new LinkedList[N];

        for (int i = 0; i < N; i++) adj[i] = new LinkedList<>();

        removed = new boolean[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) {
                root = i;
            } else {
                adj[p].add(i);
            }
        }

        R = Integer.parseInt(br.readLine());

        removeNode(R);
        getLeafNumber(root);
        System.out.println(result);
    }

    static void removeNode(int index) {
        removed[index] = true;

        for (int node : adj[index]) {
            if (!removed[node]) {
                removeNode(node);
            }
        }
    }

    static void getLeafNumber(int node) {
        if (node == R) return;

        visited[node] = true;
        int child = 0;

        for (int next : adj[node]) {
            if (!visited[next] && !removed[next]) {
                getLeafNumber(next);
                child++;
            }
        }
        if (child == 0) result++;
    }
}