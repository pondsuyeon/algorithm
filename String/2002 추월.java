import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int result = 0;
        Queue<String> input = new LinkedList<>();
        Queue<String> output = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            input.offer(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            output.offer(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            if (!input.peek().equals(output.peek())) {
                input.remove(output.poll());
                result++;
            } else {
                input.poll();
                output.poll();
            }
        }
        System.out.println(result);
    }
}