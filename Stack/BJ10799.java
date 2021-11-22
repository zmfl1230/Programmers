package Stack;

import java.io.*;

public class BJ10799 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String replacement = br.readLine();
        char RIGHT = '(';
        char LEFT = ')';

        int stickCnt = 0;
        int answer = 0;
        for(int index = 0; index < replacement.length(); index++) {
            char elm = replacement.charAt(index);

            if (elm == RIGHT) {
                // right 라면 이것이 새로이 시작되는 쇠막대기인지,
                // 혹은 레이저인지를 구분해라
                if(replacement.charAt(index+1) == LEFT) {
                    // 레이저의 영역
                    answer += stickCnt;
                    index++;
                }else {
                    // 새로운 막대기 하나 추가
                    stickCnt ++;
                }

            } else {
                // 반드시 쇠막대기의 종료를 의미함.
                stickCnt --;
                answer++;
            }

        }

        System.out.println(answer);
        
    }
    
}
