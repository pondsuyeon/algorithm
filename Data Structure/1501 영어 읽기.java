import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Map<String, int[]> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String word = br.readLine();

            if (!map.containsKey(word)) {
                map.put(word, getAlphabetCount(word));
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            int result = 1;

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int count = 0;
                String word = st.nextToken();
                int[] alphabetCount = getAlphabetCount(word);

                for (Map.Entry<String, int[]> entry : map.entrySet()) {
                    String key = entry.getKey();
                    int[] value = entry.getValue();
                    int len = word.length();

                    if (word.length() != key.length()) continue;
                    if (word.charAt(0) != key.charAt(0)) continue;
                    if (word.charAt(len - 1) != key.charAt(len - 1)) continue;

                    boolean flag = true;

                    for (int j = 0; j < 52; j++) {
                        if (alphabetCount[j] != value[j]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) count++;
                }
                result *= count;
            }
            System.out.println(result); 
        }
    }

    static int[] getAlphabetCount(String s) {
        int[] alphabetCount = new int[52];

        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if ('a' <= c && c <= 'z') {
                alphabetCount[c - 'a']++;
            } else {
                alphabetCount[c - 'A' + 26]++;
            }
        }
        return alphabetCount;
    }
}
