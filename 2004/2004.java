import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        System.out.println(Math.min(countTwo(n) - countTwo(m) - countTwo(n - m), countFive(n) - countFive(m) - countFive(n - m)));
    }

    public static int countTwo(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt += n / 2;
            n /= 2;
        }
        return cnt;
    }

    public static int countFive(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt += n / 5;
            n /= 5;
        }
        return cnt;
    }

}
