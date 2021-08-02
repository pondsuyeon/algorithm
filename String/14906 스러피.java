import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println("SLURPYS OUTPUT");
        while (N-- > 0) {
            String s = br.readLine();

            if (isSlurpy(s)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        System.out.println("END OF OUTPUT");
    }

    public static boolean isSlurpy(String string) {
        int mid = string.lastIndexOf('C');

        if (mid == -1) {
            mid = string.lastIndexOf('H');
            if (mid != 1) return false;
        }

        return isSlimp(string, 0, mid) && isSlump(string, mid + 1, string.length() - 1);

    }

    public static boolean isSlimp(String string, int s, int e) {
        if (s >= e) return false;

        if (s + 1 == e) {
            return string.charAt(s) == 'A' && string.charAt(e) == 'H';
        }

        if (string.charAt(s) == 'A' && string.charAt(e) == 'C') {
            if (isSlump(string, s + 1, e - 1)) return true;
            if (string.charAt(s + 1) == 'B') return isSlimp(string, s + 2, e - 1);
        }
        return false;
    }

    public static boolean isSlump(String string, int s, int e) {
        if (s >= e) return false;
        if (string.charAt(s) != 'D' && string.charAt(s) != 'E') return false;

        int index = s + 1;

        while (index < e && string.charAt(index) == 'F') {
            index++;
        }
        if (index == e && string.charAt(e) == 'G') return true;
        return isSlump(string, index, e);
    }
}