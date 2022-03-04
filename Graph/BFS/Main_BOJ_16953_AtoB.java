
import java.io.*;
import java.util.*;

public class Main_BOJ_16953_AtoB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        System.out.println(AtoB(A, B));
    }

    private static int AtoB(long A, long B) {
        HashSet<Long> set = new HashSet<>();
        Queue<Long> q = new LinkedList<>();
        q.add(A);
        set.add(A);

        int time = 0;

        while (!q.isEmpty()) {
            time++;

            int size = q.size();
            while (size-- > 0) {
                long next = q.poll();

                if (next == B) return time;

                if (next > B) continue;

                if (!set.contains(next << 1)) {
                    set.add(next << 1);
                    q.add(next << 1);
                }
                if (!set.contains(next * 10 + 1)) {
                    set.add(next * 10 + 1);
                    q.add(next * 10 + 1);
                }
            }
        }
        return -1;
    }
}
