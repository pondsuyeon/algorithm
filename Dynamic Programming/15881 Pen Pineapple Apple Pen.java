import java.io.*;
import java.util.Arrays;

public class Main {
    static int N;
    static char[] arr;
    static int[] dp;
    static char[] ppap = {'p', 'P', 'A', 'p'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        dp = new int[N];
        Arrays.fill(dp, -1);
        System.out.println(func(0));
    }

    static int func(int index) {
        if (index > N - 4) {
            return 0;
        }
        if (dp[index] == -1) {
            boolean flag = true;
            for (int i = 0; i < 4; i++) {
                if (arr[index + i] != ppap[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                dp[index] = Math.max(1 + func(index + 4), func(index + 1));
            } else {
                dp[index] = func(index + 1);
            }
        }
        return dp[index];
    }
}