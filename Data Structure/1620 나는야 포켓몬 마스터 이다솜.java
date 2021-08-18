import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Map<String, Integer> indices = new HashMap<>();
		String[] names = new String[N + 1];

		for (int i = 1; i <= N; i++) {
			String input = br.readLine();

			indices.put(input, i);
			names[i] = input;
		}

		for (int i = 1; i <= M; i++) {
			String input = br.readLine();

			boolean flag = true;
			for (int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				if (c < '0' || c > '9') {
					flag = false;
					break;
				}
			}
			if (flag) {
				int index = Integer.parseInt(input);
				sb.append(names[index]);
			} else {
				sb.append(indices.get(input));
			}
			sb.append('\n');
		}

		System.out.print(sb.toString());
	}
}