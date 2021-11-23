package Dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class BJ1010 {
    static int[][] memoization;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testcase = Integer.parseInt(br.readLine());

        while(testcase-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            System.out.println(solution(N, M));;
        }

    }

    public static int solution(int n, int m){
        memoization = new int[n+1][m+1];

        return dfs(0, 0, n, m);

    }

    public static int dfs(int cnt, int start, int n, int m) {
        if(cnt == n) return 1;

        int ret = 0;
        for (int index = start; index < m; index++) {
            // 앞으로 남은 서쪽 사이트가 남은 동쪽 사이트보다 많을때, 연결이 불가능함으로 해당 경우는 pass
            if(m-(index + 1) < n - (cnt + 1)) continue;

            if(memoization[cnt+1][index+1] != 0) {
                ret += memoization[cnt+1][index+1];
                continue;
            }

            ret += dfs(cnt+1, index+1, n, m);

        }

        memoization[cnt][start] = ret;
        return ret;
    }
}
