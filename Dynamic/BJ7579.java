package Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ7579 {
    static int N;
    static int targetMemory;
    static int totalCost;
    static int[][] maxMemoryManager;
    static int[] memory;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        targetMemory = Integer.parseInt(st.nextToken());

        memory = new int[N+1];
        cost = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        totalCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        maxMemoryManager = new int[N+1][totalCost+1];
        System.out.println(solution());


    }
    public static int solution() {
        int minCost = Integer.MAX_VALUE;
        for (int app = 1; app <= N; app++) {
            for (int i = 0; i <= totalCost; i++) {
                // cost[app][i] = 현재 app에서 i 비용으로 만들 수 있는 최대 메모리
                // cost[app-1][i-cost[app]] + memory[app]
                if(i-cost[app] >= 0) maxMemoryManager[app][i] = Math.max(maxMemoryManager[app-1][i], maxMemoryManager[app-1][i-cost[app]] + memory[app]);
                else maxMemoryManager[app][i] = maxMemoryManager[app-1][i];

                if(maxMemoryManager[app][i] >= targetMemory) minCost = Math.min(minCost, i);
            }
        }
        return minCost;
    }
}
