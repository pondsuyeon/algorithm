package algorithm.M12.D07;

import java.io.*;
import java.util.*;
public class Main_BOJ_15809_전국시대 {

	static int N, M;
	static int[] A;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i <= N ; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int O = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
		
			if(O == 1) {
				union(P, Q);
			}else {
				war(P, Q);
			}
		}
		
		List<Integer> alive = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			if(parent[i] == i && A[i] > 0)
				alive.add(A[i]);
		}
		
		Collections.sort(alive);
		
		sb.append(alive.size()+"\n");
		
		for(int left : alive) {
			sb.append(left+" ");
		}
		
		System.out.println(sb.toString());
	}

	private static void war(int p, int q) {
		p = find(p);
		q = find(q);
		
		if(A[p] > A[q]) {
			A[p] -= A[q];
			A[q] = 0;
		}else if(A[p] < A[q]) {
			A[q] -= A[p];
			A[p] = 0;
		}else {
			A[p] = 0;
			A[q] = 0;
		}
		
	}

	private static void union(int p, int q) {
		p = find(p);
		q = find(q);
		
		if(p == q) return;
		parent[q] = p;
		A[p] += A[p];
	}
	
	private static int find(int n) {
		if(parent[n] == n) 
			return n;
		
		return parent[n] = find(parent[n]);
		
	}
}
