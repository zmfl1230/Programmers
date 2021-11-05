package String;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ20437 {
 public static void main(String[] args)  throws IOException{


    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int testcase = Integer.parseInt(br.readLine());
    
    while(testcase > 0) {
        String str = br.readLine();
        int K = Integer.parseInt(br.readLine());
        solution(K, str);

        testcase -= 1;
    }    

 }
 
 public static void solution (int K, String str) {
    HashMap<Character, ArrayList<Integer>> stringMap = new HashMap<>();
    ArrayList<Character> check =  new ArrayList<>();

    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);

        if (!stringMap.containsKey(c)) stringMap.put(c, new ArrayList<>());
        ArrayList<Integer> val = stringMap.get(c);
        val.add(i);

        if (val.size() >= K) check.add(c);
    }

    if (check.isEmpty()) {System.out.println(-1); return;}

    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    // 각각의 string 인덱스들을 sliding window로 돌면서 인덱스 차의 최대값과 최소값을 저장하고, 이를 반환한다.

    for(char c : check) {
        ArrayList<Integer> curList = stringMap.get(c);

        int start = 0;
        int end = K-1;


        max = Math.max(max, curList.get(end) - curList.get(start) + 1);
        min = Math.min(min, curList.get(end) - curList.get(start) + 1);

        while (end < curList.size()){

            if (end + 1 == curList.size()) break;

            start += 1;
            end += 1;

            max = Math.max(max, curList.get(end) - curList.get(start) + 1);
            min = Math.min(min, curList.get(end) - curList.get(start) + 1);

        }

    }

    System.out.println(min + " " + max);
 }
}
