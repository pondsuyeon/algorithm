import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());

        int x = 2;
        int y = 1;

        ArrayList<Integer> res = new ArrayList<>();
        while (x > y) {
            long temp = x * x - y * y;

            if (temp == G) {
                res.add(x);
                x++;
            } else if (temp > G) {
                y++;
            } else {
                x++;
            }
        }
        if (res.size() == 0) System.out.println(-1);
        else {
            for (int num : res) {
                System.out.println(num);
            }
        }
    }
}