import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String function = br.readLine();
            int n = Integer.parseInt(br.readLine());
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine(), "[],");

            while (st.hasMoreTokens()) {
                deque.offer(Integer.parseInt(st.nextToken()));
            }
            AC(function, deque);
        }
    }

    public static void AC(String function, ArrayDeque<Integer> deque) {
        StringBuilder sb = new StringBuilder();
        int funcLength = function.length();
        boolean reversed = false;
        boolean status = true;
        for (int i = 0; i < funcLength; i++) {
            if (function.charAt(i) == 'R') {
                reversed = !reversed;
            } else if (function.charAt(i) == 'D') {
                if (deque.isEmpty()) {
                    sb.append("error");
                    status = false;
                    break;
                } else {
                    if (reversed) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }
        }
        if (status) {
            sb.append("[");
            if (deque.size() > 1) {
                if (reversed) {
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollLast()).append(",");
                    }
                } else {
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollFirst()).append(",");
                    }
                }
                sb.deleteCharAt(sb.lastIndexOf(","));
            } else if (deque.size() == 1) {
                sb.append(deque.poll());
            }
            sb.append("]");
        }
        System.out.println(sb);
    }
}