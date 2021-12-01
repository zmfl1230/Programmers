package Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2579 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] scores = new int[N+1];
        int[] notConsecutiveMaxScore = new int[N+1];
        int[] consecutiveMaxScore = new int[N+1];

        for (int index = 1; index <= N; index++) {
            scores[index] = Integer.parseInt(br.readLine());
        }

        notConsecutiveMaxScore[N] = scores[N];
        consecutiveMaxScore[N] = scores[N];

        for(int index = N; index > 1; index--) {

            // 불연속
            int front = scores[index-1] + notConsecutiveMaxScore[index];
            if(consecutiveMaxScore[index-1] < front) consecutiveMaxScore[index-1] = front;
        
            int prevFront = scores[index-2] + notConsecutiveMaxScore[index];
            if(notConsecutiveMaxScore[index-2] < prevFront) notConsecutiveMaxScore[index-2] = prevFront;


            // 연속
            prevFront = scores[index-2] + consecutiveMaxScore[index];
            if(notConsecutiveMaxScore[index-2] < prevFront) notConsecutiveMaxScore[index-2] = prevFront;

        }

        System.out.println(Math.max(Math.max(notConsecutiveMaxScore[0], notConsecutiveMaxScore[1]), consecutiveMaxScore[1]));


    }
}
