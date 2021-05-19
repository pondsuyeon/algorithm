import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        Deque<Integer> dq = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String cmd = br.readLine();
            if (cmd.startsWith("push")) {
                int X = Integer.parseInt(cmd.split(" ")[1]);
                dq.offer(X);
            } else if (cmd.equals("pop")) {
                sb.append(dq.isEmpty() ? -1 : dq.poll()).append("\n");
            } else if (cmd.equals("size")) {
                sb.append(dq.size()).append("\n");
            } else if (cmd.equals("empty")) {
                sb.append(dq.isEmpty() ? 1 : 0).append("\n");
            } else if (cmd.equals("front")) {
                sb.append(dq.isEmpty() ? -1 : dq.peekFirst()).append("\n");
            } else if (cmd.equals("back")) {
                sb.append(dq.isEmpty() ? -1 : dq.peekLast()).append("\n");
            }
        }
        System.out.println(sb);
    }
}