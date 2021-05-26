import java.io.*;
import java.util.*;

public class Main {
    static int[] seq;
    static int N;
    static int[] ans;
    static boolean finished = false;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        seq = new int[N];

        goodSequence(0);

        for (int i = 0; i < N; i++) {
            System.out.print(ans[i]);
        }
        System.out.println();
    }

    static void goodSequence(int index) {
        if (finished) return;

        if (N == index) {
            if (sequenceCheck(N)) {
                ans = seq.clone();
                finished = true;
            }
            return;
        }
        if (!sequenceCheck(index)) return;

        for (int i = 1; i <= 3; i++) {
            seq[index] = i;
            goodSequence(index + 1);
            seq[index] = 0;
        }
    }

    static boolean sequenceCheck(int length) {
        for (int i = 0; i < length - 1; i++) {
            for (int d = 1; d <= (length - i) / 2; d++) {
                boolean status = true;
                for (int k = 0; k < d; k++) {
                    if (seq[i + k] != seq[i + d + k]) {
                        status = false;
                        break;
                    }
                }
                if (status) return false;
            }
        }
        return true;
    }
}