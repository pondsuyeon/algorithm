import java.io.*;
import java.util.*;

public class Main {
    static int N, M, fuel;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] distance;
    static Place[][] destination;
    static boolean[][] isCustomer;
    static Place taxi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        distance = new int[N + 1][N + 1];
        destination = new Place[N + 1][N + 1];
        isCustomer = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Place(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int fc = Integer.parseInt(st.nextToken());
            int tr = Integer.parseInt(st.nextToken());
            int tc = Integer.parseInt(st.nextToken());

            isCustomer[fr][fc] = true;
            destination[fr][fc] = new Place(tr, tc);
        }

        int checkCustomerCount = 0;
        while (true) {
            Place customer = findTaxiCustomer();
            if (customer == null) {
                if (checkCustomerCount != M) fuel = -1;
                break;
            }
            checkCustomerCount++;

            int distanceToCustomer = distance[customer.r][customer.c];
            if (distanceToCustomer == -1 || fuel < distanceToCustomer) {
                fuel = -1;
                break;
            }

            fuel -= distanceToCustomer;
            isCustomer[customer.r][customer.c] = false;
            taxi = customer;

            int distanceToDestination = getDistanceToDestination(destination[customer.r][customer.c]);

            if (distanceToDestination == -1 || fuel < distanceToDestination) {
                fuel = -1;
                break;
            }

            fuel += distanceToDestination;
            taxi = destination[customer.r][customer.c];
        }
        System.out.println(fuel);
    }

    static Place findTaxiCustomer() {
        if (isCustomer[taxi.r][taxi.c]) {
            distance[taxi.r][taxi.c] = 0;
            return taxi;
        }

        Queue<Place> q = new LinkedList<>();
        Queue<Place> temp = new LinkedList<>();

        for (int i = 0; i <= N; i++) {
            Arrays.fill(distance[i], -1);
        }
        q.offer(taxi);
        distance[taxi.r][taxi.c] = 0;

        while (!q.isEmpty()) {
            Place p = q.poll();

            for (int i = 0; i < 4; i++) {
                int tr = p.r + dr[i];
                int tc = p.c + dc[i];

                if (tr > 0 && tr <= N && tc > 0 && tc <= N && map[tr][tc] == 0 && distance[tr][tc] == -1) {
                    distance[tr][tc] = distance[p.r][p.c] + 1;

                    Place t = new Place(tr, tc);

                    if (isCustomer[tr][tc]) {
                        temp.offer(t);
                    }

                    q.offer(t);
                }
            }
        }
        if (temp.isEmpty()) return null;

        Place min = temp.poll();
        while (!temp.isEmpty()) {
            Place t = temp.poll();

            if (distance[min.r][min.c] > distance[t.r][t.c]) {
                min = t;
            } else if (distance[min.r][min.c] == distance[t.r][t.c]) {
                if (min.r > t.r) {
                    min = t;
                } else if (min.r == t.r) {
                    if (min.c > t.c) min = t;
                }
            }
        }
        return min;
    }

    static int getDistanceToDestination(Place destination) {

        Queue<Place> q = new LinkedList<>();
        for (int i = 0; i <= N; i++) {
            Arrays.fill(distance[i], -1);
        }
        q.offer(taxi);
        distance[taxi.r][taxi.c] = 0;

        while (!q.isEmpty()) {
            Place p = q.poll();

            for (int i = 0; i < 4; i++) {
                int tr = p.r + dr[i];
                int tc = p.c + dc[i];

                if (tr > 0 && tr <= N && tc > 0 && tc <= N && map[tr][tc] == 0 && distance[tr][tc] == -1) {
                    distance[tr][tc] = distance[p.r][p.c] + 1;

                    Place t = new Place(tr, tc);

                    if (tr == destination.r && tc == destination.c) return distance[tr][tc];

                    q.offer(t);
                }
            }
        }
        return -1;
    }

    static class Place {
        int r;
        int c;

        public Place(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}