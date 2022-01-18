
import java.io.*;
import java.util.*;

public class Main_BOJ_17141_연구소2 {
    static int N, M;
    static int[][] map;

    static Node[] selected;
    static List<Node> possible;
    static int possibleSize = 0;
    static int empty = 0;
    static int ans = Integer.MAX_VALUE;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        selected = new Node[M];
        possible = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 1) empty++;
                if (map[i][j] == 2) possible.add(new Node(i, j));
            }
        }

        possibleSize = possible.size();

        comb(0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void comb(int index, int count) {
        if (count == M) {
            ans = Math.min(ans, spread());
            return;
        }

        if (index >= possibleSize) return;

        comb(index + 1, count);
        selected[count] = possible.get(index);
        comb(index + 1, count + 1);
    }

    private static int spread() {

        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int virusCount = M;

        int sec = -1;

        for (Node virus : selected) {
            q.add(virus);
            visited[virus.x][virus.y] = true;
        }

        while (!q.isEmpty()) {
            sec++;
            if (virusCount == empty) return sec;

            int size = q.size();
            while (size-- > 0) {

                Node n = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = n.x + dx[i];
                    int ny = n.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == 1) continue;

                    visited[nx][ny] = true;
                    virusCount++;
                    q.add(new Node(nx, ny));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
