import java.io.*;
import java.util.*;

public class Main {
    static Map<String, Integer> hashMap;
    static int[] parent;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());
            parent = new int[F * 2 + 1];
            count = new int[F * 2 + 1];
            Arrays.fill(count, 1);
            int index = 1;

            hashMap = new HashMap<>();
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());

                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if (!hashMap.containsKey(f1)) {
                    parent[index] = index;
                    hashMap.put(f1, index++);
                }
                if (!hashMap.containsKey(f2)) {
                    parent[index] = index;
                    hashMap.put(f2, index++);
                }

                union(hashMap.get(f1), hashMap.get(f2));
                System.out.println(count[find(hashMap.get(f1))]);
            }
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;

        parent[y] = x;
        count[x] += count[y];
    }
}