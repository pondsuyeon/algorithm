import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] dice;
    static int ans;
    static int[] temp = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dice = new int[N][6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = 0;
        getMaxAreaOfDice(0, 0, 0);
        System.out.println(ans);
    }

    public static void getMaxAreaOfDice(int index, int top, int sum) {
        if (index == 0) {
            for (int i = 0; i < 6; i++) {
                int opposite = temp[i];
                int max = 0;
                for (int j = 0; j < 6; j++) {
                    if (j != i && j != opposite) {
                        max = Math.max(max, dice[index][j]);
                    }
                }
                getMaxAreaOfDice(index + 1, dice[index][i], max);
            }
        } else if (index >= N) {
            ans = Math.max(ans, sum);
        } else {
            for (int i = 0; i < 6; i++) {
                if (top == dice[index][i]) {
                    int opposite = temp[i];
                    int max = 0;
                    for (int j = 0; j < 6; j++) {
                        if (j != i && j != opposite) {
                            max = Math.max(max, dice[index][j]);
                        }
                    }
                    getMaxAreaOfDice(index + 1, dice[index][opposite], sum + max);
                    break;
                }
            }
        }
    }
}