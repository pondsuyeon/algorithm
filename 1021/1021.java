import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Integer> num = new LinkedList<>();
        ArrayList<Integer> queue = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            num.offer(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < N; i++) {
            queue.add(i + 1);
        }
        int cnt = 0;
        int now = 0;
        int temp, a, b;

        while (!num.isEmpty()) {
            temp = queue.indexOf(num.peek());
            a = ((now - temp) % queue.size() >= 0) ? (now - temp) % queue.size() : ((now - temp) % queue.size() + queue.size());
            b = ((-now + temp) % queue.size() >= 0) ? (-now + temp) % queue.size() : ((-now + temp) % queue.size() + queue.size());
            cnt += Math.min(a, b);
            now = temp;
            queue.remove(now);
            num.poll();
        }
        System.out.println(cnt);
    }
}