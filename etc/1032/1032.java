import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] pattern;
        pattern = br.readLine().toCharArray();
        for (int i = 1; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < pattern.length; j++) {
                if (pattern[j] != temp[j]) {
                    pattern[j] = '?';
                }
            }
        }
        System.out.println(pattern);
    }
}
