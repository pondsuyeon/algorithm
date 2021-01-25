import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();
        int idx = 0;

        for (int i = 0; i < N; i++) {
            arr.add(i + 1);
        }
        sb.append("<");
        while (true) {
            if (arr.size() <= 0)
                break;
            idx = (idx + K - 1) % arr.size();
            sb.append(arr.get(idx)).append(", ");
            arr.remove(idx);
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.deleteCharAt(sb.lastIndexOf(" "));
        sb.append(">");
        System.out.println(sb);
    }
}