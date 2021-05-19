import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int N, M;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> importance = new ArrayList<>();

        while (T > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            queue.clear();
            importance.clear();

            int cnt = 0;
            int idx = M;

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int temp = Integer.parseInt(st.nextToken());
                queue.offer(temp);
                importance.add(temp);
            }

            importance.sort(Collections.reverseOrder());

            while (true) {
                if (queue.peek().equals(importance.get(0))) {
                    cnt++;
                    if (idx == 0) {
                        sb.append(cnt).append("\n");
                        break;
                    } else {
                        queue.poll();
                        importance.remove(0);
                        idx--;
                    }
                } else {
                    if (idx == 0) {
                        idx = queue.size() - 1;
                    } else {
                        idx--;
                    }
                    queue.offer(queue.poll());
                }
            }
            T--;
        }
        System.out.println(sb);


    }
}