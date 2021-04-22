import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        Queue<FireBall>[][] map = new LinkedList[N][N];
        Queue<FireBall>[][] tmp = new LinkedList[N][N];

        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new LinkedList<>();
                tmp[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int m = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();
            FireBall fb = new FireBall(r, c, m, s, d);

            map[r][c].offer(fb);
        }

        for (int t = 0; t < K; t++) {   // K만큼 반복

            // move
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    while (!map[i][j].isEmpty()) {
                        FireBall fb = map[i][j].poll();
                        fb.r = (N + (fb.r + fb.s * dr[fb.d]) % N) % N;
                        fb.c = (N + (fb.c + fb.s * dc[fb.d]) % N) % N;
                        tmp[fb.r][fb.c].offer(fb);
                    }
                }
            }

            // 합치고 쪼개고
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (tmp[i][j].size() == 1) {
                        map[i][j].offer(tmp[i][j].poll());
                    } else if (tmp[i][j].size() >= 2) {

                        FireBall first = tmp[i][j].poll();
                        int m = first.m;
                        int s = first.s;
                        int d = first.d % 2;
                        int n = tmp[i][j].size() + 1;

                        while (!tmp[i][j].isEmpty()) {
                            FireBall fb = tmp[i][j].poll();
                            m += fb.m;
                            s += fb.s;
                            if (d != fb.d % 2) d = -1;
                        }

                        m /= 5;
                        s /= n;
                        d = (d != -1) ? 0 : 1;
                        if (m > 0) {
                            for (int k = 0; k < 4; k++) {
                                map[i][j].offer(new FireBall(i, j, m, s, d + 2 * k));
                            }
                        }
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                while (!map[i][j].isEmpty()) {
                    FireBall fb = map[i][j].poll();
                    sum += fb.m;
                }
            }
        }
        System.out.println(sum);
    }

    public static class FireBall {
        int r;
        int c;
        int m;
        int s;
        int d;

        FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
