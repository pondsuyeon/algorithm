package algorithm.M10.D27;

import java.io.*;
import java.util.*;

public class Main_BOJ_23290_마법사상어와복제 {

	static int N = 4;
	static int M, S;

	static Queue<Node> fish;
	static Queue<Node> copied;

	static int[][] fishCount;
	static int[][] smell;

	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static int[] sx = { -1, 0, 1, 0 };
	static int[] sy = { 0, -1, 0, 1 };

	static Node shark;

	static int[] maxSharkMovement;
	static int[] sharkMovement;
	static int maxEatableFishCount;

	static int time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		fish = new LinkedList<>();

		smell = new int[N + 1][N + 1];
		fishCount = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());
			int fd = Integer.parseInt(st.nextToken()) - 1;

			fishCount[fx][fy]++;
			Node f = new Node(fx, fy, fd);
			fish.add(f);
		}

		st = new StringTokenizer(br.readLine());
		shark = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

		time = 0;
		while (time < S) {
			time++;
			copied = new LinkedList<Node>(fish);

			fishMove();

			sharkMove();

			removeSmell();

			Queue<Node> temp = new LinkedList<>();

			while (!fish.isEmpty()) {
				Node f = fish.poll();
				if (fishCount[f.x][f.y] == 0)
					continue;
				temp.add(f);
			}

			fish = temp;

			while (!copied.isEmpty()) {
				Node f = copied.poll();
				fishCount[f.x][f.y]++;
				fish.add(f);
			}
		}
		int cnt = fish.size();
		System.out.println(cnt);
	}

	private static void removeSmell() {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (smell[i][j] != 0 && smell[i][j] <= time - 2) {
					smell[i][j] = 0;
				}
			}
		}
	}

	private static void sharkMove() {
		sharkMovement = new int[3];
		Arrays.fill(sharkMovement, -1);
		maxEatableFishCount = -1;

		searchSharkRoute(shark.x, shark.y, 0, 0);

		eatFish();
	}

	private static void eatFish() {

		int x = shark.x;
		int y = shark.y;

		if (maxSharkMovement == null)
			return;
		for (int d : maxSharkMovement) {
			x += sx[d];
			y += sy[d];

			if (fishCount[x][y] != 0) {
				fishCount[x][y] = 0;
				smell[x][y] = time;
			}
		}

		shark.x = x;
		shark.y = y;
	}

	private static void searchSharkRoute(int x, int y, int count, int eatablefishCount) {
		if (count == 3) {
			if (maxEatableFishCount < eatablefishCount) {
				maxEatableFishCount = eatablefishCount;
				maxSharkMovement = sharkMovement.clone();
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + sx[i];
			int ny = y + sy[i];

			if (!isRange(nx, ny))
				continue;

			int temp = fishCount[nx][ny];
			fishCount[nx][ny] = 0;

			sharkMovement[count] = i;
			searchSharkRoute(nx, ny, count + 1, eatablefishCount + temp);
			fishCount[nx][ny] = temp;
		}
	}

	private static void fishMove() {
		int size = fish.size();

		while (size-- > 0) {

			Node f = fish.poll();

			int nd = f.d;
			int nx = f.x;
			int ny = f.y;

			fishCount[f.x][f.y]--;

			boolean flag = false;
			for (int i = 0; i < 8; i++) {
				nd = (8 + f.d - i) % 8;

				nx = f.x + dx[nd];
				ny = f.y + dy[nd];

				if (!isRange(nx, ny) || (shark.x == nx && shark.y == ny) || smell[nx][ny] != 0)
					continue;

				flag = true;
				break;
			}

			if (!flag) {
				nd = f.d;
				nx = f.x;
				ny = f.y;
			}

			fishCount[nx][ny]++;
			Node next = new Node(nx, ny, nd);
			fish.add(next);
		}
	}

	static boolean isRange(int x, int y) {
		return x > 0 && x <= N && y > 0 && y <= N;
	}

	static class Node {
		int x, y, d;

		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
