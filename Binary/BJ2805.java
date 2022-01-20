package Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2805 {

  static int N;
  static int M;
  static int[] height;
  static int target;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    height = new int[N];
    target = 0;

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      height[i] = Integer.parseInt(st.nextToken());
      target = Math.max(target, height[i]);
    }

    System.out.println(solution());

  }

  public static int solution() {

    int start = 0;
    int end = target;

    while (start <= end) {
      int mid = (start + end) / 2;

      long returnValue = returnRestHeight(mid);

      if (returnValue < M)
        end = mid - 1;
      else
        start = mid + 1;
    }

    return start - 1;

  }

  public static long returnRestHeight(int targetHeight) {
    long returnHeight = 0;

    for (int i = 0; i < N; i++) {
      if (height[i] - targetHeight > 0)
        returnHeight += height[i] - targetHeight;
    }

    return returnHeight;
  }
}
