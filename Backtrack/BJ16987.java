package Backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ16987 {
    public static void main(String[] args)  throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int egg = Integer.parseInt(br.readLine());
        int[][] eggs = new int[egg][2];
        
        for(int i = 0; i < egg; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());    
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0, 0, eggs));

    
    
    }
    public static int dfs(int idx, int removed, int[][] eggs) {

        // 조사하려는 인덱스가 가장 오른쪽에 있는 인덱스면 현대 제거된 계란 수 반환
        if (idx == eggs.length) {
            return removed;
        }

        int max = 0;
        Boolean flag = false;

        // 현 계란 수가 0보다 작으면, 이미 깨진 상태이므로 그 다음 계란에게 순서를 넘김
        if (eggs[idx][0] <= 0) return Math.max(max, dfs(idx+1, removed, eggs));

        // 칠 계란 탐색
        for (int i = 0; i < eggs.length; i++) {
            int[] egg = eggs[i];

            if (i == idx) continue;
            if (egg[0] <= 0) continue;


            eggs[idx][0] -= egg[1];
            egg[0] -= eggs[idx][1];

            flag = true;

            // 다음 인덱스로 현재 깨진 수만큼 더해준 뒤, 넘김.
            max = Math.max(max, dfs(idx+1, removed + (eggs[idx][0] <= 0 ? 1 : 0) + (egg[0] <= 0 ? 1 : 0), eggs));
            
            eggs[idx][0] += egg[1];
            egg[0] += eggs[idx][1];

            
        }

        // 만약 조사시에 남은 계란이 없는 경우, 마지막 인덱스로 간주.
        if(!flag) return removed;

        return max;
        
    }
}
