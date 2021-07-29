import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Station> stations = new PriorityQueue<>(Comparator.comparingInt(s -> s.d));
        PriorityQueue<Station> pq = new PriorityQueue<>((s1, s2) -> -s1.v + s2.v);
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stations.offer(new Station(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int ans = 0;

        while (P < L) {
            while (!stations.isEmpty() && stations.peek().d <= +P) {
                pq.offer(stations.poll());
            }
            if (pq.isEmpty()) {
                ans = -1;
                break;
            }
            P += pq.poll().v;
            ans++;
        }
        System.out.println(ans);
    }

    static class Station {
        int d;
        int v;

        public Station(int d, int v) {
            this.d = d;
            this.v = v;
        }
    }
}