import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        MaxHeap heap = new MaxHeap(N);

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0)
                sb.append(heap.delete()).append("\n");
            else
                heap.insert(input);
        }
        System.out.print(sb);
    }

    static class MaxHeap {
        int[] tree;
        int index = 0;

        MaxHeap(int N) {
            this.tree = new int[N + 1];
        }

        public void insert(int x) {
            tree[++index] = x;
            int i = index;
            while (i / 2 >= 1) {
                if (tree[i / 2] < tree[i]) {
                    swap(i / 2, i);
                    i /= 2;
                } else break;
            }
        }

        public int delete() {
            if (index == 0)
                return 0;
            int max = tree[1];
            tree[1] = tree[index];
            tree[index--] = 0;
            int i = 1;
            while (i * 2 <= index) {
                if (tree[i] > tree[i * 2] && tree[i] > tree[i * 2 + 1]) break;
                else if (tree[i * 2] > tree[i * 2 + 1]) {
                    swap(i * 2, i);
                    i = i * 2;
                } else {
                    swap(i * 2 + 1, i);
                    i = i * 2 + 1;
                }
            }
            return max;
        }

        public void swap(int a, int b) {
            int temp = tree[a];
            tree[a] = tree[b];
            tree[b] = temp;
        }
    }
}