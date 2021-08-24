import java.io.*;

public class Main {
	static int N;
	static char[] arr;

	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = br.readLine().toCharArray();

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)
				arr[i] -= '0';
		}

		addBracket(1, arr[0]);
		System.out.println(max);
	}

	private static void addBracket(int index, int prev) {
		if (index == N) {
			max = Math.max(max, prev);
			return;
		}

		if (arr[index] == '+') {
			addBracket(index + 2, prev + arr[index + 1]);
			if (index + 3 < N) {
				if (arr[index + 2] == '+') {
					addBracket(index + 4, prev + (arr[index + 1] + arr[index + 3]));
				} else if (arr[index + 2] == '-') {
					addBracket(index + 4, prev + (arr[index + 1] - arr[index + 3]));
				} else {
					addBracket(index + 4, prev + (arr[index + 1] * arr[index + 3]));
				}
			}
		} else if (arr[index] == '-') {
			addBracket(index + 2, prev - arr[index + 1]);
			if (index + 3 < N) {
				if (arr[index + 2] == '+') {
					addBracket(index + 4, prev - (arr[index + 1] + arr[index + 3]));
				} else if (arr[index + 2] == '-') {
					addBracket(index + 4, prev - (arr[index + 1] - arr[index + 3]));
				} else {
					addBracket(index + 4, prev - (arr[index + 1] * arr[index + 3]));
				}
			}
		} else {
			addBracket(index + 2, prev * arr[index + 1]);

			if (index + 3 < N) {
				if (arr[index + 2] == '+') {
					addBracket(index + 4, prev * (arr[index + 1] + arr[index + 3]));
				} else if (arr[index + 2] == '-') {
					addBracket(index + 4, prev * (arr[index + 1] - arr[index + 3]));
				} else {
					addBracket(index + 4, prev * (arr[index + 1] * arr[index + 3]));
				}
			}
		}
	}
}