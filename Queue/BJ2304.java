package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2304 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<int[]> pq; 
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((a, b) -> b[1]- a[1]);

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int startIdx = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            pq.add(new int[] {startIdx, height});

        }

        int[] maxHeight = pq.poll();
        int total = maxHeight[1];

        int startIdx = maxHeight[0];
        int endIdx = maxHeight[0]+1;
        while(!pq.isEmpty()) {
            int[] newPillar = pq.poll();

            // 이미 범위안에 포함되어 있는 기둥
            if(startIdx < newPillar[0] && newPillar[0] < endIdx) continue;

            if(endIdx < newPillar[0]){
                total += (newPillar[0] + 1 - endIdx) * newPillar[1];
                endIdx = newPillar[0] + 1;
                continue;
            }

            if(newPillar[0] < startIdx) {
                total += (startIdx - newPillar[0]) * newPillar[1];
                startIdx = newPillar[0];
                continue;
            }
        }

        System.out.println(total);

    }
}