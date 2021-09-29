package algorithm.M09.D29;

import java.io.*;
import java.util.*;

public class Main_BOJ_2109_순회강연 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		PriorityQueue<Node> courses = new PriorityQueue<>();
		PriorityQueue<Integer> pay = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			courses.offer(new Node(p, d));
		}

		while(!courses.isEmpty()) {
			Node course = courses.poll();
			if(pay.size() < course.d) {
				pay.offer(course.p);
			}else {
				if(pay.peek() < course.p) {
					pay.poll();
					pay.offer(course.p);
				}
			}			
		}
		
		int m = 0;
		
		while(!pay.isEmpty()) {
			m += pay.poll();
		}
		
		System.out.println(m);
	}

	static class Node implements Comparable<Node> {
		int p, d;

		Node(int p, int d) {
			this.p = p;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			if(this.d != o.d) return this.d - o.d;
			return -this.p + o.p;
		}
	}
}
