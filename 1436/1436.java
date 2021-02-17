import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num = 666;
        int index = 0;
        while (index < N) {
            int temp = num;
            boolean status = false;
            while (temp != 0) {
                if (temp % 10 == 6 && (temp / 10) % 10 == 6 && (temp / 100) % 10 == 6) {
                    status = true;
                    break;
                }
                temp /= 10;
            }
            if (status) index++;
            if (index == N) break;
            num++;
        }
        System.out.println(num);
    }
}
