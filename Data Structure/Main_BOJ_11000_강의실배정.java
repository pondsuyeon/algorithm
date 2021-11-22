package algorithm.M11.D22;

import java.io.*;
import java.util.*;

public class Main_BOJ_11000_강의실배정 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Node[] lectures = new Node[N];
		boolean[] selected = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			lectures[i] = new Node(s, t);
		}

		Arrays.sort(lectures);
 
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.add(lectures[0].t);

		for(int i =1 ; i< N; i++) {
			if(pq.peek() <= lectures[i].s) {
				pq.poll();
			}
			pq.add(lectures[i].t);
		}
		
		System.out.println(pq.size());
		
	}

	static class Node implements Comparable<Node> {
		int s, t;

		Node(int s, int t) {
			this.s = s;
			this.t = t;
		}

		@Override
		public int compareTo(Node n) {
			if (this.s != n.s)
				return this.s - n.s;
			return this.t - n.t;
		}
	}
}
