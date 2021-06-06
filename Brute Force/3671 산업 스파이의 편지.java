import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Integer> q;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(br.readLine());
        int max = 10000000;
        boolean[] a = new boolean[max];

        Arrays.fill(a, true);
        a[0] = false;
        a[1] = false;

        for (int i = 2; i < max; i++) {
            if (!a[i]) continue;

            for (int j = i * 2; j < max; j += i) {
                a[j] = false;
            }
        }

        while (c-- > 0) {
            String s = br.readLine();
            int[] arr = new int[s.length()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = s.charAt(i) - '0';
            }
            checked = new boolean[arr.length];
            q = new PriorityQueue<>();

            getNumbers(-1, arr);

            int ans = 0;
            int t = -1;
            while (!q.isEmpty()) {
                int num = q.poll();

                if (t != num && a[num]) {
                    ans++;
                    t = num;
                }
            }
            System.out.println(ans);
        }
    }


    static void getNumbers(int num, int[] arr) {
        if (num == -1) num = 0;
        else {
            q.offer(num);
        }

        for (int i = 0; i < arr.length; i++) {
            if (!checked[i]) {
                checked[i] = true;
                getNumbers(num * 10 + arr[i], arr);
                checked[i] = false;
            }
        }
    }
}