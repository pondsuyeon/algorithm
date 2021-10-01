package algorithm.M10.D01;

import java.io.*;
import java.util.*;

public class Main_BOJ_6198_옥상정원꾸미기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Building[] buildings = new Building[N + 1];

		for (int i = 0; i < N; i++) {
			long h = Long.parseLong(br.readLine());

			buildings[i] = new Building(i, h);
		}

		buildings[N] = new Building(N, Long.MAX_VALUE);

		Stack<Building> stack = new Stack<>();

		stack.add(buildings[N]);

		long ans = 0;
		for (int i = N - 1; i >= 0; i--) {
			while (buildings[i].height >= stack.peek().height) {
				stack.pop();
			}

			ans = ans + stack.peek().index - i - 1;

			stack.add(buildings[i]);
		}

		System.out.println(ans);
	}

	static class Building {
		int index;
		long height;

		Building(int index, long height) {
			this.index = index;
			this.height = height;
		}
	}
}