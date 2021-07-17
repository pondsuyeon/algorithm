import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int totalDistance = 0;
        CheckPoint[] cp = new CheckPoint[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            cp[i] = new CheckPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < N - 1; i++) {
            totalDistance += getDistance(cp[i], cp[i + 1]);
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < N - 1; i++) {
            int d1 = getDistance(cp[i - 1], cp[i]);
            int d2 = getDistance(cp[i], cp[i + 1]);
            int d3 = getDistance(cp[i - 1], cp[i + 1]);
            ans = Math.min(ans, totalDistance - d1 - d2 + d3);
        }

        System.out.println(ans);
    }

    static int getDistance(CheckPoint cp1, CheckPoint cp2) {
        return Math.abs(cp1.x - cp2.x) + Math.abs(cp1.y - cp2.y);
    }

    static class CheckPoint {
        int x;
        int y;

        public CheckPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}