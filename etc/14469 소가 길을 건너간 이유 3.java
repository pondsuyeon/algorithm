import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        EntryTime[] cow = new EntryTime[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cow[i] = new EntryTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(cow);
        int now = cow[0].start + cow[0].time;

        for (int i = 1; i < N; i++) {
            if (now <= cow[i].start) {
                now = cow[i].start + cow[i].time;
            } else {
                now += cow[i].time;
            }
        }
        System.out.println(now);
    }

    static class EntryTime implements Comparable<EntryTime> {
        int start;
        int time;

        EntryTime(int start, int time) {
            this.start = start;
            this.time = time;
        }

        @Override
        public int compareTo(EntryTime entryTime) {
            return this.start - entryTime.start;
        }
    }
}