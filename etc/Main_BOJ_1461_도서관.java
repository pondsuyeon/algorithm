import java.io.*;
import java.util.*;

public class Main_BOJ_1461_도서관 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int answer = 0;

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> minus = new PriorityQueue<>();
        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num > 0) plus.add(num);
            else if (num < 0) minus.add(num);
        }

        if (!minus.isEmpty() && !plus.isEmpty()) {
            if (minus.peek() + plus.peek() < 0) {
                answer += minus.peek();
            } else {
                answer -= plus.peek();
            }
        } else if (minus.isEmpty()) {
            answer -= plus.peek();
        } else if (plus.isEmpty()) {
            answer += minus.peek();
        }

        while (!minus.isEmpty()) {
            int count = M;
            answer += -2 * minus.peek();
            while (count-- > 0 && !minus.isEmpty()) {
                minus.poll();
            }
        }

        while (!plus.isEmpty()) {
            int count = M;
            answer += 2 * plus.peek();
            while (count-- > 0 && !plus.isEmpty()) {
                plus.poll();
            }
        }

        System.out.println(answer);
    }
}
