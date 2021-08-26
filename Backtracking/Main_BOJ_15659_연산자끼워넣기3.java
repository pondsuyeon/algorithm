import java.io.*;
import java.util.*;

public class Main_BOJ_15659_연산자끼워넣기3 {
	static int N;
	static int[] numbers;
	static int[] operators; // +, -, *, / 의 각각의 개수
	static int[] selected; // 선택된 연산자의 순서

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		numbers = new int[N];
		operators = new int[4];
		selected = new int[N - 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}

		permutation(0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void permutation(int count) {
		if (count == N - 1) {
			int res = calculate();
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operators[i] > 0) {
				selected[count] = i;
				operators[i]--;
				permutation(count + 1);
				operators[i]++;
			}
		}
	}

	private static int calculate() {
		// 후위연산자 만들기위해 연산자 담을 스택
		Stack<Character> stack1 = new Stack<>();
		int[] postfix = new int[2 * N - 1];
		boolean[] isNumber = new boolean[2 * N - 1];

		int index = 0;
		for (int i = 0; i < 2 * N - 1; i++) {
			if (i % 2 == 0) {
				postfix[index] = numbers[i / 2];
				isNumber[index++] = true;
			}

			else {
				char op = getOperator(selected[i / 2]);
				while (!stack1.isEmpty() && getPriority(stack1.peek()) <= getPriority(op)) {
					postfix[index++] = stack1.pop();
				}
				stack1.push(op);
			}
		}
		while (!stack1.isEmpty()) {
			postfix[index++] = stack1.pop();
		}

		// 계산할 숫자를 담을 스택
		Stack<Integer> stack2 = new Stack<>();

		for (int i = 0; i < 2 * N - 1; i++) {
			if (isNumber[i]) {
				stack2.push(postfix[i]);
			} else {
				int b = stack2.pop();
				int a = stack2.pop();

				char op = (char) postfix[i];
				if (op == '+') {
					stack2.push(a + b);
				} else if (op == '-') {
					stack2.push(a - b);
				} else if (op == '*') {
					stack2.push(a * b);
				} else if (op == '/') {
					stack2.push(a / b);
				}
			}
		}
		return stack2.pop();
	}

	private static char getOperator(int n) {
		if (n == 0)
			return '+';
		else if (n == 1)
			return '-';
		else if (n == 2)
			return '*';
		else
			return '/';
	}

	private static int getPriority(char op) {
		if (op == '+' || op == '-')
			return 1;
		else
			return 0;
	}
}