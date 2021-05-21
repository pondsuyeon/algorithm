import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] A = new int[N + 1][N + 1];
        int[][] district = new int[N + 1][N + 1];

        int MIN = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int d1 = 1; d1 < N; d1++) {
            for (int d2 = 1; d1 + d2 <= N; d2++) {
                for (int x = 1; x + d1 + d2 <= N; x++) {
                    for (int y = 1 + d1; y <= N - d2; y++) {

                        for (int i = 1; i <= N; i++) {
                            Arrays.fill(district[i], 0);
                        }
                        int[] counts = new int[N + 1];
                        int max = 0;
                        int min = Integer.MAX_VALUE;

                        district5(x, y, d1, d2, district);      // 5번 선거구 먼저 지정
                        
                        district1234(1, 1, x + d1 - 1, 1, y, district);         // 각각 범위에 맞게 해당 선거구 지정
                        district1234(2, 1, x + d2, y + 1, N, district);
                        district1234(3, x + d1, N, 1, y - d1 + d2 - 1, district);
                        district1234(4, x + d2 + 1, N, y - d1 + d2, N, district);

                        for (int i = 1; i <= N; i++) {
                            for (int j = 1; j <= N; j++) {
                                counts[district[i][j]] += A[i][j];              // N * N 모두 탐색해 해당 구역의 값에 A[r][c] 값 합산
                            }
                        }

                        for (int i = 1; i <= 5; i++) {
                            min = Math.min(min, counts[i]);                    // 5개 선거구 중 최소, 최대 값 계산
                            max = Math.max(max, counts[i]);
                        }

                        MIN = Math.min(MIN, max - min);                        // 차이 최소 값 저장
                    }
                }
            }
        }
        System.out.println(MIN);
    }

    static void district5(int x, int y, int d1, int d2, int[][] district) {
        if (d1 == 0 && d2 == 0) {
            district[x][y] = 5;
        } else {
            for (int i = 0; i <= d1; i++) {
                district[x + i][y - i] = 5;
            }
            for (int i = 0; i <= d2; i++) {
                district[x + i][y + i] = 5;
            }
            for (int i = 0; i <= d2; i++) {
                district[x + d1 + i][y - d1 + i] = 5;
            }
            for (int i = 0; i <= d1; i++) {
                district[x + d2 + i][y + d2 - i] = 5;
            }
            district5(x + 1, y, Math.max(d1 - 1, 0), Math.max(d2 - 1, 0), district);

        }
    }

    static void district1234(int num, int rFrom, int rTo, int cFrom, int cTo, int[][] district) {
        for (int r = rFrom; r <= rTo; r++) {
            for (int c = cFrom; c <= cTo; c++) {
                if (district[r][c] != 5) district[r][c] = num;
            }
        }
    }
}