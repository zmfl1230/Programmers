package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ21921 {
  static int N;
  static int X;
  static Integer[] visited;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());

    visited = new Integer[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      visited[i] = Integer.parseInt(st.nextToken());
    }
    solution();
  }

  public static void solution() {

    int start = 0;
    int end = X - 1;

    int partial = Arrays.asList(visited).subList(start, end + 1).stream().mapToInt(Integer::intValue).sum();

    int visitedMax = partial;
    int count = 1;

    while (end < N - 1) {
      partial = partial - visited[start++] + visited[++end];

      if (partial == visitedMax)
        count++;
      else if (partial > visitedMax) {
        visitedMax = partial;
        count = 1;
      }
    }

    if (visitedMax == 0) {
      System.out.println("SAD");
      return;
    }

    System.out.println(visitedMax);
    System.out.println(count);

  }

}