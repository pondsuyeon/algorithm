package Y22.M05.D12;

import java.io.*;
import java.util.*;

public class Main_BOJ_13422_도둑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int answer = 0;

            int[] house = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                house[i] = Integer.parseInt(st.nextToken());
            }

            int sum = 0;

            for (int i = 0; i < M; i++) {
                sum += house[i];
            }

            if (sum < K) {
                answer++;
            }

            if (M < N) {
                for (int i = 1; i < N; i++) {
                    sum = sum - house[i - 1] + house[(i - 1 + M) % N];
                    if (sum < K) {
                        answer++;
                    }
                }
            }

            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
