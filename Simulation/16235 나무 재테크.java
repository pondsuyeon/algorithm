import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A, ground;
    static Queue<Tree> trees, dead;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        ground = new int[N + 1][N + 1];
        trees = new LinkedList<>();
        dead = new LinkedList<>();

        PriorityQueue<Tree> tmp = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            Arrays.fill(ground[i], 5);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            tmp.offer(new Tree(x, y, z));
        }

        trees = tmp;

        for (int t = 0; t < K; t++) {
            S2D2();
        }
        int alive = trees.size();

        System.out.println(alive);
    }

    static void S2D2() {
        spring();
        summer();
        fall();
        winter();
    }

    static void spring() {
        PriorityQueue<Tree> tq = new PriorityQueue<>();

        while (!trees.isEmpty()) {
            Tree t = trees.poll();
            if (ground[t.x][t.y] >= t.age) {
                ground[t.x][t.y] -= t.age;
                tq.offer(new Tree(t.x, t.y, t.age + 1));
            } else {
                dead.offer(t);
            }
        }

        trees = tq;
    }

    static void summer() {
        while (!dead.isEmpty()) {
            Tree t = dead.poll();
            ground[t.x][t.y] += t.age / 2;
        }

    }

    static void fall() {
        PriorityQueue<Tree> tq = new PriorityQueue<>();

        while (!trees.isEmpty()) {
            Tree t = trees.poll();
            tq.offer(t);

            if (t.age % 5 == 0) {
                for (int d = 0; d < 8; d++) {
                    int x = t.x + dx[d];
                    int y = t.y + dy[d];

                    if (x >= 1 && x <= N && y >= 1 && y <= N) {
                        trees.offer(new Tree(x, y, 1));
                    }
                }
            }
        }

        trees = tq;
    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ground[i][j] += A[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree> {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree tree) {
            return this.age - tree.age;
        }
    }
}