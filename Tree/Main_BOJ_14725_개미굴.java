import java.io.*;
import java.util.*;

public class Main_BOJ_14725_개미굴 {

    static int N;
    static HashMap<String, HashMap> tree = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            HashMap<String, HashMap> now = tree;

            for (int j = 0; j < K; j++) {
                String feed = st.nextToken();

                if (!now.containsKey(feed)) {
                    now.put(feed, new HashMap<String, HashMap>());
                }
                now = now.get(feed);
            }
        }

        printStructure("", tree);
        System.out.print(sb.toString());
    }

    private static void printStructure(String layer, HashMap<String, HashMap> node) {
        if (node.size() == 0) return;

        String[] array = node.keySet().toArray(new String[node.size()]);
        Arrays.sort(array);

        for (String key : array) {
            sb.append(layer + key + "\n");
            printStructure(layer + "--", node.get(key));
        }
    }
}
