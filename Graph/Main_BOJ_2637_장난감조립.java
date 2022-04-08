import java.util.*;
import java.io.*;

public class Main_BOJ_2637_장난감조립 {
    static int N, M;
    static int[][] count;
    static boolean[] isBasic;
    static boolean[] visited;
    static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        count = new int[N + 1][N + 1];
        adj = new ArrayList[N + 1];
        isBasic = new boolean[N + 1];
        visited = new boolean[N + 1];

        Arrays.fill(isBasic, true);

        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            isBasic[X] = false;
            adj[X].add(new Node(Y, K));
        }

        getPartition(N);

        for (int i = 1; i <= N; i++) {
            if (isBasic[i]) {
                sb.append(i).append(" ").append(count[N][i]).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void getPartition(int X) {
        visited[X] = true;

        if (isBasic[X]) {
            count[X][X] = 1;
            return;
        }

        for (Node node : adj[X]) {
            if (!visited[node.Y]) {
                getPartition(node.Y);
            }

            for (int i = 1; i <= N; i++) {
                count[X][i] += node.K * count[node.Y][i];
            }
        }

    }

    static class Node {
        int Y;
        int K;

        Node(int Y, int K) {
            this.Y = Y;
            this.K = K;
        }
    }
}
