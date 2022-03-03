
import java.io.*;
import java.util.Arrays;


public class Main_BOJ_12852_1로만들기2 {

    static int N;
    static int[] visited;
    static int[] selected;

    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        visited = new int[N + 1];
        selected = new int[N + 1];
        result = new int[N + 1];

        Arrays.fill(visited, N + 1);

        find(N, 0);

        sb.append(result.length - 1).append("\n");
        for (int num : result) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    private static void find(int x, int count) {

        if (visited[x] < count) return;

        visited[x] = count;
        selected[count] = x;

        if (x == 1) {
            if (result.length > count + 1) {
                result = new int[count + 1];
                System.arraycopy(selected, 0, result, 0, count + 1);
            }
            return;
        }

        if (x % 3 == 0) {
            find(x / 3, count + 1);
        }
        if (x % 2 == 0) {
            find(x / 2, count + 1);
        }
        if (x - 1 >= 1) {
            find(x - 1, count + 1);
        }
    }
}
