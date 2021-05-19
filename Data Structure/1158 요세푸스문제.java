import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        boolean[] popped = new boolean[N + 1];
        int[] result = new int[N];
        int index = 0;

        for (int i = 0; i < N; i++) {
            int T = 0;
            while (T < K) {
                if (index == N) index = 1;
                else index++;

                if (!popped[index]) T++;

            }
            result[i] = index;
            popped[index] = true;

        }

        System.out.print("<");
        for (int i = 0; i < N - 1; i++) {
            System.out.print(result[i] + ", ");
        }
        System.out.println(result[N - 1] + ">");
    }
}