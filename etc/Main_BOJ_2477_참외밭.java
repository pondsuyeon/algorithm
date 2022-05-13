package Y22.M05.D13;

import java.io.*;
import java.util.*;

public class Main_BOJ_2477_참외밭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        int[] count = new int[5];
        int[][] line = new int[6][2];
        int height = 0;
        int width = 0;

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());

            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());

            count[line[i][0]]++;

            if (line[i][0] == 1 || line[i][0] == 2) {
                width = Math.max(width, line[i][1]);
            } else {
                height = Math.max(height, line[i][1]);
            }

        }

        int first = 0;
        int last = 0;

        if (count[1] == 2 && count[3] == 2) {
            first = 1;
            last = 3;
        } else if (count[1] == 2 && count[4] == 2) {
            first = 4;
            last = 1;
        } else if (count[2] == 2 && count[4] == 2) {
            first = 2;
            last = 4;
        } else {
            first = 3;
            last = 2;
        }

        int ans = height * width;

        for (int i = 0; i < 6; i++) {
            if (line[i][0] == first && line[(i + 1) % 6][0] == last) {
                ans -= line[i][1] * line[(i + 1) % 6][1];
            }
        }
        ans *= K;
        System.out.println(ans);
    }
}
