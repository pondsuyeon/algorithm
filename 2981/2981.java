import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        ArrayList<Integer> Ms = new ArrayList<>();
        int sub = arr[T - 1] - arr[0];
        for (int i = 2; i <= Math.sqrt(sub); i++) {
            if (sub % i == 0) {
                Ms.add(i);
                if (sub / i != i) Ms.add(sub / i);
            }
        }
        Ms.add(sub);
        Collections.sort(Ms);
        for (Integer m : Ms) {
            int R = arr[0] % m;
            boolean status = true;
            for (int i = 1; i < T; i++) {
                if (arr[i] % m != R) {
                    status = false;
                    break;
                }
            }
            if (status)
                sb.append(m).append(" ");
        }
        System.out.println(sb);
    }
}
