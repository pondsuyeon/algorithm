import java.io.*;
import java.util.*;

public class Main_BOJ_20166_문자열지옥에빠진호석 {

    static int N, M, K;
    static char[][] map;
    static List<String>[][][] words;
    static Map<String, Integer> count;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][];
        count = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 1, map[i][j] + "");
            }
        }

        for (int i = 0; i < K; i++) {
            String word = br.readLine();

            if (!count.containsKey(word))
                count.put(word, 0);

            sb.append(count.get(word)).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int x, int y, int index, String word) {

        if (!count.containsKey(word)) {
            count.put(word, 0);
        }
        count.put(word, count.get(word) + 1);

        if (index >= 5) return;

        for (int i = 0; i < 8; i++) {
            int nx = (N + x + dx[i]) % N;
            int ny = (M + y + dy[i]) % M;

            dfs(nx, ny, index + 1, word + map[nx][ny]);
        }
    }
}
