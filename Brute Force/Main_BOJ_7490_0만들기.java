import java.io.*;

public class Main_BOJ_7490_0만들기 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            dfs(0, "1", sb);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);
    }

    private static void dfs(int count, String total, StringBuilder sb) {
        if (count == N - 1) {
            String str = total.replaceAll(" ", "");

            int len = str.length();
            int idx = 0;
            int sum = 0;
            while (idx < len) {

                char ch = str.charAt(idx);

                int temp = 0;
                int nextIdx = idx + 1;
                if (idx == 0 || ch == '+' || ch == '-') {
                    if (idx == 0) {
                        temp += ch - '0';
                    }
                    while (nextIdx < len) {
                        char next = str.charAt(nextIdx);
                        if (next == '+' || next == '-') break;
                        temp = temp * 10 + (next - '0');
                        nextIdx++;
                    }
                }
                if (ch == '+') {
                    sum += temp;
                } else if (ch == '-') {
                    sum -= temp;
                } else if (idx == 0) {
                    sum = temp;
                }
                idx = nextIdx;
            }

            if (sum == 0) {
                sb.append(total).append("\n");
            }
            return;
        }

        dfs(count + 1, total + ' ' + (count + 2), sb);
        dfs(count + 1, total + '+' + (count + 2), sb);
        dfs(count + 1, total + '-' + (count + 2), sb);
    }
}
