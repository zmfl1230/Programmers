package Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arrays = new int[N];
        int[] MaxValues = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrays[i] = Integer.parseInt(st.nextToken());
        }

        MaxValues[0] = arrays[0];
        int answer = MaxValues[0];
        for (int i = 1; i < N; i++) {
            MaxValues[i] = Math.max(arrays[i], MaxValues[i-1] + arrays[i]);
            answer = Math.max(answer, MaxValues[i]);
        }

        System.out.println(answer);

    }
}
