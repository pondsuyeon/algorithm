import java.io.*;
import java.util.*;

public class Main {
    static int n, d, c;
    static LinkedList<Node>[] adj;
    static boolean[] visited;
    static int[] result = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            adj = new LinkedList[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                adj[i] = new LinkedList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adj[b].add(new Node(a, s));
            }
            dijkstra();
            System.out.println(result[0] + " " + result[1]);
        }
    }

    static void dijkstra() {
        int count = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[c] = 0;
        pq.add(new Node(c, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.index]) continue;

            visited[node.index] = true;
            count++;

            for (Node next : adj[node.index]) {
                if (dist[next.index] > dist[node.index] + next.weight) {
                    dist[next.index] = dist[node.index] + next.weight;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }
        int max = -1;
        for (int i = 1; i <= n; i++) {
            if (visited[i])
                max = Math.max(max, dist[i]);
        }
        result[0] = count;
        result[1] = max;
    }

    static class Node implements Comparable<Node> {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
}