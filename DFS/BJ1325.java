package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1325 {
    static int V;
    static int E;
    static long[] childCnt;
    static Boolean[] visited;
    static HashMap<Integer, HashSet<Integer>> relation;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        relation = new HashMap<>();
        childCnt = new long[V+1];
        visited = new Boolean[V+1];
        
        for(int i = 0; i < E; i++) {

            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if (!relation.containsKey(value)) relation.put(value, new HashSet<>());

            relation.get(value).add(key);

        }
        
        long max = 0;
        for (int v = 1; v <= V; v++) {
            if (childCnt[v] != 0) continue;
            visited = new Boolean[V+1];
            visited[v] = true;

            max = Math.max(max, findChild(v));
        }

        for (int v = 1; v <= V; v++) {
            if (childCnt[v] == max) System.out.printf("%d ", v);
        }
    }

    public static long findChild (int vertex) {
        childCnt[vertex] = 1;

        // 자식이 없는 경우
        if (!relation.containsKey(vertex)) return 1;

        for (int elm : relation.get(vertex)) {
            if(visited[elm]) continue;
            
            visited[elm] = true;
            childCnt[vertex] += findChild(elm);
        }

        return childCnt[vertex];
    }

    
}
