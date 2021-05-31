import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static LinkedList<Integer>[] relations;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        relations = new LinkedList[N + 1];

        for (int i = 1; i <= N; i++) {
            relations[i] = new LinkedList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            relations[u].add(v);
            relations[v].add(u);
        }

        int[] ans = getMinEarlyAdapter(1);
        System.out.println(Math.min(ans[0], ans[1]));
    }

    static int[] getMinEarlyAdapter(int v) {
        int[] result = {0, 0};
        visited[v] = true;

        int temp0 = 0;
        int temp1 = 0;
        
        for (int next : relations[v]) {
            if (!visited[next]) {
                int[] values = getMinEarlyAdapter(next);

                temp0 += values[1];
                temp1 += Math.min(values[0], values[1]);
            }
        }

        result[0] = temp0;
        result[1] = 1 + temp1;
        return result;
    }
}