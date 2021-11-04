package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ11725 {
    static int N;
    static int[] parents;
    static HashMap<Integer, HashSet<Integer>> relation;
    // static Boolean[] visited;

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parents = new int[N+1];
        relation = new HashMap<>();

        for(int i = 0; i < N-1; i++) {

            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if (!relation.containsKey(key)) relation.put(key, new HashSet<>());
            if (!relation.containsKey(value)) relation.put(value, new HashSet<>());

            relation.get(key).add(value);
            relation.get(value).add(key);

        }

        dfs(1);

        for(int elm: parents) {
            if (elm == 0) continue;
            System.out.println(elm);
        }

    }

    static void dfs(int node) {

        for (int elm : relation.get(node)) {
            if (parents[elm] != 0 || elm == 1) continue;

            parents[elm] = node;
            dfs(elm);
        }
    }
}
