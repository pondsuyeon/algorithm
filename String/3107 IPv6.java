import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] group = new String[8];
        String input = br.readLine();

        if (input.contains("::")) {
            String[] temp = input.split("::");
            String[] before, after;
            int zeroStart = 0;
            int zeroEnd = 7;

            if (temp[0].length() > 0) {
                before = temp[0].split(":");
                System.arraycopy(before, 0, group, 0, before.length);
                zeroStart = before.length;
            }
            if (temp.length > 1) {
                after = temp[1].split(":");
                System.arraycopy(after, 0, group, 8 - after.length, after.length);
                zeroEnd = 8 - after.length - 1;
            }


            for (int i = zeroStart; i <= zeroEnd; i++) {
                group[i] = "0000";
            }
        } else {
            group = input.split(":");

        }
        for (int i = 0; i < group.length; i++) {
            while (group[i].length() < 4) {
                group[i] = "0" + group[i];
            }
        }
        for (String s : group) {
            sb.append(s).append(":");
        }
        sb.deleteCharAt(sb.lastIndexOf(":"));
        System.out.println(sb.toString());
    }
}