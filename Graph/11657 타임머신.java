import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Edge[] edge;
    static long[] distance;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edge = new Edge[M];
        distance = new long[N + 1];

        Arrays.fill(distance, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edge[i] = new Edge(A, B, C);
        }

        distance[1] = 0;

        for (int i = 0; i < N - 1; i++) {
            bellmanFord();
        }
        if (bellmanFord()) {
            for (int i = 2; i <= N; i++) {
                System.out.println(distance[i] == INF ? -1 : distance[i]);
            }
        } else {
            System.out.println(-1);
        }
    }

    static boolean bellmanFord() {
        boolean flag = true;
        for (int j = 0; j < M; j++) {
            Edge e = edge[j];

            if (distance[e.u] != INF && distance[e.v] > distance[e.u] + e.w) {
                distance[e.v] = distance[e.u] + e.w;
                flag = false;
            }
        }
        return flag;
    }

    static class Edge {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}