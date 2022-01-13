import java.io.*;

public class Main_BOJ_9663_NQueen {


    static int[] arr;
    static int N;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        ans = 0;
        dfs(0);
        System.out.println(ans);
    }

    private static void dfs(int index) {
        if (index == N) {
            ans++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[index] = i;
            boolean flag = true;
            for (int j = 0; j < index; j++) {
                if (arr[j] == i || Math.abs(arr[j] - i) == Math.abs(j - index)) {
                    flag = false;
                    break;
                }
            }

            if (flag) dfs(index + 1);

        }
    }
}
