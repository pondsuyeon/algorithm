import java.io.*;
import java.util.*;

public class Main_BOJ_12851_숨바꼭질2 {

    static int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] res = find(N, K);

        System.out.println(res[0] + "\n" + res[1]);
    }

    private static int[] find(int N, int K) {

        int time = -1;

        int[] visited = new int[MAX];
        int[] count = new int[MAX];

        Arrays.fill(visited, MAX);

        Queue<Integer> q = new LinkedList<>();
        q.add(N);

        visited[N] = 0;
        count[N] = 1;

        while (!q.isEmpty()) {
            time++;

            int size = q.size();
            while (size-- > 0) {

                int x = q.poll();

                if (x == K) return new int[]{time, count[K]};

                if (x - 1 >= 0) {
                    if (visited[x - 1] == MAX) {
                        visited[x - 1] = time + 1;
                        count[x - 1] = count[x];
                        q.add(x - 1);
                    } else if (visited[x] + 1 == visited[x - 1]) {
                        count[x - 1] += count[x];
                    }
                }
                if (x + 1 < MAX) {
                    if (visited[x + 1] == MAX) {
                        visited[x + 1] = time + 1;
                        count[x + 1] = count[x];
                        q.add(x + 1);
                    } else if (visited[x] + 1 == visited[x + 1]) {
                        count[x + 1] += count[x];
                    }
                }

                if (2 * x < MAX) {
                    if (visited[2 * x] == MAX) {
                        visited[2 * x] = time + 1;
                        count[2 * x] = count[x];
                        q.add(2 * x);
                    } else if (visited[x] + 1 == visited[2 * x]) {
                        count[2 * x] += count[x];
                    }
                }
            }
        }

        return new int[]{-1, -1};
    }
}
