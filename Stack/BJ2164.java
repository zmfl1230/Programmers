package Stack;

// bufferReader에 대해 공부하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2164 {
    public static void main(String[] args) throws IOException{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));

        // read와 readline에 대해 알기
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> cardList = new LinkedList<>();

        for(int card = 1; card <= N; card++) {
            cardList.add(card);
        }

        while(cardList.size() > 1){
            solve(cardList);
        }

        System.out.println(cardList.getFirst());
        
    }
    // 각 클래스 별, 메소드 정리 및 암기
    public static void solve(LinkedList<Integer> cardList) {

        cardList.removeFirst();

        int firstElm = cardList.get(0);
        cardList.addLast(firstElm);
        cardList.removeFirst();
        
    }
}
