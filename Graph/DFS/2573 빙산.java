import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] iceberg = new int[N][M];
        int year = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                iceberg[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (isOneIceberg(iceberg) == 1) {        // 빙산이 하나일 경우에 계속 녹임, 0인 경우, 2이상인 경우 멈춤
            meltIceberg(iceberg);                   // 빙산이 녹도록 함수 호출
            year++;
        }
        if (isOneIceberg(iceberg) == 0) year = 0;   // 빙산이 다 녹은 상태일 경우 0을 출력하기 위함
        System.out.println(year);
    }

    static void meltIceberg(int[][] iceberg) {
        int N = iceberg.length;
        int M = iceberg[0].length;

        int[][] temp = new int[N][M];           // temp 사용 이유: for문을 통해 iceberg 자체의 값을 변경시키면 다음 행이나 열에 영향받을 수 있음.
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (iceberg[i][j] != 0) {
                    int count = 0;                    // 현재 위치 주변에 바다인 경우 카운트
                    for (int d = 0; d < 4; d++) {
                        int tx = i + dx[d];
                        int ty = j + dy[d];

                        if (tx >= 0 && tx < N && ty >= 0 && ty < M && iceberg[tx][ty] == 0)
                            count++;
                    }

                    temp[i][j] = Math.max(iceberg[i][j] - count, 0);               // 음수 방지
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                iceberg[i][j] = temp[i][j];             // 처리 후 다시 카피 
            }
        }
    }

    static int isOneIceberg(int[][] iceberg) {
        int N = iceberg.length;
        int M = iceberg[0].length;

        int[][] numbering = new int[N][M];
        int num = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (iceberg[i][j] != 0 && numbering[i][j] == 0) {      // 빙하 넘버링
                    if (num >= 1) return ++num;
                    else {
                        numberingIceberg(++num, i, j, iceberg, numbering);
                    }
                }
            }
        }
        return num;
    }

    // 재귀를 이용해 DFS 구현
    static void numberingIceberg(int num, int x, int y, int[][] iceberg, int[][] numbering) {
        int N = iceberg.length;
        int M = iceberg[0].length;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        numbering[x][y] = num;

        for (int d = 0; d < 4; d++) {
            int tx = x + dx[d];
            int ty = y + dy[d];

            if (tx >= 0 && tx < N && ty >= 0 && ty < M && iceberg[tx][ty] != 0 && numbering[tx][ty] == 0) {
                numbering[tx][ty] = num;
                numberingIceberg(num, tx, ty, iceberg, numbering);
            }
        }
    }
}