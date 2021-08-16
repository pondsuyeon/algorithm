import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int countA = Integer.parseInt(st.nextToken());
        int countB = Integer.parseInt(st.nextToken());
        int maxA = Integer.parseInt(st.nextToken());
        int maxB = Integer.parseInt(st.nextToken());

        long a = 0;
        long b = 0;
        if (maxA != 0 && maxB != 0) {

            if (countA > countB) {
                a = countB + 1;
                b = countB;

                a = Math.min(countA, a * maxA);
            } else if (countA < countB) {
                a = countA;
                b = countA + 1;

                b = Math.min(countB, b * maxB);
            } else {
                a = countA;
                b = countB;
            }
        } else if (maxA == 0 && maxB != 0) {
            b = Math.min(countB, maxB);
        } else if (maxA != 0) {
            a = Math.min(countA, maxA);
        }
        System.out.println(a + b);
    }
}