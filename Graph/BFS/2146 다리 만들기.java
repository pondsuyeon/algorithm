import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] bridge;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node>[] q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        bridge = new int[N][N];
        visited = new boolean[N][N];
        q = new Queue[10000];

        for (int i = 0; i < 10000; i++) {
            q[i] = new LinkedList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int num = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    setIsland(i, j, ++num);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < num; i++) {
            min = Math.min(min, findMinBridge(i));
        }
        System.out.println(min);
    }

    static void setIsland(int x, int y, int num) {
        q[num].offer(new Node(x, y));

        visited[x][y] = true;
        map[x][y] = num;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] == 1 && !visited[tx][ty]) {
                setIsland(tx, ty, num);
            }
        }
    }

    static int findMinBridge(int num) {
        Queue<Node> tq = q[num];
        for (int i = 0; i < N; i++) {
            Arrays.fill(bridge[i], 0);
        }

        while (!tq.isEmpty()) {
            Node node = tq.poll();

            for (int i = 0; i < 4; i++) {
                int tx = node.x + dx[i];
                int ty = node.y + dy[i];

                if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != num && bridge[tx][ty] == 0) {
                    if (map[tx][ty] == 0) {
                        bridge[tx][ty] = bridge[node.x][node.y] + 1;
                        tq.offer(new Node(tx, ty));
                    } else if (map[tx][ty] != num) {
                        return bridge[node.x][node.y];
                    }
                }

            }
        }
        return N;
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

