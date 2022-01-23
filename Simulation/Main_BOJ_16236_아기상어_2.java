import java.io.*;
import java.util.*;

public class Main_BOJ_16236_아기상어_2 {

    static int N;
    static Node[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static Node shark = null;
    static int eaten = 0;
    static int second = 0;
    static boolean success = true;

    static class Node {
        int x, y, size;

        Node(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new Node[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int s = Integer.parseInt(st.nextToken());
                if (s == 0) continue;
                if (s == 9) {
                    shark = new Node(i, j, 2);
                } else {
                    map[i][j] = new Node(i, j, s);
                }
            }
        }

        while (success) {
            second += eat();
        }

        System.out.println(second);
    }

    private static int eat() {

        Queue<Node> sharkMovement = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        sharkMovement.add(shark);
        visited[shark.x][shark.y] = true;

        int distance = 0;
        while (!sharkMovement.isEmpty()) {
            distance++;
            int size = sharkMovement.size();

            PriorityQueue<Node> fish = new PriorityQueue<Node>((f1, f2) -> {
                if (f1.x != f2.x) return f1.x - f2.x;
                return f1.y - f2.y;
            });

            while (size-- > 0) {

                Node mvmt = sharkMovement.poll();

                int x = mvmt.x;
                int y = mvmt.y;
                int s = mvmt.size;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
                        continue;
                    visited[nx][ny] = true;
                    if (map[nx][ny] != null && map[nx][ny].size < s) {
                        fish.add(map[nx][ny]);
                    } else if (map[nx][ny] == null || map[nx][ny].size == s) {
                        sharkMovement.add(new Node(nx, ny, s));
                    }
                }
            }

            if (!fish.isEmpty()) {
                Node target = fish.poll();

                int x = target.x;
                int y = target.y;

                map[x][y] = null;

                shark.x = x;
                shark.y = y;

                if (++eaten == shark.size) {
                    shark.size++;
                    eaten = 0;
                }

                success = true;
                return distance;
            }
        }

        success = false;
        return 0;
    }

}
