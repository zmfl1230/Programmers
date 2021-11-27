package Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2688 {
    static long[][] dp = new long[65][10];
    static long[] total = new long[65];

    static int pointer = 2;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(br.readLine());

        // 한자리는 무조건 1개, 총 10개
        Arrays.fill(dp[1], 1);
        total[1] = 10;

        while(testcase-- > 0) {
            int n = Integer.parseInt(br.readLine());

            if (total[n] != 0) {
                sb.append(total[n]).append('\n');
                continue;
            }
            sb.append(getCorrectNumberCnt(n)).append('\n');
        
        }
        System.out.println(sb);

    }

    public static long getCorrectNumberCnt(int until) {

        long totalSum = 0;
        for(int index = pointer; index <= until; index++) {
            
            for (int lastNum = 0; lastNum < 10; lastNum++) {

                long partial = 0;
                for (int inner = 0; inner <= lastNum; inner++) {
                    partial += dp[index-1][inner];
                }

                dp[index][lastNum] = partial;
                totalSum += partial; 

            }
            total[index] = totalSum;
        }

        pointer = until+1;
        return totalSum;
    }
    
}
