import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer>[] adj = new LinkedList[N + 1];
        int[] inDegree = new int[N + 1];
        int[] term = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adj[A].offer(B);
            inDegree[B]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                term[i] = 1;
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int next : adj[node]) {
                if (--inDegree[next] == 0) {
                    term[next] = term[node] + 1;
                    q.offer(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(term[i] + " ");
        }
    }
}