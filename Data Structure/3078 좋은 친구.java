import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long res = 0;

		Queue<Integer>[] student = new LinkedList[21];

		for (int i = 0; i < 21; i++) {
			student[i] = new LinkedList<>();
		}

		for (int i = 0; i < N; i++) {
			int len = br.readLine().length();
			while(!student[len].isEmpty() && i - student[len].peek() > K) {
				student[len].poll();
			}
			res += student[len].size();
			student[len].offer(i);
		}

		System.out.println(res);
	}
}