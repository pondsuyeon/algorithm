import java.io.*;
import java.util.*;

public class Main_BOJ_12764_싸지방에간준하 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Node[] person = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            person[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(person, (p1, p2) -> p1.a - p2.a);
        PriorityQueue<Node> use = new PriorityQueue<>((p1, p2) -> p1.b - p2.b);
        PriorityQueue<Integer> number = new PriorityQueue<>();

        int[] count = new int[100001];

        int max = 0;

        for (Node p : person) {

            while (!use.isEmpty() && use.peek().b < p.a) {
                number.add(use.poll().a);
            }

            int seat = number.isEmpty() ? ++max : number.poll();

            count[seat]++;
            p.a = seat;
            use.add(p);
        }

        sb.append(max).append("\n");
        for (int i = 1; i <= max; i++) {
            sb.append(count[i]).append(" ");
        }
        System.out.println(sb);
    }

    static class Node {
        int a, b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
