import java.io.*;
import java.util.*;

public class Main_BOJ_2632_피자판매 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long pizzaSize = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long ans = 0;

        long[] aPizza = new long[m + 1];
        long[] bPizza = new long[n + 1];

        HashMap<Long, Long> map = new HashMap<>();

        for (int i = 1; i <= m; i++) {
            aPizza[i] = Integer.parseInt(br.readLine()) + aPizza[i - 1];

        }
        for (int i = 1; i <= n; i++) {
            bPizza[i] = Integer.parseInt(br.readLine()) + bPizza[i - 1];
        }

        map.put(0L, 1L);
        map.put(aPizza[m], 1L);

        for (int i = 1; i <= m; i++) {
            for (int j = i; j < i + m - 1; j++) {

                long pizza;
                if (j <= m) {
                    pizza = aPizza[j] - aPizza[i - 1];
                } else {
                    pizza = aPizza[j % m] + aPizza[m] - aPizza[i - 1];
                }

                if (!map.containsKey(pizza)) {
                    map.put(pizza, 0L);
                }
                map.put(pizza, map.get(pizza) + 1);
            }
        }

        if (map.containsKey(pizzaSize)) ans += map.get(pizzaSize);
        if (map.containsKey(pizzaSize - bPizza[n])) ans += map.get(pizzaSize - bPizza[n]);

        for (int i = 1; i <= n; i++) {
            for (int j = i; j < i + n - 1; j++) {
                long pizza;

                if (j <= n) {
                    pizza = bPizza[j] - bPizza[i - 1];
                } else {
                    pizza = bPizza[j % n] + bPizza[n] - bPizza[i - 1];
                }

                if (map.containsKey(pizzaSize - pizza)) {
                    ans += map.get(pizzaSize - pizza);
                }
            }
        }

        System.out.println(ans);
    }
}
