package DFS;

import java.util.ArrayList;

public class TargetPath {
    ArrayList<Integer> items; 
    Boolean[] visited;
    public static void main(String[] args) {
        int answer = solution(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(answer);
        // solution(arguments, 3);
    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;

        answer = dfs(0, 0, numbers, target);

        return answer;
    }

    public static int dfs(int cnt, int val, int[] numbers, int target){
        /**
         * cnt: 현재까지 진행된 인덱스 
         * val: 현재까지 계산된 값
         * numbers: 계산할 값
         */

        if (cnt == numbers.length){
             if (val == target) return 1;
             return 0;
        }

        int ret = 0;

        ret += dfs(cnt + 1, val + numbers[cnt], numbers, target);
        ret += dfs(cnt + 1, val - numbers[cnt], numbers, target);

        return ret;
    }

}