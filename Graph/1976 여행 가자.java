import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static LinkedList<Integer>[] adj;
    static int[] plan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new LinkedList[N + 1];
        plan = new int[M];

        for (int i = 0; i <= N; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    adj[i].add(j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = true;
        for (int i = 1; i < M; i++) {
            if (!isConnected(plan[i - 1], plan[i], new boolean[N + 1])) {
                flag = false;
                break;
            }
        }
        System.out.println((flag) ? "YES" : "NO");
    }

    static boolean isConnected(int A, int B, boolean[] visited) {
        if (A == B) return true;

        visited[A] = true;
        for (Integer next : adj[A]) {
            if (!visited[next]) {
                if (isConnected(next, B, visited)) return true;
            }
        }
        return false;
    }
}

