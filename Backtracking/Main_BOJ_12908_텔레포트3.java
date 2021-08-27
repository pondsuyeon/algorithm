import java.util.*;
import java.io.*;

public class Main_BOJ_12908_텔레포트3 {
	static int xs, ys, xe, ye;
	static Teleport[] tp = new Teleport[3];

	static long min = Long.MAX_VALUE;
	static int MAX_COUNT;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		xs = Integer.parseInt(st.nextToken());
		ys = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		xe = Integer.parseInt(st.nextToken());
		ye = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			tp[i] = new Teleport(x1, y1, x2, y2);
		}

		for (int i = 0; i <= 3; i++) {
			MAX_COUNT = i;
			goHome(xs, ys, 0, 0, 0);
		}
		System.out.println(min);
	}

	private static void goHome(int x, int y, int count, long distance, int flag) {
		if (min < distance)
			return;

		if (count == MAX_COUNT) {
			min = Math.min(min, distance + getDistance(x, y, xe, ye));
			return;
		}

		for (int i = 0; i < 3; i++) {
			if ((flag & (1 << i)) != 0)
				continue;
			goHome(tp[i].x2, tp[i].y2, count + 1, 10 + distance + getDistance(x, y, tp[i].x1, tp[i].y1), flag | 1 << i);
			goHome(tp[i].x1, tp[i].y1, count + 1, 10 + distance + getDistance(x, y, tp[i].x2, tp[i].y2), flag | 1 << i);
		}
	}

	private static long getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	static class Teleport {
		int x1, y1, x2, y2;

		Teleport(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
}