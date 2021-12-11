package algorithm.M12.D11;

import java.io.*;
import java.util.*;

public class Main_BOJ_1918_후위표기식 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Map<Character, Integer> priority = new HashMap<>();

		priority.put('(', 1);
		priority.put(')', 1);
		priority.put('*', 2);
		priority.put('/', 2);
		priority.put('+', 3);
		priority.put('-', 3);

		Stack<Character> stack = new Stack<>();

		char[] infix = br.readLine().toCharArray();

		for (int i = 0; i < infix.length; i++) {

			if (priority.containsKey(infix[i])) {

				if (infix[i] == ')') {
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.pop());
					}
					if (!stack.isEmpty() && stack.peek() == '(')
						stack.pop();
				} else {
					while (!stack.isEmpty() && stack.peek() != '('
							&& priority.get(stack.peek()) <= priority.get(infix[i])) {
						sb.append(stack.pop());
					}
					stack.add(infix[i]);
				}
			} else {
				sb.append(infix[i]);
			}

		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.toString());
	}

}
