package Dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.*;

public class BJ1463 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        LinkedList<int[]> numbers = new LinkedList<>();
        numbers.add(new int[] {1, 0});


        
        while(!numbers.isEmpty()) {
            int[] curVal = numbers.poll();

            if (curVal[0] == N) {
                System.out.println(dp[curVal[0]]);
                break;
            }

            if (curVal[0] * 3 <= N && dp[curVal[0] * 3] == 0) {
                numbers.add(new int[] {curVal[0] * 3, curVal[1]+1});
                dp[curVal[0] * 3] = curVal[1]+1;
            }
            if (curVal[0] * 2 <= N && dp[curVal[0] * 2] == 0) {
                numbers.add(new int[] {curVal[0] * 2, curVal[1]+1});
                dp[curVal[0] * 2] = curVal[1]+1;
            }
            if (curVal[0] + 1 <= N && dp[curVal[0] + 1] == 0) {
                numbers.add(new int[] {curVal[0] + 1, curVal[1]+1});
                dp[curVal[0] + 1] = curVal[1]+1;
            }



        }


    }

    
}
