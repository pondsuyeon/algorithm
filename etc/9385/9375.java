import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<String> kinds = new ArrayList<>();
            int[] cnt = new int[n];
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String kind = st.nextToken();

                int index = kinds.indexOf(kind);
                if (index == -1) {
                    kinds.add(kind);
                    index = kinds.size() - 1;
                }
                cnt[index]++;
            }
            int ans = 1;
            for (int i = 0; i < kinds.size(); i++) {
                ans *= cnt[i] + 1;
            }
            ans--;
            System.out.println(ans);
        }
    }
}
