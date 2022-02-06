import java.io.*;

public class Main_BOJ_16472_고냥이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] word = br.readLine().toCharArray();

        int[] check = new int[26];
        int count = 0;
        int max = 0;

        int s = 0;
        int e = -1;

        for (char w : word) {

            if (check[w - 'a'] == 0) {
                while (count >= N) {
                    char ch = word[s];

                    if (--check[ch - 'a'] == 0)
                        count--;
                    s++;
                }
                count++;
            }
            check[w - 'a']++;
            e++;

            max = Math.max(e - s + 1, max);
        }

        System.out.println(max);

    }
}
