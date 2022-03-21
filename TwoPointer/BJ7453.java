package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ7453 {

    static int size;
    static int[] A;
    static int[] B;
    static int[] C;
    static int[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        size = Integer.parseInt(br.readLine());

        A = new int[size];
        B = new int[size];
        C = new int[size];
        D = new int[size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
//
//        Arrays.sort(A);
//        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);

        System.out.println(solution());
    }

    public static int solution() {
        int answer = 0;
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                answer += doPointer(A[i] + B[j]);
            }
        }

        return answer;
    }

    public static int doPointer(int value) {
        int totalCase = 0;

        int pointerC = 0;
        int pointerD = size-1;

        while (pointerC < size && pointerD > 0) {
            int partialSum = C[pointerC] + D[pointerD];
            if(partialSum == value) {
                totalCase++;
            }

            if(partialSum < value) {
                if (C[pointerC] <= D[pointerD]) pointerC++;
                else pointerD--;
            } else {
                if (C[pointerC] <= D[pointerD]) pointerD--;
                else pointerC--;
            }
        }



        if(pointerC == size-1) {
            pointerD++;
        }
        if(pointerD == size-1) {
            pointerC++;
        }


        return totalCase;
    }
}
