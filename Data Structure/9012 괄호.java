import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(sc.nextLine());

        while (T-- > 0) {
            Stack<Character> stack = new Stack<>();
            String input = sc.nextLine();

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                if (!stack.isEmpty() && c == ')' && stack.peek() == '(')
                    stack.pop();
                else stack.push(c);
            }

            if (stack.isEmpty()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.print(sb);
    }
}
