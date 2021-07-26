import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static LinkedList<Node>[][] adj;
    static boolean[][] visited;
    static boolean[][] checked;
    static boolean[][] isBright;
    static int ans = 0;
    static boolean flag;
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1][N + 1];
        checked = new boolean[N + 1][N + 1];
        isBright = new boolean[N + 1][N + 1];

        adj = new LinkedList[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                adj[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[x][y].offer(new Node(a, b));
        }
        isBright[1][1] = true;
        do {
            flag = false;
            visited = new boolean[N + 1][N + 1];
            BFS();
        } while (flag);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (isBright[i][j]) ans++;
            }
        }
        System.out.println(ans);

    }

    static void BFS() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 1));

        while (!q.isEmpty()) {

            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            if (!checked[x][y]) {
                checked[x][y] = true;
                for (Node next : adj[x][y]) {
                    if (!isBright[next.x][next.y]) {
                        isBright[next.x][next.y] = true;
                        flag = true;
                    }
                }
            }

            if (visited[x][y]) continue;
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + d[i][0];
                int ny = y + d[i][1];

                if (nx > 0 && nx <= N && ny > 0 && ny <= N && !visited[nx][ny] && isBright[nx][ny]) {
                    q.offer(new Node(nx, ny));
                }
            }

        }

    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}