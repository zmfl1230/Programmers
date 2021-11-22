package Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class BJ9012 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            String ParenthesisString = br.readLine();
            System.out.println(checkVPS(ParenthesisString));
        }

    }

    public static String checkVPS(String pString){
        char RIGHT = '(';

        ArrayList<Character> ps = new ArrayList<>();
        for(int index = 0; index < pString.length(); index++) {
            char c = pString.charAt(index);
            if (c == RIGHT) {ps.add(c); continue;}
            
            if (ps.isEmpty()) return "NO";
            else ps.remove(ps.size()-1);
        }

        if (ps.size() > 0) return "NO";
        return "YES";
    }
}
