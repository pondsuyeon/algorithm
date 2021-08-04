import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static PriorityQueue<Edge> worst = new PriorityQueue<>();
    static PriorityQueue<Edge> best = new PriorityQueue<>(Collections.reverseOrder());

    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        parent = new int[N + 1];

        int first = 0;

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (i == 0) {
                if (c == 0) first++;
                continue;
            }
            Edge e = new Edge(a, b, c);
            worst.offer(e);
            best.offer(e);
        }

        int max = getFatigue(0) + first;
        int min = getFatigue(1) + first;

        System.out.println(max * max - min * min);

    }

    static int getFatigue(int flag) {
        PriorityQueue<Edge> q = (flag == 0) ? worst : best;
        Arrays.fill(visited, false);
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int connect = 0;
        int k = 0;

        while (!q.isEmpty()) {

            Edge e = q.poll();

            if (find(e.a) == find(e.b)) continue;

            visited[e.a] = true;
            visited[e.b] = true;

            union(e.a, e.b);

            if (e.c == 0) k++;
            if (++connect == N - 1) return k;
        }
        return -1;
    }

    static int find(int n) {
        if (parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;
        parent[a] = b;
    }

    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int c;

        Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.c - edge.c;
        }
    }
}