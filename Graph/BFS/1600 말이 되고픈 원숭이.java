import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] map;
    static int[][][] moveCount;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        if (H == 1 && W == 1){
            System.out.println(0);
            return;
        }
        map = new int[H + 1][W + 1];

        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(BFS());
    }

    static int BFS() {
        moveCount = new int[H + 1][W + 1][K + 1];
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                Arrays.fill(moveCount[i][j], -1);
            }
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 1, K));

        moveCount[1][1][K] = 0;
        while (!q.isEmpty()) {
            Node n = q.poll();
            int nx = n.x;
            int ny = n.y;
            int nk = n.k;

            for (int i = 0; i < 4; i++) {
                int tx = nx + dx[i];
                int ty = ny + dy[i];
                int tk = nk;

                if (tx >= 1 && tx <= H && ty >= 1 && ty <= W && map[tx][ty] != 1 && moveCount[tx][ty][tk] == -1) {
                    moveCount[tx][ty][tk] = moveCount[nx][ny][nk] + 1;
                    q.offer(new Node(tx, ty, tk));
                    if (tx == H && ty == W) return moveCount[tx][ty][tk];
                }
            }
            for (int i = 0; i < 8; i++) {
                int tx = nx + hx[i];
                int ty = ny + hy[i];
                int tk = nk - 1;
                if (tx >= 1 && tx <= H && ty >= 1 && ty <= W && tk >= 0 && map[tx][ty] != 1 && moveCount[tx][ty][tk] == -1) {
                    moveCount[tx][ty][tk] = moveCount[nx][ny][nk] + 1;
                    q.offer(new Node(tx, ty, tk));
                    if (tx == H && ty == W) return moveCount[tx][ty][tk];
                }
            }
        }
        return -1;
    }

    static class Node {
        int x;
        int y;
        int k;

        public Node(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}
