
import java.io.*;
import java.util.*;

public class Main_BOJ_16137_견우와직녀 {
    static int N, M;
    static int[][] map;

    static List<int[]> cliff = new ArrayList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    cliff.add(new int[]{i, j});
                }
            }
        }

        for (int[] c : cliff) {
            int x = c[0];
            int y = c[1];

            int[] nx = new int[4];
            int[] ny = new int[4];

            for (int i = 0; i < 4; i++) {
                nx[i] = x + dx[i];
                ny[i] = y + dy[i];
            }

            if ((isRange(nx[0], ny[0]) && map[nx[0]][ny[0]] == 0 || isRange(nx[1], ny[1]) && map[nx[1]][ny[1]] == 0)
                    && (isRange(nx[2], ny[2]) && map[nx[2]][ny[2]] == 0 || isRange(nx[3], ny[3]) && map[nx[3]][ny[3]] == 0)) {
                continue;
            }

            map[x][y] = M;
            BFS();
            map[x][y] = 0;
        }
        BFS();

        System.out.println(ans);
    }

    private static void BFS() {

        int[][][] visited = new int[2][N][N];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, false, 0));
        visited[0][0][0] = 0;

        while (!pq.isEmpty()) {

            Node n = pq.poll();

            int x = n.x;
            int y = n.y;
            boolean crossed = n.crossed;
            int time = n.time;

            if (time >= ans) return;

            if (x == N - 1 && y == N - 1) {
                ans = time;
                return;
            }

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny) || map[nx][ny] == 0) continue;

                if (!crossed && map[nx][ny] > 1) {

                    int next;

                    if (time + 1 <= map[nx][ny]) {
                        next = map[nx][ny];
                    } else {
                        if ((time + 1) % map[nx][ny] == 0) {
                            next = time + 1;
                        } else {
                            next = map[nx][ny] * (1 + (time + 1) / map[nx][ny]);
                        }
                    }

                    if (next < visited[1][nx][ny]) {
                        pq.add(new Node(nx, ny, true, next));
                    }
                }
                if (map[nx][ny] == 1 && time + 1 < visited[0][nx][ny]) {
                    visited[0][nx][ny] = time + 1;
                    pq.add(new Node(nx, ny, false, time + 1));
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Node implements Comparable<Node> {
        int x, y;
        boolean crossed;
        int time;

        Node(int x, int y, boolean crossed, int time) {
            this.x = x;
            this.y = y;
            this.crossed = crossed;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
