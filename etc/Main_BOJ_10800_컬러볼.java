
import java.io.*;
import java.util.*;

public class Main_BOJ_10800_컬러볼 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] total = new int[2000 + 1];
        Ball[] balls = new Ball[N + 1];

        int[] ans = new int[N + 1];

        balls[0] = new Ball(0, 0, 0);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            balls[i] = new Ball(i, c, s);

            total[s]++;
        }

        for (int i = 1; i <= 2000; i++) {
            total[i] = total[i - 1] + total[i] * i;
        }

        Arrays.sort(balls, (o1, o2) -> {
            if (o1.color == o2.color) return o1.size - o2.size;
            return o1.color - o2.color;
        });

        for (int i = 1; i <= N; i++) {
            balls[i].sum = total[balls[i].size - 1];
        }

        int s = 0;
        int e = 1;

        while (e <= N) {

            total = new int[2000 + 1];
            if (e > 0)
                total[balls[e - 1].size]++;
            while (e <= N && balls[e - 1].color == balls[e].color) {
                total[balls[e].size]++;
                e++;
            }

            for (int i = 1; i <= 2000; i++) {
                total[i] = total[i - 1] + total[i] * i;
            }

            for (int i = s; i < e; i++) {
                if (balls[i].size > 0)
                    balls[i].sum -= total[balls[i].size - 1];
            }

            s = e++;
        }

        for (int i = 1; i <= N; i++) {
            ans[balls[i].index] = balls[i].sum;
        }

        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.print(sb);
    }

    static class Ball {
        int index;
        int color;
        int size;
        int sum;

        Ball(int index, int color, int size) {
            this.index = index;
            this.color = color;
            this.size = size;
        }
    }
}
