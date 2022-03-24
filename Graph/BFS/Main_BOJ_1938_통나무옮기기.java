
import java.io.*;
import java.util.*;

public class Main_BOJ_1938_통나무옮기기 {
    static int N;
    static int[][] map;

    static Node start, end;

    static int[] mx = {-1, 1, 0, 0, 0};
    static int[] my = {0, 0, -1, 1, 0};
    static int[][][] dx = {{{-1, -1, -1}, {1, 1, 1}, {0}, {0}, {-1, -1, -1, 1, 1, 1}}, {{-2}, {2}, {-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1, -1, 0, 1}}};
    static int[][][] dy = {{{-1, 0, 1}, {-1, 0, 1}, {-2}, {2}, {-1, 0, 1, -1, 0, 1}}, {{0}, {0}, {-1, -1, -1}, {1, 1, 1}, {-1, -1, -1, 1, 1, 1}}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<int[]> starts = new ArrayList<>();
        List<int[]> ends = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (temp[j] == 'B') starts.add(new int[]{i, j});
                else if (temp[j] == 'E') ends.add(new int[]{i, j});
                else map[i][j] = temp[j] - '0';
            }
        }

        start = new Node(starts);
        end = new Node(ends);

        System.out.println(move());
    }

    private static int move() {

        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[2][N][N];

        visited[start.type][start.x][start.y] = true;

        q.add(start);

        int time = -1;
        while (!q.isEmpty()) {
            time++;

            int size = q.size();
            while (size-- > 0) {

                Node n = q.poll();

                if (n.type == end.type && n.x == end.x && n.y == end.y) return time;

                for (int i = 0; i <= 4; i++) {

                    boolean flag = true;

                    for (int j = 0; j < dx[n.type][i].length; j++) {
                        int nx = n.x + dx[n.type][i][j];
                        int ny = n.y + dy[n.type][i][j];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        int nx = n.x + mx[i];
                        int ny = n.y + my[i];

                        int nextType = n.type;
                        if (i == 4) {
                            if (n.type == 0) nextType = 1;
                            else nextType = 0;
                        }
                        if (visited[nextType][nx][ny]) continue;

                        visited[nextType][nx][ny] = true;
                        q.add(new Node(nextType, nx, ny));
                    }
                }
            }
        }

        return 0;
    }


    static class Node {
        int type, x, y;

        Node(List<int[]> parts) {

            int[] start = parts.get(0);
            int[] middle = parts.get(1);
            int[] end = parts.get(2);

            this.type = start[0] == end[0] ? 0 : 1;
            this.x = middle[0];
            this.y = middle[1];
        }

        Node(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
}
