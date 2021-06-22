import java.util.*;
import java.io.*;

public class Main {
    static int w, h;
    static char[][] map;

    static int dirtyCount;
    static int[][] distance;

    static boolean[] checked;

    static ArrayList<Node> dirties;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            ans = Integer.MAX_VALUE;

            map = new char[h][w];
            dirties = new ArrayList<>();
            dirtyCount = 1;

            for (int i = 0; i < h; i++) {
                String r = br.readLine();

                for (int j = 0; j < w; j++) {
                    map[i][j] = r.charAt(j);

                    if (map[i][j] == 'o') {
                        dirties.add(0, new Node(i, j));
                    } else if (map[i][j] == '*') {
                        dirties.add(new Node(i, j));
                        dirtyCount++;
                    }
                }
            }

            distance = new int[dirtyCount][dirtyCount];

            for (int i = 0; i < dirtyCount; i++) {
                for (int j = i + 1; j < dirtyCount; j++) {
                    distance[i][j] = getDistanceWithDirties(dirties.get(i), dirties.get(j));
                    if (distance[i][j] == -1) {
                        ans = -1;
                        break;
                    }
                    distance[j][i] = distance[i][j];
                }
                if (ans == -1) break;
            }
            if (ans == -1) System.out.println(-1);
            else {
                checked = new boolean[dirtyCount];
                shortestDistance(0, 0, 0);

                System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
            }
        }
    }

    static void shortestDistance(int v, int depth, int dist) {
        if (depth == dirtyCount) {
            ans = Math.min(ans, dist);
            return;
        }

        for (int i = 0; i < dirtyCount; i++) {
            if (!checked[i]) {
                checked[i] = true;
                shortestDistance(i, depth + 1, dist + distance[v][i]);
                checked[i] = false;
            }
        }
    }

    static int getDistanceWithDirties(Node u, Node v) {
        Queue<Node> q = new LinkedList<>();
        int[][] dist = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(dist[i], -1);
        }

        dist[u.x][u.y] = 0;
        q.offer(u);

        while (!q.isEmpty()) {
            Node n = q.poll();

            for (int i = 0; i < 4; i++) {
                int tx = n.x + dx[i];
                int ty = n.y + dy[i];

                if (tx >= 0 && tx < h && ty >= 0 && ty < w && dist[tx][ty] == -1 && map[tx][ty] != 'x') {
                    dist[tx][ty] = dist[n.x][n.y] + 1;

                    if (tx == v.x && ty == v.y) {
                        return dist[tx][ty];
                    }
                    q.offer(new Node(tx, ty));
                }
            }
        }
        return -1;
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