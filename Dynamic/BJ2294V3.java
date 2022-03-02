package Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2294V3 {

    static int N;
    static int K;

    static int[] coins;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];
        count = new int[K+1];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(coins);
        Arrays.fill(count, -1);
        fillCount();

        System.out.println(count[K]);

    }

    public static void fillCount() {
        for (int i = 1; i <= K; i++) {
            for (int coin : coins) {
                if(coin == i) count[i] = 1;
                if(i - coin < 0 || count[i-coin] == -1) continue;
                if(count[i] == -1) count[i] = count[i-coin] + 1;
                else count[i] = Math.min(count[i], count[i-coin] + 1);
            }
        }
    }

}
