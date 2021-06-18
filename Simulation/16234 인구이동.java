import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] population;
    static boolean[][] visited;
    static int sum = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Country> countries = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        population = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        while (true) {
            boolean flag = false;

            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        countries.clear();
                        sum = 0;
                        DFS(i, j);

                        if (countries.size() != 1) {
                            int p = sum / countries.size();

                            while (!countries.isEmpty()) {
                                Country c = countries.poll();
                                population[c.r][c.c] = p;
                            }

                            flag = true;
                        }
                    }
                }
            }
            if (flag) count++;
            else break;
        }
        System.out.println(count);
    }

    static void DFS(int r, int c) {
        visited[r][c] = true;
        sum += population[r][c];
        countries.offer(new Country(r, c));

        for (int i = 0; i < 4; i++) {
            int tr = r + dr[i];
            int tc = c + dc[i];

            if (tr >= 0 && tr < N && tc >= 0 && tc < N && !visited[tr][tc]) {
                int temp = Math.abs(population[r][c] - population[tr][tc]);
                if (L <= temp && temp <= R)
                    DFS(tr, tc);
            }
        }
    }

    static class Country {
        int r;
        int c;

        public Country(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}