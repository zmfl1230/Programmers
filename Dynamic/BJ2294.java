package Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ2294 {

    static int N;
    static int K;

    static Integer[] coins;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new Integer[N];
        count = new int[K+1];

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(coins, Collections.reverseOrder());
        Arrays.fill(count, Integer.MAX_VALUE);
        fillCount();

        if(count[K] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(count[K]);

    }

    public static void fillCount() {
        for (int coin : coins) {
            int cnt = 1;
            while (coin * cnt <= K) {
                count[coin * cnt] = (cnt != 1) ? Math.min(count[coin * cnt], count[coin*(cnt-1)] + 1) : cnt;
                if(count[K-coin*cnt] != Integer.MAX_VALUE) count[K] = Math.min(count[K], count[coin * cnt] + count[K - coin * cnt]);
                cnt++;
            }
        }
    }

}
