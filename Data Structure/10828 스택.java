import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();

        int N = sc.nextInt();
        sc.nextLine();      // nextInt는 개행(\n)을 저장하지 않아서 다음에 개행이 들어가게 되면 오류가 남.

        while (N-- > 0) {
            String cmd = sc.nextLine();
            if (cmd.startsWith("push")) {
                int X = Integer.parseInt(cmd.split(" ")[1]);
                stack.push(X);
            } else if (cmd.equals("pop")) {
                if (stack.isEmpty()) result.add(-1);
                else result.add(stack.pop());
            } else if (cmd.equals("size")) {
                result.add(stack.size());
            } else if (cmd.equals("empty")) {
                result.add(stack.isEmpty() ? 1 : 0);
            } else if (cmd.equals("top")) {
                if (stack.isEmpty()) result.add(-1);
                else result.add(stack.peek());
            }
        }
        for (int num : result) {
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
}