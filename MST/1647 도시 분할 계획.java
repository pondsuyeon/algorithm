import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    static int N, M;
    static PriorityQueue<Edge> edges = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;                     // 초기 트리의 부모 값 자기 자신 설정
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        System.out.println(roadPartition());
    }

    static int roadPartition() {

        int result = 0;                         // 가중치 합산 값
        int last = 0;                           // 마지막에 더한 가중치 값
        int count = 0;                          // 합산된 간선의 개수 카운트
        while (!edges.isEmpty()) {
            if (count == N - 1) break;
            Edge e = edges.poll();

            if (union(e.from, e.to)) {
                result += e.weight;
                last = e.weight;
                count++;
            }
        }
        result -= last;                         // 마지막에 합산된 가중치를 제거하여 가장 적은 유지비의 합 계산
        return result;
    }

    static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}