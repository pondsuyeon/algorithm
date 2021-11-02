package algorithm.M11.D02;

import java.io.*;
import java.util.*;

public class Main_BOJ_16434_드래곤앤던전 {

	static int N, ATK;
	static int[] t, a, h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ATK = Integer.parseInt(st.nextToken());

		t = new int[N];
		a = new int[N];
		h = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			t[i] = Integer.parseInt(st.nextToken());
			a[i] = Integer.parseInt(st.nextToken());
			h[i] = Integer.parseInt(st.nextToken());
		}

		long s = 1, e = (long) N * 1000001 * 1000001;
		long ans = e;

		while (s <= e) {

			long mid = (s + e) / 2;

			if (battle(mid, mid, ATK, 0)) {
				ans = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		System.out.println(ans);
	}

	private static boolean battle(long maxHP, long curHP, long curATK, int roomNumber) {
		if (roomNumber == N) {
			return true;
		}

		if (t[roomNumber] == 1) {

			int monster = h[roomNumber];

			long monsterAttackedCount = (monster % curATK == 0) ? (monster / curATK) : (monster / curATK + 1);

			long userAttackedCount = (curHP % a[roomNumber] == 0) ? (curHP / a[roomNumber])
					: (curHP / a[roomNumber] + 1);

			if (monsterAttackedCount <= userAttackedCount) {
				curHP -= (monsterAttackedCount - 1) * a[roomNumber];
				return battle(maxHP, curHP, curATK, roomNumber + 1);
			} else {
				return false;
			}
		} else {

			curATK += a[roomNumber];
			curHP = Math.min(maxHP, curHP + h[roomNumber]);

			return battle(maxHP, curHP, curATK, roomNumber + 1);
		}
	}
}
