package BFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class Node {
    int row;
    int col;

    public Node(int row, int col){
        this.row = row;
        this.col = col;
    }

}
/**
 * 한번 틀린 이유: row, col의 깂을 기준으로 int형트로 해당 자리의 위치를 지정하는 부분이 있는데 
 * 이때 colSize * row + col 로 위치 값을 지정해줘야 하는 것을
 * rowSize * row + col로 지정해줬다. 지는 row == col 인 경우만 가능해야 하는데, 이를 생각하지 못하고 사용했다.
 * 
 * - 발견 과정
 * 특정 테케에서 선택된 수열이 1, 6, 12였는데 12번이 2인 바이러스가 있는 자리라 절대로 선택불가능한데 선택되서 이 오류를 알 수 있었다.
 * 
 */

public class Laboratory {

    static int[][] maps;
    static Boolean[] visited;
    static int rowSize;
    static int colSize;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static final int VIRUS = 2;
    static final int BLOCK = 1;
    static final int EMPTY = 0;
    static final int MAX = 3;

    static ArrayList<Node> virus = new ArrayList<>();
    static ArrayList<Integer> available = new ArrayList<>();

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());


        maps = new int[rowSize][colSize];
        

        // graph 입력 받기
        for (int row = 0 ; row < rowSize; row ++){
            st = new StringTokenizer(br.readLine());

            for(int col = 0; col < colSize; col ++ ){
                // if(st.hasMoreTokens()){
                    int curVal = Integer.parseInt(st.nextToken());

                    if (curVal == VIRUS) virus.add(new Node(row, col));
                    else if(curVal == EMPTY) available.add(colSize * row + col);

                    maps[row][col] = curVal;
                // }
            }

            // visited 초기화

        }

        //  조합을 위한 backtrack
        visited = new Boolean[available.size()];
        Arrays.fill(visited, false);

        backtrack(0, new int[] {0, 0, 0});

        System.out.println(answer);

        
    }

    public static void backtrack(int cnt, int[] selected){

        if (cnt == MAX){
            answer = Math.max(answer, spreadVirus(selected));
            return;
        }


        for (int idx = 0; idx < available.size(); idx++) {
            if (visited[idx]) continue;

            selected[cnt] = available.get(idx);
            visited[idx] = true;

            backtrack(cnt+1, selected);

            visited[idx] = false;

        }

    }

    public static int spreadVirus(int[] selected){
        int beforeSpread = available.size();
        Boolean[][] virusVisited = new Boolean[rowSize][colSize];
        
        for (int idx = 0; idx < rowSize; idx++) {
            Arrays.fill(virusVisited[idx], false);
        }
        // 바이러스에 담긴 바

        int spreadPlaceNum = 0;

        for (Node position: virus) {
            Queue<Node> spread = new LinkedList<>();
            spread.add(position);
            virusVisited[position.row][position.col] = true;

            while(!spread.isEmpty()) {
                Node curNode = spread.poll();

                for (int i =0; i < 4; i ++){
                    int newRow = curNode.row + dr[i];
                    int newCol = curNode.col + dc[i];
    
                    if (newRow < 0 || newRow > rowSize - 1 || newCol < 0 || newCol > colSize - 1) continue;
                    if (maps[newRow][newCol] != EMPTY) continue;
                    
                    // int 배열 안에 해당 수가 포함되어 있는지 확인 코드
                    if (IntStream.of(selected).anyMatch(x -> x == newRow*colSize + newCol)) continue;

                    if (virusVisited[newRow][newCol]) continue;
    
                    spread.add(new Node(newRow, newCol));
                    // System.out.printf("추가: %d %d", newRow, newCol);
                    virusVisited[newRow][newCol] = true;
                    spreadPlaceNum += 1;
    
                    }
                }
            }

            return beforeSpread - spreadPlaceNum - 3;

        }



    }

