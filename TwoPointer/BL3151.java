package TwoPointer;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.*;

public class BL3151 {
    public static void main(String args[]) throws IOException{
        /**
         * 세 포인터는 항상 하나의 포인터를 고정으로 하고, 나머지 두개의 포인터를 움직여야 함을 명심해야 한다.
         * 이렇게 푼다면, n^2만에 해결할 수 있다.
         * 두개를 포인터로 두고 하나를 움직이려고 할 때, 결국에는 n^3으로 구현해야 한다.
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] codeSkills = new int[N];


        String codeSkillsString = br.readLine();
        st = new StringTokenizer(codeSkillsString);

        for (int index = 0; index < N; index++) {
            codeSkills[index] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(codeSkills);
        long answer = 0;

        for(int pointer = 0; pointer < N-2; pointer++) {
            int base = codeSkills[pointer];

            if(base > 0) break;

            int start = pointer + 1;
            int end = N-1;

            while(start < end) {

                int partialSum = base + codeSkills[start] + codeSkills[end];
                if(partialSum > 0) {
                    end--;
                    continue;
                }

                if(partialSum < 0) {
                    start++;
                    continue;
                }

                // partialSum 이 0인 경우만 남게된다.
                /**
                 * 함이 0인 경우에서 발생할 수 있는 경우
                 * 1. start 가리키는 값 == end 가리키는 값
                 * 정렬된 상태에서 시작 값과 끝값이 같다는 것의 의미는 해당 조합들만 계산하면, 조사할 값은 끝났다는 것을 의미한다.
                 * 고로 2 2 2 2 에 상태에서 start 값이 첫번쨰 2를 가리키고, end 값이 마지막 2를 가리킬 때, 서로 조합을 이룰 수 있는 수는
                 * (if n == 전체 개수) (n-1) * n / 2 개이다.
                 * 
                 * 2. start 가리키는 값 != end 가리키는 값
                 *  2-1. start 값을 시작으로 연속적으로 같은 값을 가질 가능성
                 *  2.2. end 값을 마지막으로 연속적으로 같은 값을 가질 가능성
                 *  2.3. 2.1과 2.2가 동시에 일어날 가능성
                 * 이를 한번에 해결해 주기 위한 방법은 start와 end 값을 기준으로 값을 증가시키거나, 값을 감소시키면서 연속적으로 같은 값이 나올때까지 조사하는 방법이다.
                 * 결국, start를 기준으로 연속적으로 같은 값이 나온 개수 * end 값을 마지막으로 이전까지 계속해서 같은 값이 나온 개수
                 * 를 한 것이 최종적으로 해당 값들의 모든 조합을 카운트 할 수 있도록 한다.
                 */

                if(codeSkills[start] == codeSkills[end]) {
                    answer += ((end - start) * (end - start + 1) / 2);
                    break;
                }

                int startVal = codeSkills[start];
                int startCnt = 0; 
                while (startVal == codeSkills[start]) { start++; startCnt ++; }

                int endVal = codeSkills[end];
                int endCnt = 0;
                while(endVal == codeSkills[end]) { end--; endCnt++; }

                answer += startCnt * endCnt;
    
            }
        }
        System.out.println(answer);


    }
    
}
