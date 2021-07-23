import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String game = br.readLine();

            if (game.equals("end")) break;

            char[][] map = new char[3][3];
            int X = 0;
            int O = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = game.charAt(3 * i + j);
                    if (map[i][j] == 'X') X++;
                    else if (map[i][j] == 'O') O++;
                }
            }
            boolean flag = false;
            if (X == O + 1) {
                if (check(map, 'X') && !check(map, 'O')) {
                    flag = true;
                } else if (!check(map, 'X') && !check(map, 'O') && X + O == 9) {
                    flag = true;
                }
            } else if (X == O) {
                if (!check(map, 'X') && check(map, 'O')) {
                    flag = true;
                }
            }

            if (flag) System.out.println("valid");
            else System.out.println("invalid");
        }
    }

    public static boolean check(char[][] map, char c) {
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == map[i][1] && map[i][1] == map[i][2] && map[i][0] == c) return true;
            else if (map[0][i] == map[1][i] && map[1][i] == map[2][i] && map[0][i] == c) return true;
        }
        if (map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[0][0] == c) return true;
        else if (map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[0][2] == c) return true;
        return false;
    }
}