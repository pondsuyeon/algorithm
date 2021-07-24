import java.io.*;
import java.util.*;

public class Main {
    static int INF = 1000000001;
    static int[] arr;
    static Tree tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;
            arr = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            tree = new Tree(arr);
            sb.append(tree.getMaxArea(1, n)).append("\n");
        }
        System.out.print(sb);
    }

    static class Tree {
        int[] arr;
        int[] tree;

        public Tree(int[] arr) {
            this.arr = arr;
            int len = arr.length;
            tree = new int[len * 4];
            for (int i = 0; i < len * 4; i++) {
                tree[i] = INF;
            }
            init(1, len - 1, 1);
        }

        public int init(int left, int right, int node) {
            if (left == right) {
                tree[node] = left;
                return tree[node];
            }
            int mid = (left + right) / 2;
            int leftMinIndex = init(left, mid, node * 2);
            int rightMinIndex = init(mid + 1, right, node * 2 + 1);
            if (arr[leftMinIndex] <= arr[rightMinIndex])
                tree[node] = leftMinIndex;
            else
                tree[node] = rightMinIndex;
            return tree[node];
        }

        public int query(int start, int end, int left, int right, int node) {
            // left, right: tree index
            // start, end: number range
            if (left > end || right < start) {
                return INF;
            } else if (left >= start && right <= end) {
                return tree[node];
            }
            int mid = (left + right) / 2;
            int leftMinIndex = query(start, end, left, mid, node * 2);
            int rightMinIndex = query(start, end, mid + 1, right, node * 2 + 1);

            if (leftMinIndex == INF) return rightMinIndex;
            if (rightMinIndex == INF) return leftMinIndex;
            if (arr[leftMinIndex] <= arr[rightMinIndex]) return leftMinIndex;
            else return rightMinIndex;
        }

        public long getMaxArea(int start, int end) {
            int N = arr.length - 1;

            int minIndex = query(start, end, 1, N, 1);
            long maxArea = (long) (end - start + 1) * arr[minIndex];

            if (start <= minIndex - 1) {
                long temp = getMaxArea(start, minIndex - 1);
                maxArea = Math.max(maxArea, temp);
            }
            if (minIndex + 1 <= end) {
                long temp = getMaxArea(minIndex + 1, end);
                maxArea = Math.max(maxArea, temp);
            }
            return maxArea;
        }
    }
}
