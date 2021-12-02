package TwoPointer;

import java.io.*;
import java.util.*;

public class BJ11728 {
    static int[] A;
    static int[] B;
    static int[] sorted;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            A[index] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int index = 0; index < M; index++) {
            B[index] = Integer.parseInt(st.nextToken());
        }

        int ap = 0;
        int bp = 0;

        int sp = 0;
        while (ap < N && bp < M) {
            if(A[ap] <= B[bp]) {
                sb.append(A[ap++]);
                sb.append(" ");

                continue;
            }

            if(A[ap] > B[bp]) {
                sb.append(B[bp++]);
                sb.append(" ");

                continue;
            }

        }

        if (ap == N) {
            addRest(bp, sp, B, sb);
        }
        else {
            addRest(ap, sp, A, sb);
        }

        System.out.println(sb);
    }

    public static void addRest(int startP, int sortedP, int[] target, StringBuilder sb) {

        for (int index = startP; index < target.length; index++) {
            sb.append(target[index]);
            sb.append(" ");
        }
    }
}
