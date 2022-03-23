package Backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18429 {
    static int N;
    static int K;
    static int[] weight;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weight = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(backtrack(0, 500));
    }

    public static int backtrack(int days, int totalWeight) {
        if(days == N) return 1;
        int caseCount = 0;
        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            if(totalWeight+weight[i] - (days+1) * K < 500) continue;

            visited[i] = true;
            caseCount += backtrack(days+1, totalWeight+weight[i]);
            visited[i] = false;
        }

        return caseCount;
    }
}
