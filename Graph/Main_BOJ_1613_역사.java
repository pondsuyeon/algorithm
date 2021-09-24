package algorithm.M09.D0924;
import java.io.*;
import java.util.*;

public class Main_BOJ_1613_역사 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int INF = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] d = new int[N + 1][N + 1];
		
		for(int i = 0; i <= N; i++) {
			Arrays.fill(d[i], INF);
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			d[u][v] = 1;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					
					if(d[i][k] == INF || d[k][j] == INF) continue;
					
					d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
		
		int S = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if(d[u][v] == INF && d[v][u] == INF) sb.append(0);
			else if(d[u][v] == INF)sb.append(1);
			else if(d[v][u] == INF) sb.append(-1);
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
