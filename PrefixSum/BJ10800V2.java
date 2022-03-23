package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ10800V2 {
    static final int INDEX = 0;
    static final int COLOR = 1;
    static final int SIZE = 2;
    static int size;
    //전체 누적액
    static int prefixSum = 0;

    static int[][] balls;
    static int[] prefixSumByColor;
    // 전체 누적액에서 같은 색 공의 누적액 제거
    static int[] answer;
    // 어떤 크기가 몇번 누적됐는지 추적
    static int[] countBySize;
    // 어떤 색의 어떤 크기의 공이 몇번 누적됐는지를 추적
    static int[][] sameSizeAndColorCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        size = Integer.parseInt(br.readLine());
        // 3: idx, color, size
        balls = new int[size][3];
        answer = new int[size];
        prefixSumByColor = new int[size+1];
        countBySize = new int[2001];
        sameSizeAndColorCount = new int[size+1][2001];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            balls[i] = new int[] {i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(balls, Comparator.comparingInt((int[] v) -> v[SIZE]));

        for (int[] ball : balls) {
            // balls를 조사하면서 본인과 같은 크기의 공이 앞에 있었으면, 그 값이 더해진 수만큼 제거 (Size)
            // 전체 누적액 - 본인과 같은 크기 공들의 누적액 - 본인과 같은 컬러 공들의 누적액 + 본인과 같은 컬러고 같은 크기인 공들의 누적액(중복 소거 제거)
            answer[ball[INDEX]] = prefixSum - prefixSumByColor[ball[COLOR]] - countBySize[ball[SIZE]] + sameSizeAndColorCount[ball[COLOR]][ball[SIZE]];

            prefixSum += ball[SIZE];
            prefixSumByColor[ball[COLOR]] += ball[SIZE];
            countBySize[ball[SIZE]]++;
            sameSizeAndColorCount[ball[COLOR]][ball[SIZE]]++;

        }

        for (int ans : answer) {
            System.out.println(ans);
        }
    }


}
