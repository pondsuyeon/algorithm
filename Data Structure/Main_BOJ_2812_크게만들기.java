package algorithm.M10.D07;

import java.io.*;
import java.util.*;

public class Main_BOJ_2812_크게만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		char[] num = br.readLine().toCharArray();

		int count = 0;

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && stack.peek() < num[i] && count < K) {
				stack.pop();
				count++;
			}

			stack.add(num[i]);
		}

		while (count < K) {
			stack.pop();
			count++;
		}
		for (char c : stack) {
			sb.append(c);
		}
		System.out.println(sb.toString());
	}
}