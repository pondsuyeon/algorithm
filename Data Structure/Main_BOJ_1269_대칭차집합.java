package Y22.M05.D13;

import java.io.*;
import java.util.*;

public class Main_BOJ_1269_대칭차집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Set<Integer>[] set = new HashSet[2];
        set[0] = new HashSet<>();
        set[1] = new HashSet<>();

        int[] setSize = new int[2];

        st = new StringTokenizer(br.readLine());

        setSize[0] = Integer.parseInt(st.nextToken());
        setSize[1] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < setSize[i]; j++) {
                set[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int minIndex = setSize[0] < setSize[1] ? 0 : 1;
        int ans = setSize[0] + setSize[1];

        for (int num : set[minIndex]) {
            if (set[(minIndex + 1) % 2].contains(num)) ans -= 2;
        }

        System.out.println(ans);
    }
}
