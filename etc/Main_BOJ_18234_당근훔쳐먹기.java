package algorithm.D0831;

import java.util.*;
import java.io.*;

public class Main_BOJ_18234_당근훔쳐먹기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		Carot[] carots = new Carot[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			carots[i] = new Carot(w, p);
		}
		
		Arrays.sort(carots, Collections.reverseOrder());
	
		long ans = 0;
		
		int t = T - 1;
		
		for(int i = 0; i < N; i++) {
			ans += carots[i].w + (long)carots[i].p * t--;
		}
		
		System.out.println(ans);
	}

	static class Carot implements Comparable<Carot> {
		int w, p;

		Carot(int w, int p) {
			this.w = w;
			this.p = p;
		}

		@Override
		public int compareTo(Carot o) {
			if (this.p != o.p)
				return this.p - o.p;
			return this.w - o.w;
		}
	}
}
