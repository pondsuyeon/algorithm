import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) < Math.abs(o2) || Math.abs(o1) == Math.abs(o2) && o1 < o2)
                return -1;
            else return 1;
        });

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                if (!priorityQueue.isEmpty()) {
                    sb.append(priorityQueue.poll()).append("\n");
                } else
                    sb.append(0).append("\n");
            } else
                priorityQueue.add(input);
        }
        System.out.print(sb);
    }
}