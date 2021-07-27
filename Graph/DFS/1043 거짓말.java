import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;
    static LinkedList<Integer>[] adj;
    static int[][] parties;
    static boolean[] truth;
    static int[] p;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        truth = new boolean[N + 1];
        parties = new int[M][];

        adj = new LinkedList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new LinkedList<>();
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        p = new int[T];
        for (int i = 0; i < T; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            parties[i] = new int[C];
            for (int j = 0; j < C; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < parties[i].length - 1; j++) {
                for (int k = j + 1; k < parties[i].length; k++) {
                    adj[parties[i][j]].offer(parties[i][k]);
                    adj[parties[i][k]].offer(parties[i][j]);
                }
            }
        }
        for (int i = 0; i < T; i++) {
            setTruth(p[i]);
        }
        int ans = 0;
        for (int i = 0; i < M; i++) {
            boolean flag = true;
            for (int j = 0; j < parties[i].length; j++) {
                if (truth[parties[i][j]]) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans++;
        }
        System.out.println(ans);
    }

    public static void setTruth(int v) {
        if (truth[v]) return;
        truth[v] = true;

        for (int next : adj[v]) {
            if (!truth[next]) setTruth(next);
        }
    }
}