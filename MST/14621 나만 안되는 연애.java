import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] uni;
    static int[] parent;
    static PriorityQueue<Edge> edges = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        uni = new int[N + 1];
        parent = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            uni[i] = st.nextToken().equals("M") ? 0 : 1;
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (uni[u] != uni[v]) {
                edges.offer(new Edge(u, v, d));
            }
        }

        int count = 0;
        int distance = 0;
        int groupIndex = 1;


        while (!edges.isEmpty()) {

            Edge e = edges.poll();

            if (find(e.u) != find(e.v)) {
                union(e.u, e.v);
                distance += e.d;
                count++;
            }
            if (count == N - 1) break;

        }
        System.out.println((count == N - 1) ? distance : -1);
    }

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y)
            parent[y] = x;
    }

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int d;

        public Edge(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.d - edge.d;
        }
    }
}
