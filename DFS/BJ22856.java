package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ22856 {
    static int Node;
    static int level;


    static int[] nodes;
    static int[][] indexOfNodes;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Node = Integer.parseInt(br.readLine());
        nodes = new int[4*(Node+1)];
        indexOfNodes = new int[Node+1][2];
        visited = new boolean[Node+1];

        int cnt = 0;
        nodes[1] = 1;
        indexOfNodes[1][0] = 1;
        indexOfNodes[1][1] = 1;
        while (Node > cnt++) {
            st = new StringTokenizer(br.readLine());
            int[] val = indexOfNodes[Integer.parseInt(st.nextToken())];
            int index = val[0];
            nodes[2*index] = Integer.parseInt(st.nextToken());
            nodes[2*index+1] = Integer.parseInt(st.nextToken());

            if(nodes[2*index] != -1 || nodes[2*index+1] != -1) {
                level = Math.max(level, val[1]+1);
            }

            if(nodes[2*index] != -1) {
                indexOfNodes[nodes[2 * index]][0] = 2 * index;
                indexOfNodes[nodes[2 * index]][1] = val[1]+1;
            }
            if(nodes[2*index+1] != -1) {
                indexOfNodes[nodes[2 * index + 1]][0] = 2 * index + 1;
                indexOfNodes[nodes[2 * index + 1]][1] = val[1]+1;
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int answer = 0;
        List<Integer> stack = new ArrayList<>();

        stack.add(1);
        visited[1] = true;

        while (!stack.isEmpty()) {
            int node = stack.remove(stack.size() - 1);

            int nextNode = getNextNode(node);
            if(nextNode == -1) continue;


            answer++;
            System.out.println(nextNode);
            if(!visited[nextNode]) {
                visited[nextNode] = true;
            }

            stack.add(nextNode);
        }

        return answer;
    }
    // 내려가려면, 방문 이력 체크
    // 올라가는건 상관 No
    public static int getNextNode(int node) {
        int index = indexOfNodes[node][0];

        if((nodes[2*index] != 0 && nodes[2*index] != -1) && !visited[nodes[2*index]]) return nodes[2*index];
        if((nodes[2*index+1] != 0 && nodes[2*index+1] != -1) && !visited[nodes[2*index+1]]) return nodes[2*index+1];

        if(index > 1 && index < Math.pow(2, level) - 1) return nodes[index/2];

        return -1;

    }
}
