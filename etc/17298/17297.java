import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] ans = new int[N];
        int[] stack = new int[N];

        int top = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        ans[N - 1] = -1;

        for (int i = N - 2; i >= 0; i--) {
            if (A[i] < A[i + 1]) {
                ans[i] = A[i + 1];
                stack[++top] = A[i + 1];
            } else {
                if (top > -1 && A[i] < stack[top]) {
                    ans[i] = stack[top];
                } else {
                    while (top > -1) {
                        if (A[i] < stack[top]) {
                            ans[i] = stack[top];
                            break;
                        }
                        top--;
                    }
                    if (top == -1) {
                        ans[i] = -1;
                        stack[++top] = A[i];
                    }

                }
            }
        }
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }
}