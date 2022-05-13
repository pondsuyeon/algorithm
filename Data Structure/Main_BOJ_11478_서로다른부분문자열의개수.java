package Y22.M05.D13;

import java.io.*;
import java.util.*;

public class Main_BOJ_11478_서로다른부분문자열의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<String> set = new HashSet<>();
        String S = br.readLine();

        int size = S.length();

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                set.add(S.substring(i, j + 1));
            }
        }

        System.out.println(set.size());
    }
}
