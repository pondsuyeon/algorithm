
import java.io.*;
import java.util.*;

public class Main_BOJ_2638_치즈 {
    static int N, M;

    static int[][] map;
    static boolean[][] isOutside;

    static Queue<int[]> cheese, melting;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        cheese = new LinkedList<>();
        melting = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheese.add(new int[]{i, j});
            }
        }

        int hour = 0;
        while (!cheese.isEmpty()) {
            setOutside();
            setMelting();
            melt();
            hour++;
        }


        System.out.println(hour);
    }

    private static void setOutside() {

        Queue<int[]> outside = new LinkedList<>();
        isOutside = new boolean[N][M];

        outside.add(new int[]{0, 0});
        isOutside[0][0] = true;

        while (!outside.isEmpty()) {
            int[] o = outside.poll();

            for (int i = 0; i < 4; i++) {
                int nx = o[0] + dx[i];
                int ny = o[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 1 || isOutside[nx][ny]) continue;

                isOutside[nx][ny] = true;
                outside.add(new int[]{nx, ny});
            }
        }
    }

    private static void setMelting() {

        int size = cheese.size();

        while (size-- > 0) {
            int[] c = cheese.poll();

            int count = 0;
            for (int i = 0; i < 4; i++) {
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 1 || !isOutside[nx][ny]) continue;

                count++;
            }

            if (count >= 2) melting.add(c);
            else cheese.add(c);
        }
    }

    private static void melt() {
        while (!melting.isEmpty()) {
            int[] m = melting.poll();

            map[m[0]][m[1]] = 0;
        }
    }
}
