import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder("");

        Deque<Integer> deque = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());
        String cmd = null;
        int X;

        while (N > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            if (cmd.equals("push_front")) {
                X = Integer.parseInt(st.nextToken());
                deque.addFirst(X);
            } else if (cmd.equals("push_back")) {
                X = Integer.parseInt(st.nextToken());
                deque.addLast(X);
            } else if (cmd.equals("pop_front")) {
                sb.append(((!deque.isEmpty()) ? deque.pollFirst() : -1) + "\n");
            } else if (cmd.equals("pop_back")) {
                sb.append(((!deque.isEmpty()) ? deque.pollLast() : -1) + "\n");
            } else if (cmd.equals("size")) {
                sb.append(deque.size()).append("\n");
            } else if (cmd.equals("empty")) {
                sb.append(((deque.isEmpty()) ? 1 : 0) + "\n");
            } else if (cmd.equals("front")) {
                sb.append(((!deque.isEmpty()) ? deque.getFirst() : -1) + "\n");
            } else {
                sb.append(((!deque.isEmpty()) ? deque.getLast() : -1) + "\n");
            }
            N--;
        }
        System.out.println(sb);
    }
}