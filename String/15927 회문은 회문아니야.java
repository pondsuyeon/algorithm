import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        boolean flag1 = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                flag1 = false;
                break;
            }
        }
        if (flag1) {
            System.out.println(-1);
            return;
        }

        boolean flag2 = true;

        for (int i = 0; i <= arr.length / 2; i++) {
            int s = i;
            int e = arr.length - 1 - i;

            if (arr[s] != arr[e]) {
                flag2 = false;
                break;
            }
        }

        if (flag2) {
            System.out.println(arr.length - 1);
        } else {
            System.out.println(arr.length);
        }
    }
}

