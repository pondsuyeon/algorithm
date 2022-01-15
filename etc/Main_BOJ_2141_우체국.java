import java.io.*;
import java.util.*;

public class Main_BOJ_2141_우체국 {


    static class Node implements Comparable<Node> {
        long x, a;

        Node(long x, long a) {
            this.x = x;
            this.a = a;
        }

        @Override
        public int compareTo(Node n) {
            if (this.x == n.x) return (int) (this.a - n.a);
            return (int) (this.x - n.x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        long total = 0;
        Node[] nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            total += nodes[i].a;
        }

        Arrays.sort(nodes);

        long sum = 0;

        for (Node node : nodes) {

            sum += node.a;

            if (sum >= (total + 1) / 2) {
                System.out.println(node.x);
                break;
            }
        }
    }
}
