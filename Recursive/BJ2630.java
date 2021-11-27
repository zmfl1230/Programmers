package Recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2630 {
    static int[][] paperGrid;
    static int red = 0;
    static int blue = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] answer = new int[] {0, 0};
        int N = Integer.parseInt(br.readLine());
        paperGrid = new int[N][N];
        
        for(int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N; col++) {
                paperGrid[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        findOrDivide(0, N-1, 0, N-1, answer);

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    
    }

    public static void findOrDivide(int startRow, int endRow, int startCol, int endCol, int[] paperCnt) {
        if (startRow == endRow && startCol == endCol) {
            paperCnt[paperGrid[startRow][startCol]] ++;
            return;
        }
        boolean isSame = true;
        int base = paperGrid[startRow][startCol];
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                if (paperGrid[row][col] != base) {
                    isSame = false;
                    break;
                }
            }
        }

        if (isSame) {
            paperCnt[paperGrid[startRow][startCol]] += 1;
            return;
        }

        int rowMid = (startRow + endRow) / 2;
        int colMid = (startCol + endCol) / 2;

        findOrDivide(startRow, rowMid, startCol, colMid, paperCnt);
        findOrDivide(startRow, rowMid, colMid+1, endCol, paperCnt);
        findOrDivide(rowMid+1, endRow, startCol, colMid, paperCnt);
        findOrDivide(rowMid+1, endRow, colMid+1, endCol, paperCnt);
    }
}