//package algorithm.M09.D0917;
//
//import java.io.*;
//import java.util.*;
//
//public class Main_BOJ_21611_마법사상어와블리자드 {
//	static int N, M;
//	static int[][] map;
//
//	static int[] dx = { -1, 1, 0, 0 };
//	static int[] dy = { 0, 0, -1, 1 };
//
//	static int[] d;
//	static int[] s;
//
//	static LinkedList<Node> marble = new LinkedList<>();
//
//	static int[] score = new int[4];
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//
//		map = new int[N][N];
//		d = new int[M];
//		s = new int[M];
//
//		for (int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < N; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		for (int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//
//			d[i] = Integer.parseInt(st.nextToken()) - 1;
//			s[i] = Integer.parseInt(st.nextToken());
//		}
//
//		getMarbles();
//
//		for (int i = 0; i < M; i++) {
//			magic(d[i], s[i]);
//			while (bomb()) {
//			}
//			change();
//		}
//
//		int ans = 0;
//		for (int i = 1; i <= 3; i++) {
//			ans += i * score[i];
//		}
//
//		System.out.println(ans);
//	}
//
//	private static void change() {
//		LinkedList<Node> temp = new LinkedList<>();
//
//		temp.add(new Node(0));
//
//		int length = marble.size();
//
//		int start = 1;
//
//		while (temp.size() < N * N && start < length) {
//			int end = start;
//			int count = 0;
//
//			while (end < length && marble.get(start).number == marble.get(end).number) {
//				count++;
//				end++;
//			}
//
//			int num = marble.get(start).number;
//
//			temp.add(new Node(count));
//			if (temp.size() < N * N)
//				temp.add(new Node(num));
//
//			start = end;
//		}
//
//		marble = temp;
//	}
//
//	private static boolean bomb() {
//		boolean flag = false;
//
//		int length = marble.size();
//
//		int start = length - 1;
//
//		while (start >= 0) {
//
//			int end = start;
//			int count = 0;
//
//			while (end >= 0 && marble.get(start).number == marble.get(end).number) {
//				count++;
//				end--;
//			}
//
//			if (count >= 4) {
//				flag = true;
//				int num = marble.get(start).number;
////				System.out.println("폭발---" + num + "그룹 " + count + "개 폭발");
//				score[num] += count;
//
//				int temp = start;
//				while (temp > end) {
//					marble.remove(temp--);
//				}
//			}
//			start = end;
//		}
//
//		return flag;
//	}
//
//	private static void magic(int d, int s) {
//		for (int i = s; i >= 1; i--) {
//			int a = 0, b = 0;
//			a = (2 * i + 1) * (2 * i + 1);
//			b = 0;
//
//			switch (d) {
//			case 0:
//				b = -1 - i;
//				break;
//			case 1:
//				b = -1 - 5 * i;
//				break;
//			case 2:
//				b = -1 - 7 * i;
//				break;
//			case 3:
//				b = -1 - 3 * i;
//				break;
//			}
//
//			int temp = a + b;
//			int num = marble.get(temp).number;
////			System.out.println("매직---" + temp + "위치의" + num + " 제거");
//			marble.remove(temp);
//		}
//	}
//
//	private static void getMarbles() {
//
//		boolean[][] visited = new boolean[N][N];
//
//		int x = 0;
//		int y = -1;
//		int[] dir = { 3, 1, 2, 0 };
//		int d = 0;
//
//		while (true) {
//			x += dx[dir[d]];
//			y += dy[dir[d]];
//
//			if (x == y && x == N / 2)
//				break;
//
//			if (x < 0 || x >= N || y < 0 || y >= N || visited[x][y]) {
//				x -= dx[dir[d]];
//				y -= dy[dir[d]];
//
//				d = (d + 1) % 4;
//				continue;
//			}
//
//			visited[x][y] = true;
//
//			if (map[x][y] == 0)
//				continue;
//
//			marble.addFirst(new Node(map[x][y]));
//		}
//		marble.addFirst(new Node(0));
//	}
//
//	static class Node {
//		int number;
//
//		Node(int number) {
//			this.number = number;
//		}
//	}
//}