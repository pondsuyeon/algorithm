import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] wall = new int[M][N];
        int[][] roomNumber = new int[M][N];
        int[] sumArea;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                wall[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = numberOfRooms(wall, roomNumber);
        sumArea = new int[count + 1];

        int largestArea = largestRoomArea(count, roomNumber, sumArea);
        int combinedArea = largestCombinedArea(wall, roomNumber, sumArea);

        System.out.println(count);
        System.out.println(largestArea);
        System.out.println(combinedArea);
    }

    static int numberOfRooms(int[][] wall, int[][] roomNumber) {
        int M = wall.length;
        int N = wall[0].length;

        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (roomNumber[i][j] == 0) {
                    roomDFS(++count, i, j, wall, roomNumber);
                    ;
                }
            }
        }
        return count;
    }

    static void roomDFS(int num, int x, int y, int[][] wall, int[][] roomNumber) {
        int M = wall.length;
        int N = wall[0].length;

        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        roomNumber[x][y] = num;

        String binary = Integer.toBinaryString(wall[x][y]);
        while (binary.length() != 4) {
            binary = '0' + binary;
        }

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (binary.charAt(3 - i) == '0' && tx >= 0 && tx < M && ty >= 0 && ty < N && roomNumber[tx][ty] == 0) {
                roomDFS(num, tx, ty, wall, roomNumber);
            }
        }
    }

    static int largestRoomArea(int count, int[][] roomNumber, int[] sumArea) {
        int M = roomNumber.length;
        int N = roomNumber[0].length;

        int max = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                sumArea[roomNumber[i][j]]++;
            }
        }

        for (int i = 1; i <= count; i++) {
            max = Math.max(max, sumArea[i]);
        }

        return max;
    }

    static int largestCombinedArea(int[][] wall, int[][] roomNumber, int[] sumArea) {
        int M = roomNumber.length;
        int N = roomNumber[0].length;

        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        int max = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int d = 0; d < 4; d++) {
                    int tx = i + dx[d];
                    int ty = j + dy[d];

                    if (tx >= 0 && tx < M && ty >= 0 && ty < N && roomNumber[i][j] != roomNumber[tx][ty]) {
                        max = Math.max(max, sumArea[roomNumber[i][j]] + sumArea[roomNumber[tx][ty]]);
                    }
                }
            }
        }
        return max;
    }
}