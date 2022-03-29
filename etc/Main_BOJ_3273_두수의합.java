import java.io.*;
import java.util.*;

public class Main_BOJ_3273_두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] index = new int[1000001];
        int[] temp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
            index[temp[i]] = i;
        }

        int X = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 1; i <= N; i++) {
            if (X > temp[i] && X - temp[i] < 1000001 && i < index[X - temp[i]]) count++;
        }

        System.out.println(count);
    }
}
