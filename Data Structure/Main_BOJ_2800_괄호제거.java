package algorithm.M10.D06;

import java.io.*;
import java.util.*;

public class Main_BOJ_2800_괄호제거 {

	static char[] s;

	static int P;
	static ArrayList<Pair> pairs = new ArrayList<>();

	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		s = br.readLine().toCharArray();

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < s.length; i++) {
			if (s[i] == '(') {
				stack.push(i);
			} else if (s[i] == ')') {
				pairs.add(0, new Pair(stack.pop(), i));
			}
		}

		P = pairs.size();
		removeBracket(0, 0);

		ArrayList<String> res = new ArrayList<>();

		for (String temp : set) {
			res.add(temp);
		}

		Collections.sort(res);

		for (String temp : res) {
			sb.append(temp).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void removeBracket(int index, int count) {
		if (index == P) {
			if (count == 0)
				return;
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < s.length; i++) {
				if (s[i] == '\0')
					continue;
				sb.append(s[i]);
			}
			set.add(sb.toString());
			return;
		}

		removeBracket(index + 1, count);

		Pair p = pairs.get(index);
		s[p.s] = '\0';
		s[p.e] = '\0';

		removeBracket(index + 1, count + 1);
		s[p.s] = '(';
		s[p.e] = ')';

	}

	static class Pair {
		int s, e;

		Pair(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}
