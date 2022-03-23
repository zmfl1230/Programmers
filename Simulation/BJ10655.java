package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10655 {
    static final int X = 0;
    static final int Y = 1;

    static int N;
    static int[][] checkPoint;
    static int[] memoization;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        checkPoint = new int[N][2];
        memoization = new int[N];


        int totalDistance = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            checkPoint[i][X] = Integer.parseInt(st.nextToken());
            checkPoint[i][Y] = Integer.parseInt(st.nextToken());

            if(i == 0) continue;
            memoization[i] = Math.abs(checkPoint[i-1][X] - checkPoint[i][X]) + Math.abs(checkPoint[i-1][Y] - checkPoint[i][Y]);
            totalDistance += memoization[i];

        }

        int minDistance = totalDistance;
        // 제거할 체크포인트 (맨 앞과 맨 뒤 제거)
        for (int i = 1; i < N-1; i++) {
            int removing = memoization[i] + memoization[i+1];
            int adding = Math.abs(checkPoint[i-1][X] - checkPoint[i+1][X]) + Math.abs(checkPoint[i-1][Y] - checkPoint[i+1][Y]);
            minDistance = Math.min(minDistance, totalDistance - removing + adding);
        }

        System.out.println(minDistance);

    }
}
