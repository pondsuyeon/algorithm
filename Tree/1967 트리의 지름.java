import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static LinkedList<Node>[] nodes;
    static boolean[] visited;
    static int[] distance;

    static int max = -1;
    static int maxIndex = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        nodes = new LinkedList[N + 1];
        visited = new boolean[N + 1];
        distance = new int[N + 1];

        for (int i = 0; i <= N; i++) nodes[i] = new LinkedList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[p].add(new Node(c, w));
            nodes[c].add(new Node(p, w));
        }

        updateDistance(1);
        findMaxDistance();

        Arrays.fill(distance, 0);
        Arrays.fill(visited, false);

        updateDistance(maxIndex);
        findMaxDistance();

        System.out.println(max);
    }

    static void findMaxDistance() {
        for (int i = 1; i <= N; i++) {
            if (max < distance[i]) {
                max = distance[i];
                maxIndex = i;
            }
        }
    }

    static void updateDistance(int v) {
        visited[v] = true;

        for (Node next : nodes[v]) {
            if (!visited[next.index]) {
                distance[next.index] = distance[v] + next.weight;
                updateDistance(next.index);
            }
        }
    }

    static class Node {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
}