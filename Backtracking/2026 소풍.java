import java.io.*;
import java.util.*;

public class Main {
    static int K, N, F;
    static boolean[][] connected;
    static int[] connectCount;
    static int[] comb;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        connected = new boolean[N + 1][N + 1];
        connectCount = new int[N + 1];
        comb = new int[K];

        for (int i = 1; i <= N; i++) {
            connected[i][i] = true;
        }
        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            connected[u][v] = true;
            connected[v][u] = true;

            connectCount[u]++;
            connectCount[v]++;
        }
        goPicnic(1, 0);

        if (!flag) {
            sb.append(-1);
        } else {
            for (int student : comb) {
                sb.append(student).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    public static void goPicnic(int index, int count) {
        if (flag) return;

        if (count == K) {
            flag = true;
            return;
        }

        for (int i = index; i <= N; i++) {
            if (!flag && connectCount[i] >= K - 1 && isConnected(i, count)) {
                comb[count] = i;
                goPicnic(i + 1, count + 1);
            }
        }
    }

    public static boolean isConnected(int index, int count) {
        for (int i = 0; i < count; i++) {
            int num = comb[i];
            if (!connected[num][index]) return false;
        }
        return true;
    }
}