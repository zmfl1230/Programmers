package Priority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Number {
  int num;
  int order;

  public Number(int num, int order) {
    this.num = num;
    this.order = order;
  }

  @Override
  public boolean equals(Object obj) {
    Number compared = (Number) obj;
    return this.num == compared.num && this.order == compared.num;
  }

}

class HighPriority extends Number implements Comparable<Number> {

  HighPriority(int num, int order) {
    super(num, order);
  }

  @Override
  public int compareTo(Number o) {
    return o.num - num;
  }
}

class LowPriority extends Number implements Comparable<Number> {

  LowPriority(int num, int order) {
    super(num, order);
  }

  @Override
  public int compareTo(Number o) {
    return num - o.num;
  }
}

public class BJ7662 {
  static Set<Number> shared = new HashSet<>();
  static BufferedReader br;

  static final String INSERT = "I";
  static final String DELETE = "D";

  public static void main(String[] args) throws NumberFormatException, IOException {

    br = new BufferedReader(new InputStreamReader(System.in));
    int testcase = Integer.parseInt(br.readLine());
    while (testcase-- > 0) {
      solution();
    }

  }

  public static void solution() throws NumberFormatException, IOException {
    StringTokenizer st;

    Queue<Number> highPQueue = new PriorityQueue<>();
    Queue<Number> lowPQueue = new PriorityQueue<>();

    int k = Integer.parseInt(br.readLine());

    while (k-- > 0) {
      st = new StringTokenizer(br.readLine());

      String op = st.nextToken();
      int num = Integer.parseInt(st.nextToken());

      if (op.equals(INSERT)) {
        Number n = new HighPriority(num, k);
        highPQueue.add(new HighPriority(num, k));
        lowPQueue.add(new LowPriority(num, k));
        continue;
      }
      // 높은 우선순위에서 제거
      if (num == 1) {
        deleteQueue(highPQueue);
      } else {
        deleteQueue(lowPQueue);
      }

    }

    Integer high = deleteQueue(highPQueue);
    Integer low = deleteQueue(lowPQueue);

    if (high == null || low == null)
      System.out.println("EMPTY");
    else {
      System.out.println(high + " " + low);
    }

  }

  public static Integer deleteQueue(Queue<Number> queue) {
    while (!queue.isEmpty() && shared.contains(queue.peek()))
      queue.poll();

    if (!queue.isEmpty()) {
      Number val = queue.poll();
      shared.add(val);

      return val.num;
    }

    return null;
  }
}
