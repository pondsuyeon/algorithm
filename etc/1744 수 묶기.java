import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> minus = new PriorityQueue<>();
        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        boolean zero = false;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) zero = true;
            else if (num < 0) minus.offer(num);
            else plus.offer(num);
        }
        int ans = 0;

        while (!minus.isEmpty() && minus.size() >= 2) {
            ans += minus.poll() * minus.poll();
        }
        if (!zero && !minus.isEmpty()) {
            ans += minus.poll();
        }
        while (!plus.isEmpty() && plus.size() >= 2) {
            int a = plus.poll();
            int b = plus.poll();

            ans += Math.max(a + b, a * b);
        }
        if (!plus.isEmpty()) {
            ans += plus.poll();
        }
        System.out.println(ans);
    }
}