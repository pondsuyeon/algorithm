package algorithm.M10.D03;

import java.io.*;
import java.util.*;

public class Main_BOJ_14719_빗물 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] heights = new int[W + 2];

		int[] leftMaxHeight = new int[W + 2];
		int[] rightMaxHeight = new int[W + 2];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= W; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= W; i++) {
			leftMaxHeight[i] = Math.max(leftMaxHeight[i - 1], heights[i - 1]);
		}

		for (int i = W - 1; i >= 1; i--) {
			rightMaxHeight[i] = Math.max(rightMaxHeight[i + 1], heights[i + 1]);
		}

		int ans = 0;
		for (int i = 0; i < W; i++) {
			if (leftMaxHeight[i] > heights[i] && rightMaxHeight[i] > heights[i]) {
				ans += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - heights[i];
			}
		}
		System.out.println(ans);
	}

}
