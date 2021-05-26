import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int T = 0;

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            ++T;

            int[][] cave = new int[N][N];
            int[][] dist = new int[N][N];
            boolean[][] visited = new boolean[N][N];

            PriorityQueue<Node> pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            pq.offer(new Node(0, 0, cave[0][0]));
            dist[0][0] = cave[0][0];

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                visited[node.r][node.c] = true;

                for (int d = 0; d < 4; d++) {
                    int tr = node.r + dr[d];
                    int tc = node.c + dc[d];

                    if (tr >= 0 && tr < N && tc >= 0 && tc < N) {
                        if (!visited[tr][tc]) {
                            if (dist[tr][tc] > dist[node.r][node.c] + cave[tr][tc]) {
                                dist[tr][tc] = dist[node.r][node.c] + cave[tr][tc];
                                pq.offer(new Node(tr, tc, dist[tr][tc]));
                            }
                        }
                    }
                }
            }
            sb.append("Problem ").append(T).append(": ").append(dist[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {
        int r;
        int c;
        int rupee;

        public Node(int r, int c, int rupee) {
            this.r = r;
            this.c = c;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Node node) {
            return this.rupee - node.rupee;
        }
    }
}