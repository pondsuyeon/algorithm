import java.util.*;
import java.io.*;

public class Main_BOJ_5014_스타트링크 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int res = dijkstra(F, S, G, U, D);

        System.out.println(res != -1 ? res : "use the stairs");
    }

    public static int dijkstra(int F, int S, int G, int U, int D) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[F + 1];

        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {

            Node n = pq.poll();

            if (n.index == G) return n.count;

            if (visited[n.index]) continue;
            visited[n.index] = true;

            if (n.index + U <= F) {
                pq.add(new Node(n.index + U, n.count + 1));
            }
            if (n.index - D >= 1) {
                pq.add(new Node(n.index - D, n.count + 1));
            }
        }

        return -1;
    }

    static class Node implements Comparable<Node> {
        int index;
        int count;

        Node(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public int compareTo(Node n) {
            return this.count - n.count;
        }
    }
}
