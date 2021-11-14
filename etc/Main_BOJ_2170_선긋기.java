package algorithm.M11.D14;

import java.io.*;
import java.util.*;

public class Main_BOJ_2170_선긋기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Line[] lines = new Line[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			lines[i] = new Line(s, e);
		}

		Arrays.sort(lines);

		int ans = 0;
		int s = lines[0].s, e = lines[0].e;
		for (int i = 1; i < N; i++) {
			if (lines[i].s <= e && lines[i].e >= e) {
				e = lines[i].e;
			} else if (e < lines[i].s) {
				ans += e - s;
				s = lines[i].s;
				e = lines[i].e;
			}
		}
		ans += e - s;

		System.out.println(ans);
	}

	static class Line implements Comparable<Line> {
		int s, e;

		Line(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Line l) {
			if (this.s != l.s)
				return this.s - l.s;
			return this.e - l.e;
		}
	}
}
